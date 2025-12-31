package lab.soa.filters.checkers.value;

import lab.soa.bd.entities.Transport;

public class FilterTransportValueChecker implements FilterValueChecker {
    public boolean isIncorrectValue(String rawStringValue) {
        return !Transport.isValidValue(rawStringValue);
    }
}
