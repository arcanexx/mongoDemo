package mongoDemo.JMongoObjects;

import com.googlecode.mjorm.annotations.Entity;
import com.googlecode.mjorm.annotations.Property;

@Entity
public class Document extends BaseEntity {
	private String description;
	private Company company;
	
	@Property
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	@Property
	public Company getCompany() {return company;}
	public void setCompany(Company company) {this.company = company;}

}