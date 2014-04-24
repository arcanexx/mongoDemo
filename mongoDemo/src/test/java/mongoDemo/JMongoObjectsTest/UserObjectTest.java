package mongoDemo.JMongoObjectsTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import mongoDemo.JMongoObjects.Company;
import mongoDemo.JMongoObjects.User;

import org.junit.Test;

public class UserObjectTest {
	private final String userId = "12345";
	private User user = new User();
	private ArrayList<Company> companies = new ArrayList<Company>();

	@Test
	public void testGetandSetCompanies() {
		companies.add(new Company());
		companies.add(new Company());
		companies.add(new Company());
		user.setCompanies(companies);
		List<Company> companies = user.getCompanies();
		assertEquals(3, companies.size());
	}

	@Test
	public void testGetAndSetUserId() {
		user.setUserId(userId);
		assertEquals(userId, user.getUserId());
	}
	
	@Test
	public void testBaseGetId(){
		assertNotNull(user.getId());
	}
	
	@Test
	public void testBaseEquals(){
		assertNotNull(user.equals(user));
	}
}
