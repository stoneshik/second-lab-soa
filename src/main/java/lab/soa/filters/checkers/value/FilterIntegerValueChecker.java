package lab.soa.filters.checkers.value;

import java.util.regex.Pattern;

public class FilterIntegerValueChecker implements FilterValueChecker {
    private final Pattern integerPattern = Pattern.compile("-?\\d+");

    public boolean isIncorrectValue(String rawStringValue) {
        return !integerPattern.matcher(rawStringValue).matches();
    }
}
