package ro.adi.farming_land.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import ro.adi.farming_land.dto.request.FarmingLandSaveRequestDto;
import ro.adi.farming_land.dto.request.FarmingLandSearchRequestDto;
import ro.adi.farming_land.dto.request.FarmingLandUpdateRequestDto;
import ro.adi.farming_land.dto.response.FarmingLandResponseDto;

@RequestMapping(path = "/v1/farming-lands")
public interface FarmingLandController {
    @PostMapping("/search")
    PageImpl<FarmingLandResponseDto> search(@RequestBody FarmingLandSearchRequestDto requestDto);

    @PostMapping
    void saveFarmingLand(@RequestBody FarmingLandSaveRequestDto requestDto);

    @PutMapping
    void updateFarmingLand(@RequestBody FarmingLandUpdateRequestDto requestDto);

    @DeleteMapping("/id/{id}")
    void deleteFarmingLandById(@PathVariable @Valid @NotNull Integer id);

    @GetMapping("/title/{title}")
    FarmingLandResponseDto findFarmingLandByTitle(@PathVariable @Valid @NotBlank String title);
}
