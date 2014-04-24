package mongoDemo.JMongoObjects;

import java.util.ArrayList;
import java.util.List;
import com.googlecode.mjorm.annotations.Entity;
import com.googlecode.mjorm.annotations.Property;

@Entity
public class User extends BaseEntity {
	
	private String userId;
	private List<Company> companies = new ArrayList<Company>();
	
	@Property
	public List<Company> getCompanies() {return companies;}
	public void setCompanies(List<Company> companies) {this.companies = companies;}

	@Property
	public String getUserId() {return userId;}
	public void setUserId(String id) {this.userId = id;}
	
}