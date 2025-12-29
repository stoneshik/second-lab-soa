package lab.is.dto.requests;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "transport")
@XmlEnum
public enum TransportRequestDto {
    @XmlEnumValue("FEW")
    FEW,
    @XmlEnumValue("NONE")
    NONE,
    @XmlEnumValue("LITTLE")
    LITTLE,
    @XmlEnumValue("NORMAL")
    NORMAL,
    @XmlEnumValue("ENOUGH")
    ENOUGH;
}
