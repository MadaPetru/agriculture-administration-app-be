package ro.adi.agroadmin.farming_land.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.adi.agroadmin.common.entity.BaseEntity;

import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Table(name = "farming_land_image")
public class FarmingLandImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farming_land_image_id_generator")
    @SequenceGenerator(name = "farming_land_image_id_generator", sequenceName = "farming_land_image_seq", allocationSize = 1)
    @Column(columnDefinition = "integer", updatable = false, nullable = false)
    private Integer id;

    @Column(updatable = false)
    private Integer createdBy;

    @Column
    private String fileName;

    @Column
    private LocalDateTime at;

    @Column
    private Integer farmingLandId;
}
