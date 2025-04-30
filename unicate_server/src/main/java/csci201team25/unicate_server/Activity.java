package csci201team25.unicate_server;

import jakarta.persistence.*;

/* Representation of the Universities Table in the DB */
/* Each instance of this class corresponds to the entries */
@Entity
@Table(name="activities")
public class Activity {
	@Id
	@Column(name="actID")
	private Long actID;
	public Long getActID() {
		return actID;
	}
	public void setActID(Long actID) {
		this.actID = actID;
	}

	@Column(name="actName")
	private String actName;
	public String getactName() {
		return actName;
	}
	public void setactName(String actName) {
		this.actName =  actName;
	}
	
//	@Column(name="actLocation")
//	private String actLocation;
//	
//	public String getactLocation() {
//		return actLocation;
//	}
//
//	public void setactLocation(String actLocation) {
//		this.actLocation = actLocation;
//	}
	
	@ManyToOne
	@JoinColumn(name = "uniID")
	private University university;

	@Override
	public String toString() {
		String output = "Activity EntryID: %s; Activity Name: %s; For Uni: %s\n";
	    return String.format(output, actID, actName, university.getUniversityName());
	}
}
