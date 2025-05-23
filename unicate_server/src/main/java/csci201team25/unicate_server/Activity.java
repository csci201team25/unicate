package csci201team25.unicate_server;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// representation of the uni table in the DB
// each instance of this class corresponds to the entries
@Entity
@Table(name="activities")
public class Activity {
	@Id
	@Column(name="actID")
	private int actID;
	public int getActID() { return actID; }
	public void setActID(int actID) {
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

	@Column(name = "description")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name = "uniID")
	@JsonIgnoreProperties("activities")
	private University university;

	public University getUniversity() {
		return university;
	}
	
	public University setUniversity() {
		return university;
	}

	@Override
	public String toString() {
		String output = "Activity EntryID: %s; Activity Name: %s; For Uni: %s\n";
	    return String.format(output, actID, actName, university.getUniversityName());
	}
}
