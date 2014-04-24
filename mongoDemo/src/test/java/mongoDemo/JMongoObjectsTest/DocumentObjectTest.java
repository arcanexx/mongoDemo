package mongoDemo.JMongoObjectsTest;

import static org.junit.Assert.*;
import mongoDemo.JMongoObjects.Company;
import mongoDemo.JMongoObjects.Document;

import org.junit.Test;

public class DocumentObjectTest {
	private final String description = "Description";
	private final String id = "12345";
	private Document document = new Document();
	private Company company = new Company();

	@Test
	public void testGetAndSetDocId() {
		document.setDocId(id);
		String d = document.getDocId();
		assertEquals(d, id);
	}
	
	@Test
	public void testGetAndSetDescription() {
		document.setDescription(description);
		String d = document.getDescription();
		assertEquals(d, description);
	}

	@Test
	public void testGetAndSetCompany() {
		document.setCompany(company);
		Company c = document.getCompany();
		assertEquals(c, company);
	}

	@Test
	public void testBaseGetId(){
		assertNotNull(document.getId());
	}
	
	@Test
	public void testBaseEquals(){
		assertNotNull(document.equals(document));
	}
	
}
