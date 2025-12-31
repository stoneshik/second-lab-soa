package lab.soa.filters.checkers.value;

import lab.soa.bd.entities.View;

public class FilterViewValueChecker implements FilterValueChecker {
    public boolean isIncorrectValue(String rawStringValue) {
        return !View.isValidValue(rawStringValue);
    }
}
