package mongoDemo.JMongoObjects;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.mjorm.annotations.Entity;
import com.googlecode.mjorm.annotations.Property;

@Entity
public class Company extends BaseEntity {
	private String name;
	private String stateId;
	private List<Document> documents = new ArrayList<Document>();

	@Property
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	@Property
	public List<Document> getDocuments() {return documents;}
	public void setDocuments(List<Document> documents) {this.documents = documents;}

	@Property
	public String getStateId() {return stateId;}
	public void setStateId(String stateId) {this.stateId = stateId;}

}