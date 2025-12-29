package lab.is.services.musicband;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.is.bd.entities.Coordinates;
import lab.is.bd.entities.Flat;
import lab.is.bd.entities.View;
import lab.is.dto.requests.flat.FlatRequestCreateDto;
import lab.is.dto.requests.flat.FlatRequestUpdateDto;
import lab.is.dto.responses.flat.FlatResponseDto;
import lab.is.dto.responses.flat.WrapperListFlatsResponseDto;
import lab.is.repositories.MusicBandRepository;
import lab.is.repositories.specifications.musicband.MusicBandSpecifications;
import lab.is.util.musicband.MusicBandToDtoFromEntityMapper;
import lab.is.util.musicband.MusicBandToEntityFromDtoCreateRequest;
import lab.is.util.musicband.MusicBandToEntityFromDtoUpdateRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MusicBandService {
    private final MusicBandRepository musicBandRepository;
    private final MusicBandTxService musicBandTxService;
    private final MusicBandSpecifications musicBandSpecifications;
    private final MusicBandToEntityFromDtoCreateRequest musicBandToEntityFromDtoCreateRequest;
    private final MusicBandToEntityFromDtoUpdateRequest musicBandToEntityFromDtoUpdateRequest;

    @Transactional(readOnly = true)
    public WrapperListFlatsResponseDto findAll(
        String name,
        View genre,
        String description,
        String bestAlbumName,
        String studioName,
        String studioAddress,
        Pageable pageable
    ) {
        Specification<Flat> specification = Specification.unrestricted();

        specification = specification.and(musicBandSpecifications.nameLike(name));
        specification = specification.and(musicBandSpecifications.genreEquals(genre));
        specification = specification.and(musicBandSpecifications.descriptionLike(description));
        specification = specification.and(musicBandSpecifications.bestAlbumNameLike(bestAlbumName));
        specification = specification.and(musicBandSpecifications.studioNameLike(studioName));
        specification = specification.and(musicBandSpecifications.studioAddressLike(studioAddress));

        Page<Flat> page = musicBandRepository.findAll(specification, pageable);
        List<FlatResponseDto> musicBandResponseDtos = new ArrayList<>();

        page.forEach(musicBand ->
            musicBandResponseDtos.add(
                MusicBandToDtoFromEntityMapper.toDtoFromEntity(musicBand)
            )
        );

        return WrapperListFlatsResponseDto.builder()
            .totalElements(page.getTotalElements())
            .totalPages(page.getTotalPages())
            .currentPage(page.getNumber())
            .pageSize(page.getNumberOfElements())
            .musicBands(musicBandResponseDtos)
            .build();
    }

    @Transactional(readOnly = true)
    public FlatResponseDto findById(Long id) {
        Flat musicBand = musicBandTxService.findByIdReturnsEntity(id);
        return MusicBandToDtoFromEntityMapper.toDtoFromEntity(musicBand);
    }

    @Transactional
    public Flat create(FlatRequestCreateDto dto) {
        Flat musicBand = musicBandToEntityFromDtoCreateRequest.toEntityFromDto(dto);
        Flat savedMusicBand = musicBandRepository.save(musicBand);
        musicBandRepository.flush();
        return savedMusicBand;
    }

    @Transactional
    public Flat update(long id, FlatRequestUpdateDto dto) {
        Flat musicBand = musicBandTxService.findByIdReturnsEntity(id);
        musicBand = musicBandToEntityFromDtoUpdateRequest.toEntityFromDto(
            dto,
            musicBand
        );
        Flat savedMusicBand = musicBandRepository.save(musicBand);
        musicBandRepository.flush();
        return savedMusicBand;
    }

    @Transactional
    public void delete(Long id) {
        Flat musicBand = musicBandTxService.findByIdReturnsEntity(id);
        Coordinates coordinates = musicBand.getCoordinates();
        coordinates.removeMusicBand(musicBand);
        musicBandRepository.delete(musicBand);
        musicBandRepository.flush();
    }

    @Transactional(readOnly = true)
    public Flat findByIdReturnsEntity(Long id) {
        return musicBandTxService.findByIdReturnsEntity(id);
    }
}
