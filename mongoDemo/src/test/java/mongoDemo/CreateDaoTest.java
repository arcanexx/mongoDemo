package mongoDemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

public class CreateDaoTest {
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
	public void testSaveCompany() throws InterruptedException {

		Company company = new Company();
		company.setStateId("12345");
		company.setName("GoDaddy");
		Company aCompany = mDao.createObject("sampleCompany", company);

		DaoQuery query = mDao.createQuery();
		query.setCollection("sampleCompany");
		query.eq("stateId", "12345");
		query.eq("name", "GoDaddy");
		Company c = query.findObject(Company.class);
		assertEquals(c.getStateId(), aCompany.getStateId());
		assertEquals(c.getName(), aCompany.getName());
		assertEquals(new ArrayList<Document>(), c.getDocuments());
		assertNotSame(c.getId(), aCompany.getId());
	}

	@Test
	public void testSaveDocument() {
		final Integer documentCount = 3;
		final String descriptionString = "Estimated value 1.5 Billion";

		Document document = new Document();
		document.setDescription(descriptionString);
		document.setCompany(mongoTestHelper.makeCompany(documentCount));
		Document aDocument = mDao.createObject("sampleDocument", document);

		DaoQuery query = mDao.createQuery();
		query.setCollection("sampleDocument");
		query.eq("description", descriptionString);
		Document d = query.findObject(Document.class);

		assertEquals(d.getCompany().getName(), aDocument.getCompany().getName());
		assertEquals(d.getDescription(), aDocument.getDescription());
		assertNotSame(d.getId(), aDocument.getId());
	}

	@Test
	public void testSaveUser() {
		User user = mongoTestHelper.makeUser();
		User aUser = mDao.createObject("sampleUser", user);

		DaoQuery query = mDao.createQuery();
		query.setCollection("sampleUser");
		query.eq("userId", aUser.getUserId());
		User u = query.findObject(User.class);

		assertEquals(u.getUserId(), aUser.getUserId());
		assertEquals(u.getCompanies().size(), aUser.getCompanies().size());
		assertNotSame(u.getId(), aUser.getId());
	}

	@Test
	public void testCompanySubComponent() {
		User user = mongoTestHelper.makeUser();
		mDao.createObject("sampleUser", user);
		DaoQuery query;

		query = mDao.createQuery();
		query.setCollection("sampleUser");
		User c = query.findObject(User.class);
		assertNotNull(c.getCompanies());

		for (int x = 0; x < c.getCompanies().size(); x++) {
			// Not only do they all exist, they're even in order!
			assertEquals(c.getCompanies().get(x).getName(), user.getCompanies()
					.get(x).getName());
		}
	}

	@Test
	public void testDocumentSubComponent() {
		User user = mongoTestHelper.makeUser();
		mDao.createObject("sampleUser", user);
		DaoQuery query;

		query = mDao.createQuery();
		query.setCollection("sampleUser");
		User c = query.findObject(User.class);
		assertNotNull(c.getCompanies());

		for (int x = 0; x < c.getCompanies().size(); x++) {
			// Not only do they all exist, they're even in order!
			assertNotNull(c.getCompanies().get(x).getDocuments());
			assertEquals(c.getCompanies().get(x).getDocuments(), user
					.getCompanies().get(x).getDocuments());
		}
	}
	
}
