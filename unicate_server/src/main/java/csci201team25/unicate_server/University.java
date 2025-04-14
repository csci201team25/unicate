package csci201team25.unicate_server;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jakarta.persistence.*;

/* Representation of the Universities Table in the DB */
/* Each instance of this class corresponds to the entries */
@Entity
@Table(name="universities")
public class University {
	@Id
	@Column(name="uniID")
	private Long uniID;
	public Long getUniID() {
		return uniID;
	}
	public void setUniID(Long uniID) {
		this.uniID = uniID;
	}

	@Column(name="UniversityName")
	private String universityName;
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName =  universityName;
	}

	@Column(name="CalendarDates")
	private String calendarDates;
	
	public String getCalendarDates() {
		return calendarDates;
	}

	public void setCalendarDates(String calendarDates) {
		this.calendarDates = calendarDates;
	}

	@Override
	public String toString() {
		String output = "University Entry\nID: %s; UniversityName: %s\n |";
		for (DateRange date : getDateRanges()) {
			output += date + " | ";
		}
	    return String.format(output, uniID, universityName);
	}
	
	public ArrayList<DateRange> getDateRanges(){
		ArrayList<DateRange> dateList = new ArrayList<DateRange>();

		String[] dates = this.calendarDates.split(", ");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for(String dateString : dates) {
			LocalDate newDate = LocalDate.parse(dateString, formatter);
			dateList.add(new DateRange(newDate, newDate));
		}
		
		return dateList;
	}
}
