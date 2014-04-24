package mongoDemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;

import mongoDemo.JMongoObjects.Company;
import mongoDemo.JMongoObjects.Document;
import mongoDemo.JMongoObjects.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.mjorm.MongoDao;
import com.googlecode.mjorm.query.DaoQuery;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class UpdateDaoTest {
	protected DBCollection collection;
	protected MongoDao mDao;
	protected DaoQuery query;
	protected DB db;

	@Before
	public void before() {
		query = new DaoQuery();
		mDao = MongoInit.getDao();
		db = mDao.getDB();
		query.setDB(db);
	}

	@After
	public void after() {
		db.dropDatabase();
	}

	@Test
	public void testUpdateCompany() throws InterruptedException {
		Company company = new Company();
		company.setStateId("12345");
		company.setName("GoDaddy");
	    mDao.createObject("sampleCompanyUpdate", company);
	    
		DaoQuery query = mDao.createQuery();
		query.setCollection("sampleCompanyUpdate");
		query.eq("stateId", "12345");
		query.modify().set("name", "TotallyNOTGoDaddy");
		query.modify().push("name");
		query.modify().update();
		
		query = mDao.createQuery();
		query.setCollection("sampleCompanyUpdate");
		query.eq("stateId", "12345");
		
		Company c = query.findObject(Company.class);
		assertEquals(c.getName().toString(), "TotallyNOTGoDaddy");
	}

	@Test
	public void testUpdateDocument() {
		final Integer documentCount = 3;
		final String descriptionString = "Estimated value 1.5 Billion";
		final String newDescriptionString = "Estimated value 2 Billion";

		Document document = new Document();
		document.setDescription(descriptionString);
		document.setDocId("12345");
		document.setCompany(mongoTestHelper.makeCompany(documentCount));
		Document aDocument = mDao.createObject("sampleDocumentUpdate", document);

		DaoQuery query = mDao.createQuery();
		query.setCollection("sampleDocumentUpdate");
		query.eq("description", descriptionString);
		Document d = query.findObject(Document.class);

		assertEquals(d.getCompany().getName(), aDocument.getCompany().getName());
		assertEquals(d.getDescription(), aDocument.getDescription());
		assertNotSame(d.getId(), aDocument.getId());

		query = mDao.createQuery();
		query.setCollection("sampleDocumentUpdate");
		query.eq("docId", "12345");
		query.modify().set("description", newDescriptionString);
		query.modify().push("description");
		query.modify().update();
		
		d = query.findObject(Document.class);
		assertEquals(d.getDescription(), newDescriptionString);
	}

	@Test
	public void testUpdateUser() {
		User user = mongoTestHelper.makeUser();
		User aUser = mDao.createObject("sampleUserUpdate", user);

		DaoQuery query = mDao.createQuery();
		query.setCollection("sampleUserUpdate");
		query.eq("userId", aUser.getUserId());
		User u = query.findObject(User.class);

		assertEquals(u.getUserId(), aUser.getUserId());
		assertEquals(u.getCompanies().size(), aUser.getCompanies().size());
		assertNotSame(u.getId(), aUser.getId());
		
		query = mDao.createQuery();
		query.setCollection("sampleUserUpdate");
		query.eq("userId", aUser.getUserId());
		query.modify().set("companies", new ArrayList<Company>());
		query.modify().push("companies");
		query.modify().update();
		 u = query.findObject(User.class);
		 
		assertEquals(u.getCompanies().size(),0);
		
	}

}
