package ro.adi.farming_land.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FarmingLandSortBy {
    ID("id"), TITLE("title");
    private final String value;
}
