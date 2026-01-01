package lab.soa.utils.checkers.value;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class FilterLocalDatetimeValueChecker implements FilterValueChecker {
    public boolean isIncorrectValue(String rawStringValue) {
        try {
            LocalDateTime.parse(rawStringValue);
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }
}
