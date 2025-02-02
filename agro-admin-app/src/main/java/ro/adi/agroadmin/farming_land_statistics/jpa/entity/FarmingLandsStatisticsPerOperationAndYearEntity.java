package ro.adi.agroadmin.farming_land_statistics.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.adi.agroadmin.common.entity.BaseEntity;
import ro.adi.agroadmin.common.entity.OperationType;
import ro.adi.agroadmin.farming_land_statistics.jpa.entity.composite_key.FarmingLandsStatisticsPerOperationAndYearCompositeKey;

@Getter
@Setter
@Entity
@Table(name = "farming_land_statistics_per_operation_and_year")
@IdClass(FarmingLandsStatisticsPerOperationAndYearCompositeKey.class)
public class FarmingLandsStatisticsPerOperationAndYearEntity extends BaseEntity {

    @Id
    private Integer year;
    @Id
    private Integer createdBy;
    @Id
    @Enumerated(value = EnumType.STRING)
    private OperationType operation;
    @Column
    private Float cost;
    @Column
    private Float revenue;
}
