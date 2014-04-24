package mongoDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import mongoDemo.JMongoObjects.Company;
import mongoDemo.JMongoObjects.Document;
import mongoDemo.JMongoObjects.User;

public class mongoTestHelper {
	public static User makeUser() {
		User user = new User();
		user.setUserId(String.valueOf(new Random().nextInt(Integer.MAX_VALUE)));
		for (int i = 0; i < 10; i++) {
			user.getCompanies().add(makeCompany(i));
		}
		return user;
	}

	public static Company makeCompany(int documentCount) {
		Company company = new Company();
		for (int j = 0; j < 3; j++) {
			company.getDocuments().addAll(makeDocuments(j, company));
		}
		company.setName("Company Name " + UUID.randomUUID());
		company.setStateId(String.valueOf(new Random().nextInt(Integer.MAX_VALUE)));
		return company;
	}

	public static List<Document> makeDocuments(int howMany, Company company) {
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < howMany; i++) {
			Document document = new Document();
			document.setCompany(company);
			document.setDescription(("Description " + UUID.randomUUID()
					.toString()));
		}
		return documents;
	}
}
