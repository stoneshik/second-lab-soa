package lab.soa.utils.checkers.value;

import lab.soa.domain.models.Transport;

public class FilterTransportValueChecker implements FilterValueChecker {
    public boolean isIncorrectValue(String rawStringValue) {
        return !Transport.isValidValue(rawStringValue);
    }
}
