package csci201team25.unicate_server;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// possibly unnecessary?

public class ScheduleComparer {
    public static List<DateRange> compareUniversitySchedules(List<University> universities) {
        if (universities.isEmpty())
            return new ArrayList<>();
        List<DateRange> currentMatchingRanges = universities.getFirst().getDateRanges();
        for (int i = 1; i < universities.size(); i++) {
            List<DateRange> newMatchingRanges = new ArrayList<>();
            University nextUniversity = universities.get(i);
            // compare every DateRange of the current university to the existing overlaps
            for (DateRange newRange : nextUniversity.getDateRanges()) {
                for (DateRange curRange : currentMatchingRanges) {
                    Optional<DateRange> possibleOverlap = rangeOverlap(curRange, newRange);
                    possibleOverlap.ifPresent(newMatchingRanges::add);
                }
            }
            // early end case
            if (newMatchingRanges.isEmpty())
                return new ArrayList<>();
            // normal case (there are overlaps)
            currentMatchingRanges = newMatchingRanges;
        }
        return currentMatchingRanges;
    }

    private static Optional<DateRange> rangeOverlap(DateRange a, DateRange b) {
        // get max of start dates
        LocalDate overlapStart = a.startDate.isAfter(b.startDate) ? a.startDate : b.startDate;
        // get min of end dates
        LocalDate overlapEnd = a.endDate.isBefore(b.endDate) ? a.endDate : b.endDate;

        // if there is a valid overlap
        if (overlapStart.isBefore(overlapEnd) || overlapStart.isEqual(overlapEnd)) {
            return Optional.of(new DateRange(overlapStart, overlapEnd));
        }
        // invalid overlap
        else {
            return Optional.empty();
        }
    }
    
    
    
}
