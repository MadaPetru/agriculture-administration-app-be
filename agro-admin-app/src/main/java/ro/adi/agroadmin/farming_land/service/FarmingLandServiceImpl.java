package ro.adi.agroadmin.farming_land.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.agroadmin.common.entity.AreaUnitType;
import ro.adi.agroadmin.common.exception.NotFoundException;
import ro.adi.agroadmin.farming_land.converter.FarmingLandMapper;
import ro.adi.agroadmin.farming_land.dto.request.FarmingLandSaveRequest;
import ro.adi.agroadmin.farming_land.dto.request.FarmingLandSearchRequest;
import ro.adi.agroadmin.farming_land.dto.request.FarmingLandUpdateRequest;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandResponse;
import ro.adi.agroadmin.farming_land.jpa.FarmingLandRepository;
import ro.adi.agroadmin.farming_land.jpa.entity.FarmingLandEntity;
import ro.adi.agroadmin.farming_land.specification.FarmingLandSpecificationUtility;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmingLandServiceImpl implements FarmingLandService {

    private final FarmingLandMapper farmingLandMapper;
    private final FarmingLandRepository farmingLandRepository;

    @Override
    public Float getAreaInHaOfFieldsAdministrated(String username) {
        var areaInHa = farmingLandRepository.calculateTotalAreaByCreatedByAndAreaUnitType(username, AreaUnitType.HM);
        var areaInAr = farmingLandRepository.calculateTotalAreaByCreatedByAndAreaUnitType(username, AreaUnitType.AR);
        return areaInHa + areaInAr / 100;
    }

    @Override
    public PageImpl<FarmingLandResponse> search(FarmingLandSearchRequest request) {
        var pageable = request.getPageable();
        var specification = FarmingLandSpecificationUtility.search(request);
        Page<FarmingLandEntity> entities = farmingLandRepository.findAll(specification, pageable);
        return farmingLandMapper.toPageImplFarmingLandResponse(entities);
    }

    @Override
    @Transactional
    public void saveFarmingLand(FarmingLandSaveRequest request) {
        var entity = farmingLandMapper.toFarmingLandEntity(request);
        farmingLandRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateFarmingLand(FarmingLandUpdateRequest request) {
        var entity = farmingLandMapper.toFarmingLandEntity(request);
        farmingLandRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteFarmingLandById(Integer id) {
        farmingLandRepository.deleteById(id);
    }

    @Override
    public FarmingLandResponse findFarmingLandByTitle(String title) {
        return farmingLandRepository.findByTitle(title)
                .map(farmingLandMapper::toFarmingLandResponse)
                .orElseThrow(() -> NotFoundException.getFarmingLandNotFoundByTitle(title));
    }
}