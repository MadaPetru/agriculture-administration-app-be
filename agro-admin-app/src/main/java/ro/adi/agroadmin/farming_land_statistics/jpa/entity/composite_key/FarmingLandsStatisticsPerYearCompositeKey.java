package ro.adi.agroadmin.farming_land_statistics.jpa.entity.composite_key;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class FarmingLandsStatisticsPerYearCompositeKey implements Serializable {

    private Integer year;
    private Integer createdBy;
}
