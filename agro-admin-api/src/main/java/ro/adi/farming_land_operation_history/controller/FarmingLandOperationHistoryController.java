package ro.adi.farming_land_operation_history.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySaveRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistorySearchRequestDto;
import ro.adi.farming_land_operation_history.dto.request.FarmingLandOperationHistoryUpdateRequestDto;
import ro.adi.farming_land_operation_history.dto.response.FarmingLandOperationHistoryResponseDto;

@RequestMapping(path = "/v1/farming-lands/operation-histories")
public interface FarmingLandOperationHistoryController {
    @PostMapping("/search")
    PageImpl<FarmingLandOperationHistoryResponseDto> search(@RequestBody @Valid FarmingLandOperationHistorySearchRequestDto requestDto);

    @PostMapping
    void saveFarmingLandOperationHistory(@RequestBody FarmingLandOperationHistorySaveRequestDto requestDto);

    @PutMapping
    void updateFarmingLandOperationHistory(@RequestBody FarmingLandOperationHistoryUpdateRequestDto requestDto);

    @DeleteMapping("/id/{id}")
    void deleteFarmingLandOperationHistoryById(@PathVariable @Valid @NotNull Integer id);

    @GetMapping("/id/{id}")
    FarmingLandOperationHistoryResponseDto findFarmingLandOperationHistoryById(@PathVariable @Valid @NotNull Integer id);
}