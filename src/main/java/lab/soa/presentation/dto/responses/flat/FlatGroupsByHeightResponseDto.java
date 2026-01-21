package lab.soa.presentation.dto.responses.flat;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "groupsWrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlatGroupsByHeightResponseDto {
    @Builder.Default
    @XmlElementWrapper(name = "groups")
    @XmlElement(name = "group")
    private List<FlatGroupByHeightResponseDto> groups = new ArrayList<>();
}
