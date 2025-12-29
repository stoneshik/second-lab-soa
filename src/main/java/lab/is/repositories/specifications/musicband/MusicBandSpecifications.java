package lab.is.repositories.specifications.musicband;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import lab.is.bd.entities.Flat;
import lab.is.bd.entities.View;
import lab.is.repositories.specifications.MySpecification;

@Component
public final class MusicBandSpecifications extends MySpecification<Flat> {
    public Specification<Flat> nameLike(String fieldValue) {
        return fieldStringValueLike(
            MusicBandFieldNames.NAME,
            fieldValue
        );
    }

    public Specification<Flat> genreEquals(View fieldValue) {
        if (fieldValue == null) return null;
        return fieldValueEquals(
            MusicBandFieldNames.GENRE,
            fieldValue.name()
        );
    }

    public Specification<Flat> descriptionLike(String fieldValue) {
        return fieldStringValueLike(
            MusicBandFieldNames.DESCRIPTION,
            fieldValue
        );
    }

    public Specification<Flat> bestAlbumNameLike(String fieldValue) {
        return fieldStringValueFromEntityWithJoinLike(
            MusicBandFieldNames.BEST_ALBUM,
            MusicBandFieldNames.BEST_ALBUM_NAME,
            fieldValue
        );
    }

    public Specification<Flat> studioNameLike(String fieldValue) {
        return fieldStringValueFromEntityWithJoinLike(
            MusicBandFieldNames.STUDIO,
            MusicBandFieldNames.STUDIO_NAME,
            fieldValue
        );
    }

    public Specification<Flat> studioAddressLike(String fieldValue) {
        return fieldStringValueFromEntityWithJoinLike(
            MusicBandFieldNames.STUDIO,
            MusicBandFieldNames.STUDIO_ADDRESS,
            fieldValue
        );
    }
}

