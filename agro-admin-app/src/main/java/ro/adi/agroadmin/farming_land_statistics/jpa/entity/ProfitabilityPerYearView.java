package ro.adi.agroadmin.farming_land_statistics.jpa.entity;

public interface ProfitabilityPerYearView {

    Float getCost();

    Float getRevenue();

    Integer getYear();
}
