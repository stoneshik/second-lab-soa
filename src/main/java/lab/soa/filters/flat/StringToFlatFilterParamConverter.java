package lab.soa.filters.flat;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lab.soa.exceptions.IncorrectParamException;
import lab.soa.filters.checkers.value.FilterFloatValueChecker;
import lab.soa.filters.checkers.value.FilterIntegerValueChecker;
import lab.soa.filters.checkers.value.FilterLocalDatetimeValueChecker;
import lab.soa.filters.checkers.value.FilterStringValueChecker;
import lab.soa.filters.checkers.value.FilterTransportValueChecker;
import lab.soa.filters.checkers.value.FilterValueChecker;
import lab.soa.filters.checkers.value.FilterViewValueChecker;

@Component
public class StringToFlatFilterParamConverter implements Converter<String, FlatFilterParam> {
    private static final Pattern FILTER_PATTERN =
        Pattern.compile("^([a-z.]+)\\((eq|ne|gt|lt|gte|lte|interval|range)\\)(.+)$");

    private final Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

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

    @Override
    public FlatFilterParam convert(String source) {
        Matcher matcher = FILTER_PATTERN.matcher(source);
        if (!matcher.matches()) {
            throw new IncorrectParamException("Invalid filter format: " + source);
        }
        String filterFieldRawString = matcher.group(1);
        FlatFilterField filterField = FlatFilterField.parseFromString(filterFieldRawString);
        String operationRawString = matcher.group(2);
        FlatFilterOperation operation = FlatFilterOperation.parseFromString(operationRawString);
        String valueRawString = matcher.group(3);
        if (!mapFilterFieldToFilterValueChecker.containsKey(filterField)) {
            throw new IncorrectParamException("Incorrect field value: " + filterFieldRawString);
        }
        String filterMinValue = null;
        String filterMaxValue = null;
        if (operation.isRangeOrInterval()) {
            String[] rangeValues = valueRawString.split("_");
            if (rangeValues.length != 2) {
                throw new IncorrectParamException("Range and interval must have two values separated by '_'");
            }
            filterMinValue = rangeValues[0];
            filterMaxValue = rangeValues[1];
            if (isNotNumber(filterMinValue) || isNotNumber(filterMaxValue)) {
                throw new IncorrectParamException("Range and interval supports only numbers");
            }
            FilterValueChecker filterValueChecker = mapFilterFieldToFilterValueChecker.get(filterField);
            if (filterValueChecker.isIncorrectValue(filterMinValue) ||
                filterValueChecker.isIncorrectValue(filterMaxValue)) {
                throw new IncorrectParamException("Incorrect value: " + filterFieldRawString);
            }
        } else {
            FilterValueChecker filterValueChecker = mapFilterFieldToFilterValueChecker.get(filterField);
            if (filterValueChecker.isIncorrectValue(valueRawString)) {
                throw new IncorrectParamException("Incorrect value: " + filterFieldRawString);
            }
        }
        return FlatFilterParam.builder()
            .fieldFilter(filterField)
            .operation(operation)
            .value(valueRawString)
            .minValue(filterMinValue)
            .maxValue(filterMaxValue)
            .build();
    }

    public boolean isNotNumber(String numberString) {
        return !numberPattern.matcher(numberString).matches();
    }
}
