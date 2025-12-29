package lab.is.services.album;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.is.bd.entities.House;
import lab.is.dto.requests.house.HouseRequestUpdateDto;
import lab.is.dto.responses.house.HouseResponseDto;
import lab.is.dto.responses.house.WrapperListAlbumResponseDto;
import lab.is.exceptions.NestedObjectIsUsedException;
import lab.is.repositories.AlbumRepository;
import lab.is.util.AlbumMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumTxService albumTxService;

    @Transactional(readOnly = true)
    public WrapperListAlbumResponseDto findAll(Pageable pageable) {
        Page<House> page = albumRepository.findAll(pageable);
        List<HouseResponseDto> albumResponseDtos = new ArrayList<>();

        page.forEach(album ->
            albumResponseDtos.add(
                AlbumMapper.toDtoFromEntity(album)
            )
        );

        return WrapperListAlbumResponseDto.builder()
            .totalElements(page.getTotalElements())
            .totalPages(page.getTotalPages())
            .currentPage(page.getNumber())
            .pageSize(page.getNumberOfElements())
            .albums(albumResponseDtos)
            .build();
    }

    @Transactional(readOnly = true)
    public HouseResponseDto findById(Long id) {
        House album = albumTxService.findByIdReturnsEntity(id);
        return AlbumMapper.toDtoFromEntity(album);
    }

    @Transactional
    public House create(String name, int length) {
        House album = House.builder()
            .name(name)
            .length(length)
            .build();
        House savedAlbum = albumRepository.save(album);
        albumRepository.flush();
        return savedAlbum;
    }

    @Transactional
    public House update(long id, HouseRequestUpdateDto dto) {
        House album = albumTxService.findByIdReturnsEntity(id);
        House updatedAlbum = album.toBuilder()
            .name(dto.getName())
            .length(dto.getLength())
            .build();
        House savedAlbum = albumRepository.save(updatedAlbum);
        albumRepository.flush();
        return savedAlbum;
    }

    @Transactional
    public void delete(Long id) {
        House album = albumTxService.findByIdReturnsEntity(id);
        if (isUsedNestedObject(album)) {
            throw new NestedObjectIsUsedException(
                String.format(
                    "Альбом %s с id: %s не может быть удален, так как связан с другими объектами",
                    album.getName(),
                    id
                )
            );
        }
        albumRepository.delete(album);
    }

    public House findByIdReturnsEntity(Long id) {
        return albumTxService.findByIdReturnsEntity(id);
    }

    private boolean isUsedNestedObject(House album) {
        return !album.getFlats().isEmpty();
    }
}
