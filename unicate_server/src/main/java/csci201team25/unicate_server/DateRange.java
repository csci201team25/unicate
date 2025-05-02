package csci201team25.unicate_server;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// daterange class for internal use 
public class DateRange {
	LocalDate startDate;
	LocalDate endDate;
	
	public DateRange (LocalDate start, LocalDate end){
		startDate = start;
		endDate = end;
	}
	
	public long getDurationDays() {
		return ChronoUnit.DAYS.between(startDate, endDate) + 1;
	}
	
	@Override
	public String toString() {
		if (getDurationDays() == 1) {
			return startDate.toString();
		}
		return startDate.toString() + " - " + endDate.toString();
	}
}
