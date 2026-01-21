package lab.soa.infrastructure.xml.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class IntegerXmlAdapter extends XmlAdapter<String, Integer> {
    @Override
    public Integer unmarshal(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String marshal(Integer value) {
        return value == null ? null : value.toString();
    }
}
