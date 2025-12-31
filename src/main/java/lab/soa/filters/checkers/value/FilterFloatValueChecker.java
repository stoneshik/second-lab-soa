package lab.soa.filters.checkers.value;

import java.util.regex.Pattern;

public class FilterFloatValueChecker implements FilterValueChecker {
    private final Pattern floatPattern = Pattern.compile("-?\\d+\\.\\d+");

    public boolean isIncorrectValue(String rawStringValue) {
        return !floatPattern.matcher(rawStringValue).matches();
    }
}
