package lab.is.dto.requests;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "view")
@XmlEnum
public enum ViewRequestDto {
    @XmlEnumValue("STREET")
    STREET,
    @XmlEnumValue("YARD")
    YARD,
    @XmlEnumValue("PARK")
    PARK,
    @XmlEnumValue("BAD")
    BAD,
    @XmlEnumValue("GOOD")
    GOOD;
}
