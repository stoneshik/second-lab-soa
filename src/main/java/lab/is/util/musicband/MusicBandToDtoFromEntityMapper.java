package lab.is.util.musicband;

import org.springframework.stereotype.Service;

import lab.is.bd.entities.Flat;
import lab.is.dto.responses.flat.FlatResponseDto;
import lab.is.util.AlbumMapper;
import lab.is.util.CoordinatesMapper;
import lab.is.util.StudioMapper;

@Service
public class MusicBandToDtoFromEntityMapper {
    private MusicBandToDtoFromEntityMapper() {}

    public static FlatResponseDto toDtoFromEntity(Flat entity) {
        return FlatResponseDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .coordinates(
                CoordinatesMapper.toDtoFromEntity(
                    entity.getCoordinates()
                )
            )
            .creationDate(entity.getCreationDate())
            .genre(entity.getGenre())
            .numberOfParticipants(entity.getNumberOfParticipants())
            .singlesCount(entity.getSinglesCount())
            .description(entity.getDescription())
            .bestAlbum(
                (entity.getBestAlbum() == null) ? null :
                    AlbumMapper.toDtoFromEntity(entity.getBestAlbum())
            )
            .albumsCount(entity.getAlbumsCount())
            .establishmentDate(entity.getEstablishmentDate())
            .studio(
                (entity.getStudio() == null) ? null :
                    StudioMapper.toDtoFromEntity(entity.getStudio())
            )
            .build();
    }
}
