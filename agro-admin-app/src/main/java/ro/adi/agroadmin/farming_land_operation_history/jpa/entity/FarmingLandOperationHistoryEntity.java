package ro.adi.agroadmin.farming_land_operation_history.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ro.adi.agroadmin.common.entity.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "farming_land_operation_history")
public class FarmingLandOperationHistoryEntity extends BaseEntity {
    public final static String FARMING_LAND_ID_FILED_NAME = "farmingLandId";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "farming_land_operation_history_id_generator")
    @SequenceGenerator(name = "farming_land_operation_history_id_generator", sequenceName = "farming_land_operation_history_seq", allocationSize = 1)
    @Column(columnDefinition = "integer", updatable = false, nullable = false)
    private Integer id;
    @Column(updatable = false)
    private String createdBy;
    @Column
    private Float estimatedCost;
    @Column
    private Integer farmingLandId;
    @Column
    private Float estimatedHarvest;
    @Column
    private Float estimatedRevenue;
    @Column
    private LocalDateTime appliedAt;
    @Column
    @Enumerated(value = EnumType.STRING)
    private OperationType operation;
    @Column
    @Enumerated(value = EnumType.STRING)
    private PlantType plantType;
    @Column
    @Enumerated(value = EnumType.STRING)
    private CurrencyType estimatedCostCurrencyType;
    @Column
    @Enumerated(value = EnumType.STRING)
    private WeightMeasureType estimatedHarvestMeasureType;
    @Column
    @Enumerated(value = EnumType.STRING)
    private CurrencyType estimatedRevenueCurrencyType;
}
