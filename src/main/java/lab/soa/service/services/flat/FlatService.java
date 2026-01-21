package lab.soa.service.services.flat;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lab.soa.domain.models.Flat;
import lab.soa.domain.repositories.flat.FlatRepositoryImpl;
import lab.soa.domain.repositories.flat.HeightGroupProjection;
import lab.soa.domain.specifications.Specification;
import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.infrastructure.exceptions.ObjectNotFoundException;
import lab.soa.presentation.dto.requests.flat.FlatRequestCreateDto;
import lab.soa.presentation.dto.requests.flat.FlatRequestUpdateDto;
import lab.soa.presentation.dto.responses.LongValueResponseDto;
import lab.soa.presentation.dto.responses.flat.FlatGroupByHeightResponseDto;
import lab.soa.presentation.dto.responses.flat.FlatGroupsByHeightResponseDto;
import lab.soa.presentation.dto.responses.flat.FlatResponseByIdDto;
import lab.soa.presentation.dto.responses.flat.FlatResponseDto;
import lab.soa.presentation.dto.responses.flat.WrapperListFlatsResponseDto;
import lab.soa.presentation.params.sort.SortParam;
import lab.soa.service.filters.flat.FlatFilterParam;
import lab.soa.service.mappers.flat.FlatToDtoFromEntityMapper;
import lab.soa.service.mappers.flat.FlatToEntityFromDtoCreateRequest;
import lab.soa.service.mappers.flat.FlatToEntityFromDtoUpdateRequest;
import lab.soa.service.services.flat.factories.FlatGetAllMethodSpecificationFactory;

@ApplicationScoped
public class FlatService {
    @Inject
    private FlatRepositoryImpl flatRepository;

    @Inject
    private FlatTxService flatTxService;

    @Inject
    private FlatToEntityFromDtoCreateRequest flatToEntityFromDtoCreateRequest;

    @Inject
    private FlatToEntityFromDtoUpdateRequest flatToEntityFromDtoUpdateRequest;

    @Transactional
    public FlatResponseByIdDto create(@Valid FlatRequestCreateDto dto) {
        Flat flat = flatToEntityFromDtoCreateRequest.toEntityFromDtoAndCreateNestedEntities(dto);
        Flat savedFlat = flatRepository.create(flat);
        return FlatToDtoFromEntityMapper.toDtoByIdFromEntity(savedFlat);
    }

    @Transactional
    public WrapperListFlatsResponseDto findAll(
        List<FlatFilterParam> filterParams,
        int page,
        long size,
        List<SortParam> sortParams
    ) {
        FlatGetAllMethodSpecificationFactory factory = new FlatGetAllMethodSpecificationFactory();
        Specification<Flat> specification = factory.create(filterParams);
        int offset = page * (int) size;
        List<Flat> flats = flatRepository.findAll(specification, offset, (int) size, sortParams);
        long totalElements = flatRepository.count(specification);
        int totalPages = (int) Math.ceil((double) totalElements / size);
        List<FlatResponseDto> flatResponseDtos = new ArrayList<>();
        for (Flat flat : flats) {
            flatResponseDtos.add(FlatToDtoFromEntityMapper.toDtoFromEntity(flat));
        }
        return WrapperListFlatsResponseDto.builder()
            .totalElements(totalElements)
            .totalPages(totalPages)
            .currentPage(page)
            .pageSize(flatResponseDtos.size())
            .flats(flatResponseDtos)
            .build();
    }

    @Transactional
    public void deleteOneFlatByFilter(
        String houseName,
        Integer houseYear,
        Integer numberOfFlatsOnFloor
    ) {
        if (houseName == null && houseYear == null && numberOfFlatsOnFloor == null) {
            throw new IncorrectParamException("At least one parameter was not passed");
        }
        int numberDeletedRows = flatRepository.deleteFirstByHouseCriteria(
            houseName,
            houseYear,
            numberOfFlatsOnFloor
        );
        if (numberDeletedRows == 0) {
            throw new ObjectNotFoundException("Not found flat by filter param");
        }
    }

    @Transactional
    public FlatResponseByIdDto findById(Long id) {
        Flat flat = flatTxService.findByIdReturnsEntity(id);
        return FlatToDtoFromEntityMapper.toDtoByIdFromEntity(flat);
    }

    @Transactional
    public FlatResponseByIdDto update(Long id, @Valid FlatRequestUpdateDto updateDto) {
        Flat foundFlat = flatTxService.findByIdReturnsEntity(id);
        foundFlat = flatToEntityFromDtoUpdateRequest.toEntityFromDtoAndUpdateNestedEntities(
            updateDto,
            foundFlat
        );
        Flat savedFlat = flatRepository.update(foundFlat);
        return FlatToDtoFromEntityMapper.toDtoByIdFromEntity(savedFlat);
    }

    @Transactional
    public void delete(Long id) {
        Flat flat = flatTxService.findByIdReturnsEntity(id);
        flatRepository.delete(flat);
    }

    @Transactional
    public LongValueResponseDto getAmountOfHeights() {
        Long amountOfHeights = flatRepository.sumAllHeights();
        if (amountOfHeights == null) {
            amountOfHeights = 0L;
        }
        return LongValueResponseDto.builder()
            .value(amountOfHeights)
            .build();
    }

    @Transactional
    public FlatGroupsByHeightResponseDto getGroupsByHeight() {
        List<HeightGroupProjection> heightGroupProjections = flatRepository.getHeightDistributionByCount();
        List<FlatGroupByHeightResponseDto> groups = new ArrayList<>();
        for (HeightGroupProjection groupProjection: heightGroupProjections) {
            groups.add(
                FlatGroupByHeightResponseDto.builder()
                    .height(groupProjection.getHeight())
                    .count(groupProjection.getCount())
                    .build()
            );
        }
        return FlatGroupsByHeightResponseDto.builder()
            .groups(groups)
            .build();
    }

    @Transactional
    public Flat findByIdReturnsEntity(Long id) {
        return flatTxService.findByIdReturnsEntity(id);
    }
}
