package lab.soa.utils.checkers.value;

import lab.soa.domain.models.View;

public class FilterViewValueChecker implements FilterValueChecker {
    public boolean isIncorrectValue(String rawStringValue) {
        return !View.isValidValue(rawStringValue);
    }
}
