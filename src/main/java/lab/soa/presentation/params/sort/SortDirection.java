package lab.soa.presentation.params.sort;

import lab.soa.infrastructure.exceptions.IncorrectParamException;

public enum SortDirection {
    ASC("asc"),
    DESC("desc")
    ;

    private final String value;

    private SortDirection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SortDirection parseValue(String value) {
        if (value == null) {
            throw new IncorrectParamException("Incorrect sort param: " + value);
        }
        for (SortDirection sortDirection: SortDirection.values()) {
            if (sortDirection.getValue().equals(value)) {
                return sortDirection;
            }
        }
        throw new IncorrectParamException("Incorrect sort param: " + value);
    }
}
