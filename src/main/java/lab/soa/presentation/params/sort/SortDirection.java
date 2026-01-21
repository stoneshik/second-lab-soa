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
        try {
            return SortDirection.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IncorrectParamException("Incorrect sort param: " + value);
        }
    }
}
