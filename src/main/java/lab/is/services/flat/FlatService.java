package lab.is.services.flat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import lab.is.bd.entities.Flat;
import lab.is.dto.requests.flat.FlatRequestCreateDto;
import lab.is.dto.requests.flat.FlatRequestUpdateDto;
import lab.is.dto.responses.flat.FlatResponseByIdDto;
import lab.is.dto.responses.flat.FlatResponseDto;
import lab.is.dto.responses.flat.WrapperListFlatsResponseDto;
import lab.is.repositories.FlatRepository;
import lab.is.util.flat.FlatToDtoFromEntityMapper;
import lab.is.util.flat.FlatToEntityFromDtoCreateRequest;
import lab.is.util.flat.FlatToEntityFromDtoUpdateRequest;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class FlatService {
    private final FlatRepository flatRepository;
    private final FlatTxService flatTxService;
    private final FlatToEntityFromDtoCreateRequest flatToEntityFromDtoCreateRequest;
    private final FlatToEntityFromDtoUpdateRequest flatToEntityFromDtoUpdateRequest;

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

    @Transactional(readOnly = true)
    public FlatResponseByIdDto findById(Long id) {
        Flat flat = flatTxService.findByIdReturnsEntity(id);
        return FlatToDtoFromEntityMapper.toByIdDtoFromEntity(flat);
    }

    @Transactional
    public FlatResponseByIdDto create(FlatRequestCreateDto dto) {
        Flat flat = flatToEntityFromDtoCreateRequest.toEntityFromDto(dto);
        Flat savedFlat = flatRepository.save(flat);
        flatRepository.flush();
        return FlatToDtoFromEntityMapper.toByIdDtoFromEntity(savedFlat);
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
