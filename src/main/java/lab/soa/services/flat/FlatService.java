package lab.soa.services.flat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import lab.soa.bd.entities.Flat;
import lab.soa.dto.requests.flat.FlatRequestCreateDto;
import lab.soa.dto.requests.flat.FlatRequestUpdateDto;
import lab.soa.dto.responses.flat.FlatResponseByIdDto;
import lab.soa.dto.responses.flat.FlatResponseDto;
import lab.soa.dto.responses.flat.WrapperListFlatsResponseDto;
import lab.soa.exceptions.IncorrectDtoInRequestException;
import lab.soa.exceptions.IncorrectParamException;
import lab.soa.exceptions.ObjectNotFoundException;
import lab.soa.repositories.FlatRepository;
import lab.soa.util.flat.FlatToDtoFromEntityMapper;
import lab.soa.util.flat.FlatToEntityFromDtoCreateRequest;
import lab.soa.util.flat.FlatToEntityFromDtoUpdateRequest;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class FlatService {
    private final FlatRepository flatRepository;
    private final FlatTxService flatTxService;
    private final FlatToEntityFromDtoCreateRequest flatToEntityFromDtoCreateRequest;
    private final FlatToEntityFromDtoUpdateRequest flatToEntityFromDtoUpdateRequest;

    @Transactional
    public FlatResponseByIdDto create(FlatRequestCreateDto dto) {
        Flat flat = flatToEntityFromDtoCreateRequest.toEntityFromDto(dto);
        Flat savedFlat = flatRepository.save(flat);
        flatRepository.flush();
        return FlatToDtoFromEntityMapper.toByIdDtoFromEntity(savedFlat);
    }

    @Transactional(readOnly = true)
    public WrapperListFlatsResponseDto findAll(
        String filter,
        Pageable pageable
    ) {
        Specification<Flat> specification = Specification.unrestricted();

        Page<Flat> page = flatRepository.findAll(specification, pageable);
        List<FlatResponseDto> flatResponseDtos = new ArrayList<>();

        page.forEach(musicBand ->
            flatResponseDtos.add(
                FlatToDtoFromEntityMapper.toDtoFromEntity(musicBand)
            )
        );

        return WrapperListFlatsResponseDto.builder()
            .totalElements(page.getTotalElements())
            .totalPages(page.getTotalPages())
            .currentPage(page.getNumber())
            .pageSize(page.getNumberOfElements())
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

    @Transactional(readOnly = true)
    public FlatResponseByIdDto findById(Long id) {
        Flat flat = flatTxService.findByIdReturnsEntity(id);
        return FlatToDtoFromEntityMapper.toByIdDtoFromEntity(flat);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public FlatResponseByIdDto update(Long id, FlatRequestUpdateDto dto) {
        Flat flat = flatTxService.findByIdReturnsEntity(id);
        flat = flatToEntityFromDtoUpdateRequest.toEntityFromDto(
            dto,
            flat
        );
        Flat savedFlat = flatRepository.save(flat);
        flatRepository.flush();
        return FlatToDtoFromEntityMapper.toByIdDtoFromEntity(savedFlat);
    }

    @Transactional
    public void delete(Long id) {
        Flat flat = flatTxService.findByIdReturnsEntity(id);
        flatRepository.delete(flat);
        flatRepository.flush();
    }

    @Transactional(readOnly = true)
    public Flat findByIdReturnsEntity(Long id) {
        return flatTxService.findByIdReturnsEntity(id);
    }
}
