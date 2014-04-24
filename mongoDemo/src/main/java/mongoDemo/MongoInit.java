package mongoDemo;

import java.net.UnknownHostException;

import mongoDemo.JMongoObjects.Company;
import mongoDemo.JMongoObjects.Document;
import mongoDemo.JMongoObjects.User;

import com.googlecode.mjorm.MongoDao;
import com.googlecode.mjorm.MongoDaoImpl;
import com.googlecode.mjorm.annotations.AnnotationsDescriptorObjectMapper;
import com.mongodb.MongoClient;

public class MongoInit {
	public static final String DB_NAME = "localhost";
	private static boolean closed = false;
	private static MongoDao dao;

	private static class SingletonMongo {
		private static MongoClient mongoClient;

		static {
			try {
				System.out.println("Connecting to database: "+ DB_NAME);
				mongoClient = new MongoClient(DB_NAME);

				// create object mapper and add classes
				AnnotationsDescriptorObjectMapper objectMapper = new AnnotationsDescriptorObjectMapper();
				objectMapper.addClass(User.class);
				objectMapper.addClass(Company.class);
				objectMapper.addClass(Document.class);
				dao = new MongoDaoImpl(mongoClient.getDB(DB_NAME), objectMapper);
			} catch (UnknownHostException e) {
				System.out.println("Couldn't connect to localhost, is a mongoDB running locally??");
				e.printStackTrace();
			}
		}

		public static MongoDao getDao() {
			if (closed) {
				throw new RuntimeException("Datastore has been closed.");
			}
			return dao;
		}

		public static MongoClient getMongoClient() {
			return mongoClient;
		}

	}

	public static MongoDao getDao() {
		return SingletonMongo.getDao();
	}

	public static void close() {
		closed = true;
		MongoClient mongoClient = SingletonMongo.getMongoClient();
		if (mongoClient != null) {
			mongoClient.close();
		}
	}

}