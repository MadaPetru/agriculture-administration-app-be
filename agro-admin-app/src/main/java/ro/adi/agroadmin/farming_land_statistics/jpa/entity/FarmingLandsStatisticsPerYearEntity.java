package ro.adi.agroadmin.farming_land_statistics.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.adi.agroadmin.common.entity.BaseEntity;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.composite_key.FarmingLandsStatisticsPerOperationAndYearCompositeKey;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.composite_key.FarmingLandsStatisticsPerYearCompositeKey;

@Getter
@Setter
@Entity
@Table(name = "farming_land_statistics_per_year")
@IdClass(FarmingLandsStatisticsPerYearCompositeKey.class)
public class FarmingLandsStatisticsPerYearEntity extends BaseEntity {

    @Id
    private Integer year;
    @Id
    private String createdBy;
    @Column
    private Float cost;
    @Column
    private Float revenue;
}
