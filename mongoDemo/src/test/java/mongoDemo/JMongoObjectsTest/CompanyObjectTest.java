package mongoDemo.JMongoObjectsTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import mongoDemo.JMongoObjects.Company;
import mongoDemo.JMongoObjects.Document;

import org.junit.Test;

public class CompanyObjectTest {
	private final String name = "GoDaddy";
	private final String stateId = "12345";
	private Company company = new Company();
	private ArrayList<Document> documents = new ArrayList<Document>();

	@Test
	public void testGetAndSetName() {
		company.setName(name);
		String n = company.getName();
		assertEquals(n, name);
	}

	@Test
	public void testGetAndSetDocuments() {
		documents.add(new Document());
		documents.add(new Document());
		documents.add(new Document());
		company.setDocuments(documents);
		List<Document> docuements = company.getDocuments();
		assertEquals(3, docuements.size());
	}

	@Test
	public void testGetAndSetStateId() {
		company.setStateId(stateId);
		String s = company.getStateId();
		assertEquals(s, stateId);
	}

	@Test
	public void testBaseGetId(){
		assertNotNull(company.getId());
	}
	
	@Test
	public void testBaseEquals(){
		assertNotNull(company.equals(company));
	}
}
