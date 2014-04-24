package mongoDemo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.mjorm.MongoDao;

public class MongoInitTest {
	private static final String MONGOURI = "localhost";
	MongoDao mDao;
	
	@Before
	public void testGetDao() {
		mDao = MongoInit.getDao();
	}

	@Test
	public void testConnection() {
		mDao = MongoInit.getDao();
		assertNotNull(mDao);
		assertEquals(MONGOURI, mDao.getDB().getName());
	}

	@Test(expected = RuntimeException.class)
	public void testClose() {
		mDao = MongoInit.getDao();
		assertNotNull(mDao);
		MongoInit.close();
		MongoInit.getDao();
	}
}
