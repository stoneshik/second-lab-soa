package lab.is.dto.responses;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "view")
@XmlEnum
public enum ViewResponseDto {
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
