package ro.adi.farming_land.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import ro.adi.farming_land.dto.request.*;
import ro.adi.farming_land.dto.response.FarmingLandImageBlobResponseDto;
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

    @PostMapping("/{farmingLandId}/files")
    void uploadFile(@RequestBody UploadFieldImageRequestDto requestDto, @PathVariable Integer farmingLandId);

    @PostMapping("/{farmingLandId}/files/list")
    Page<FarmingLandImageBlobResponseDto> listFiles(@RequestBody ListFieldImageRequestDto requestDto, @PathVariable Integer farmingLandId);

    @DeleteMapping("/files/{id}")
    void deleteFile(@PathVariable Integer id);
}
