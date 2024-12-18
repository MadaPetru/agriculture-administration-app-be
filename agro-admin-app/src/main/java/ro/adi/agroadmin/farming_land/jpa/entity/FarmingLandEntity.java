package ro.adi.agroadmin.farming_land.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.adi.agroadmin.common.entity.BaseEntity;
import ro.adi.agroadmin.common.entity.AreaUnitType;
import ro.adi.agroadmin.common.entity.DistanceUnitType;


@Setter
@Getter
@Entity
@Table(name = "farming_land")
public class FarmingLandEntity extends BaseEntity {
    public static String ID_FILED_NAME = "id";
    public static String TITLE_FILED_NAME = "title";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farming_land_id_generator")
    @SequenceGenerator(name = "farming_land_id_generator", sequenceName = "farming_land_seq", allocationSize = 1)
    @Column(columnDefinition = "integer", updatable = false, nullable = false)
    private Integer id;
    @Column
    private Float area;
    @Column
    private String title;
    @Column(updatable = false)
    private String createdBy;
    @Column
    @Enumerated(value = EnumType.STRING)
    private AreaUnitType areaUnitType;
    @Column
    private Float roughlyDistanceFromFarm;
    @Column
    @Enumerated(value = EnumType.STRING)
    private DistanceUnitType roughlyDistanceFromFarmUnitType;
}
