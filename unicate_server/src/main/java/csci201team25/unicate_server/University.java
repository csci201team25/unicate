package csci201team25.unicate_server;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.persistence.*;

/* Representation of the Universities Table in the DB */
/* Each instance of this class corresponds to the entries */
@Entity
@Table(name="universities")
public class University {
	@Id
	@Column(name="uniID")
	private int uniID;
	public int getUniID() {
		return uniID;
	}
	public void setUniID(int uniID) {
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
		
		JsonArray jsonArr = JsonParser.parseString(this.calendarDates).getAsJsonArray();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for(JsonElement dateString : jsonArr) {
			LocalDate startDate = LocalDate.parse(dateString.getAsJsonObject().get("start_date").getAsString(), formatter);
			LocalDate endDate = LocalDate.parse(dateString.getAsJsonObject().get("end_date").getAsString(), formatter);
			dateList.add(new DateRange(startDate, endDate));
		}
		
		return dateList;
	}
	
	// for when we only have spring break date in db
	public DateRange getSpringBreakDateRange() {
		return getDateRanges().getFirst();
	}
}
