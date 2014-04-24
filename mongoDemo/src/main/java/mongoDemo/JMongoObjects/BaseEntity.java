package mongoDemo.JMongoObjects;

import org.bson.types.ObjectId;

import com.googlecode.mjorm.annotations.Id;

public abstract class BaseEntity {
	@Id
	private ObjectId id = new ObjectId();

	public ObjectId getId() {return id;}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass().equals(getClass())) {
			BaseEntity other = (BaseEntity) obj;
			return id.equals(other.id);
		} else {
			return false;
		}
	}
}
