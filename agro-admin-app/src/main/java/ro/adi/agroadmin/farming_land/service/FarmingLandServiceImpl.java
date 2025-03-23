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
import ro.adi.agroadmin.farming_land.dto.request.*;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandImageResponse;
import ro.adi.agroadmin.farming_land.dto.response.FarmingLandResponse;
import ro.adi.agroadmin.farming_land.jpa.FarmingLandImageRepository;
import ro.adi.agroadmin.farming_land.jpa.FarmingLandRepository;
import ro.adi.agroadmin.farming_land.jpa.entity.FarmingLandEntity;
import ro.adi.agroadmin.farming_land.jpa.entity.FarmingLandImageEntity;
import ro.adi.agroadmin.farming_land.specification.FarmingLandSpecificationUtility;
import ro.adi.agroadmin.user.utils.UserUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FarmingLandServiceImpl implements FarmingLandService {

    private final FarmingLandMapper farmingLandMapper;
    private final FarmingLandRepository farmingLandRepository;
    private final FarmingLandImageRepository farmingLandImageRepository;

    @Override
    public Float getAreaInHaOfFieldsAdministrated() {
        var administratedBy = UserUtils.getIdOfCurrentUser();
        var areaInHa = farmingLandRepository.calculateTotalAreaByCreatedByAndAreaUnitType(administratedBy, AreaUnitType.HM);
        var areaInAr = farmingLandRepository.calculateTotalAreaByCreatedByAndAreaUnitType(administratedBy, AreaUnitType.AR);
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

    @Override
    @Transactional
    public Map<Integer, String> uploadFile(UploadFieldImageRequest request, Integer farmingLandId) {
        var userId = UserUtils.getIdOfCurrentUser();
        var entities = new ArrayList<FarmingLandImageEntity>();
        var contentMappedByFileName = new HashMap<String, String>();
        request.getImages().forEach(image -> {
            contentMappedByFileName.put(image.getFileName(), image.getContent());
            var entity = new FarmingLandImageEntity();
            entity.setCreatedBy(userId);
            entity.setFarmingLandId(farmingLandId);
            entity.setAt(LocalDateTime.ofInstant(request.getAt().toInstant(), ZoneId.of("UTC")));
            entity.setFileName(image.getFileName());
            entities.add(entity);
        });
        farmingLandImageRepository.saveAll(entities);
        var contentMappedById = new HashMap<Integer, String>();
        entities.forEach(entity -> contentMappedById.put(entity.getId(), contentMappedByFileName.get(entity.getFileName())));
        return contentMappedById;
    }

    @Override
    public Page<FarmingLandImageResponse> listFiles(ListFieldImageRequest request, Integer farmingLandId) {
        var userId = UserUtils.getIdOfCurrentUser();
        var entities = farmingLandImageRepository.findAllByFarmingLandIdAndCreatedByAndAtBetweenOrderByAtDesc(farmingLandId, userId, request.getStartDate(), request.getEndDate(), request.getPageable());
        return farmingLandMapper.toListFarmingLandImageResponse(entities);
    }

    @Override
    @Transactional
    public void deleteFile(Integer id) {
        farmingLandImageRepository.deleteById(id);
    }
}