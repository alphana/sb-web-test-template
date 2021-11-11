package name.alp.sbwebtesttemplate.dbo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "classification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Classification {

    @Id
    String id;
    @Column(name = "parent_id")
    String parentId;
    String name;

    @Column(name = "sector_code")
    String sectorCode;
    @Column(name = "sector_id")
    String sectorId;
    Integer severity;
}

