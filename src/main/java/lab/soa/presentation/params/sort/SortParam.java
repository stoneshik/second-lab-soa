package lab.soa.presentation.params.sort;

public class SortParam {
    private final String field;
    private final SortDirection direction;

    public SortParam(String field, String direction) {
        this.field = field;
        this.direction = SortDirection.parseValue(direction);
    }

    public String getField() {
        return field;
    }

    public SortDirection getDirection() {
        return direction;
    }

    public String getDirectionString() {
        return direction.getValue();
    }

    public boolean isAscending() {
        return SortDirection.ASC.equals(direction);
    }
}
