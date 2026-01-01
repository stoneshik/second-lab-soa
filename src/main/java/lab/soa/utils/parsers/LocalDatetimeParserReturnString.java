package lab.soa.utils.parsers;

import lab.soa.utils.checkers.value.LocalDatetimeValueChecker;

public class LocalDatetimeParserReturnString extends Parser<String> {
    private static final LocalDatetimeValueChecker VALUE_CHECKER = new LocalDatetimeValueChecker();

    public String parse(String value) {
        checkValueAndThrowExceptionIfIncorrect(VALUE_CHECKER, value);
        return value;
    }
}
