package csci201team25.unicate_server;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
		
	@Column(name = "actLocation")
	private String actLocation;

	public String getActLocation() {
		return actLocation;
	}
	public void setActLocation(String actLocation) {
		this.actLocation = actLocation;
	}

	@Column(name = "actImage")
	private String actImage;

	public String getActImage() {
		return actImage;
	}
	public void setActImage(String actImage) {
		this.actImage = actImage;
	}

	
	@ManyToOne
	@JoinColumn(name = "uniID")
	@JsonIgnoreProperties("activities") // prevent circular serialization
	private University university;

	@Override
	public String toString() {
		String output = "Activity EntryID: %s; Activity Name: %s; For Uni: %s\n";
	    return String.format(output, actID, actName, university.getUniversityName());
	}
}
