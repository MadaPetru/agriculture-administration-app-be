package ro.adi.agroadmin.farming_land_statistics.jpa.entity.composite_key;

import lombok.*;
import ro.adi.agroadmin.common.entity.OperationType;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class FarmingLandsStatisticsPerOperationAndYearCompositeKey implements Serializable {

    private Integer year;
    private String createdBy;
    private OperationType operation;
}
