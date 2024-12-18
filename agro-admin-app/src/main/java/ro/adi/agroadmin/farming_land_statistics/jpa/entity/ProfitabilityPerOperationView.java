package ro.adi.agroadmin.farming_land_statistics.jpa.entity;

import ro.adi.agroadmin.common.entity.OperationType;

public interface ProfitabilityPerOperationView {

    Float getCost();

    Float getRevenue();

    OperationType getOperation();
}
