package lab.soa.service.filters.flat.factories;

import java.util.Map;

import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.service.filters.flat.FlatFilterField;
import lab.soa.utils.checkers.value.FilterFloatValueChecker;
import lab.soa.utils.checkers.value.FilterIntegerValueChecker;
import lab.soa.utils.checkers.value.FilterLocalDatetimeValueChecker;
import lab.soa.utils.checkers.value.FilterStringValueChecker;
import lab.soa.utils.checkers.value.FilterTransportValueChecker;
import lab.soa.utils.checkers.value.FilterValueChecker;
import lab.soa.utils.checkers.value.FilterViewValueChecker;

public class FlatFilterCheckerFactory {
    private final Map<FlatFilterField, FilterValueChecker> mapFilterFieldToFilterValueChecker = Map.ofEntries(
        Map.entry(FlatFilterField.ID, new FilterIntegerValueChecker()),
        Map.entry(FlatFilterField.NAME, new FilterStringValueChecker()),
        Map.entry(FlatFilterField.COORDINATES_ID, new FilterIntegerValueChecker()),
        Map.entry(FlatFilterField.COORDINATES_X, new FilterFloatValueChecker()),
        Map.entry(FlatFilterField.COORDINATES_Y, new FilterIntegerValueChecker()),
        Map.entry(FlatFilterField.CREATION_DATE, new FilterLocalDatetimeValueChecker()),
        Map.entry(FlatFilterField.AREA, new FilterIntegerValueChecker()),
        Map.entry(FlatFilterField.NUMBER_OF_ROOMS, new FilterIntegerValueChecker()),
        Map.entry(FlatFilterField.HEIGHT, new FilterIntegerValueChecker()),
        Map.entry(FlatFilterField.VIEW, new FilterViewValueChecker()),
        Map.entry(FlatFilterField.TRANSPORT, new FilterTransportValueChecker()),
        Map.entry(FlatFilterField.HOUSE_ID, new FilterIntegerValueChecker()),
        Map.entry(FlatFilterField.HOUSE_NAME, new FilterStringValueChecker()),
        Map.entry(FlatFilterField.HOUSE_YEAR, new FilterIntegerValueChecker()),
        Map.entry(FlatFilterField.HOUSE_NUMBER_OF_FLATS_ON_FLOOR, new FilterIntegerValueChecker())
    );

    public FilterValueChecker create(FlatFilterField filterField) {
        if (!mapFilterFieldToFilterValueChecker.containsKey(filterField)) {
            throw new IncorrectParamException("Incorrect field value: " + filterField.getFilterFieldName());
        }
        return mapFilterFieldToFilterValueChecker.get(filterField);
    }
}
