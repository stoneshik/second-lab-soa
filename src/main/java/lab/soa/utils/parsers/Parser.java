package lab.soa.utils.parsers;

public interface Parser<T> {
    T parse(String value);
}
