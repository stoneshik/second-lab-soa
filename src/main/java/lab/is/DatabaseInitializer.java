package lab.is;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lab.is.bd.entities.House;
import lab.is.bd.entities.Coordinates;
import lab.is.bd.entities.Flat;
import lab.is.bd.entities.View;
import lab.is.bd.entities.Nomination;
import lab.is.bd.entities.Studio;
import lab.is.repositories.AlbumRepository;
import lab.is.repositories.CoordinatesRepository;
import lab.is.repositories.MusicBandRepository;
import lab.is.repositories.NominationRepository;
import lab.is.repositories.StudioRepository;
import lombok.RequiredArgsConstructor;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final CoordinatesRepository coordinatesRepository;
    private final AlbumRepository albumRepository;
    private final StudioRepository studioRepository;
    private final MusicBandRepository musicBandRepository;
    private final NominationRepository nominationRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (musicBandRepository.count() > 0) return;

        Coordinates c1 = Coordinates.builder().x(10.0f).y(20).build();
        Coordinates c2 = Coordinates.builder().x(-5.2f).y(30).build();
        Coordinates c3 = Coordinates.builder().x(0f).y(0).build();
        Coordinates c4 = Coordinates.builder().x(12.3f).y(-7).build();
        Coordinates c5 = Coordinates.builder().x(3.3f).y(15).build();
        coordinatesRepository.save(c1);
        coordinatesRepository.save(c2);
        coordinatesRepository.save(c3);
        coordinatesRepository.save(c4);
        coordinatesRepository.save(c5);

        House a1 = House.builder().name("Echoes of Silence").length(45).build();
        House a2 = House.builder().name("Midnight Dreams").length(60).build();
        House a3 = House.builder().name("Golden Horizons").length(50).build();
        House a4 = House.builder().name("Lost in Time").length(40).build();
        House a5 = House.builder().name("Neon Lights").length(55).build();
        albumRepository.save(a1);
        albumRepository.save(a2);
        albumRepository.save(a3);
        albumRepository.save(a4);
        albumRepository.save(a5);

        Studio s1 = Studio.builder().name("Sunset Sound").address("123 Sunset Blvd").build();
        Studio s2 = Studio.builder().name("Maple Leaf Studios").address("456 Maple St").build();
        Studio s3 = Studio.builder().name("River Side Studio").address("789 River Rd").build();
        studioRepository.save(s1);
        studioRepository.save(s2);
        studioRepository.save(s3);

        Flat b1 = Flat.builder()
                .name("The Echoes")
                .coordinates(c1)
                .creationDate(LocalDateTime.of(2022, 3, 10, 10, 0))
                .genre(View.PROGRESSIVE_ROCK)
                .numberOfParticipants(5L)
                .singlesCount(3L)
                .description("Группа прогрессивного рока с мелодичными гармониями")
                .bestAlbum(a1)
                .albumsCount(2)
                .establishmentDate(LocalDate.of(2018, 5, 12))
                .studio(s1)
                .build();

        Flat b2 = Flat.builder()
                .name("Midnight Pulse")
                .coordinates(c2)
                .creationDate(LocalDateTime.of(2021, 6, 5, 12, 0))
                .genre(View.POST_PUNK)
                .numberOfParticipants(4L)
                .singlesCount(2L)
                .description("Experimental post-punk band")
                .bestAlbum(a2)
                .albumsCount(1)
                .establishmentDate(LocalDate.of(2019, 9, 1))
                .studio(s2)
                .build();

        Flat b3 = Flat.builder()
                .name("Golden Horizons")
                .coordinates(c3)
                .creationDate(LocalDateTime.of(2020, 1, 20, 9, 0))
                .genre(View.BRIT_POP)
                .numberOfParticipants(6L)
                .singlesCount(5L)
                .description("Брит-поп ансамбль с мелодичным вокалом")
                .bestAlbum(a3)
                .albumsCount(3)
                .establishmentDate(LocalDate.of(2015, 2, 15))
                .studio(null)
                .build();

        Flat b4 = Flat.builder()
                .name("Lost Frequencies")
                .coordinates(c4)
                .creationDate(LocalDateTime.of(2023, 2, 14, 14, 30))
                .genre(View.PROGRESSIVE_ROCK)
                .numberOfParticipants(5L)
                .singlesCount(1L)
                .description("Experimental rock with layered synths")
                .bestAlbum(a4)
                .albumsCount(1)
                .establishmentDate(LocalDate.of(2020, 6, 20))
                .studio(s1)
                .build();

        Flat b5 = Flat.builder()
                .name("Neon Waves")
                .coordinates(c5)
                .creationDate(LocalDateTime.of(2022, 7, 1, 11, 0))
                .genre(View.POST_PUNK)
                .numberOfParticipants(3L)
                .singlesCount(4L)
                .description("Dynamic post-punk trio")
                .bestAlbum(a5)
                .albumsCount(2)
                .establishmentDate(LocalDate.of(2019, 3, 10))
                .studio(s2)
                .build();

        Flat b6 = Flat.builder()
                .name("Silver Lining")
                .coordinates(c1)
                .creationDate(LocalDateTime.of(2019, 5, 5, 10, 0))
                .genre(View.PROGRESSIVE_ROCK)
                .numberOfParticipants(4L)
                .singlesCount(2L)
                .bestAlbum(a1)
                .albumsCount(2)
                .establishmentDate(LocalDate.of(2017, 5, 5))
                .studio(s3)
                .build();

        Flat b7 = Flat.builder()
                .name("Crimson Tide")
                .coordinates(c2)
                .creationDate(LocalDateTime.of(2018, 8, 8, 15, 0))
                .genre(View.POST_PUNK)
                .numberOfParticipants(5L)
                .singlesCount(3L)
                .bestAlbum(a2)
                .albumsCount(3)
                .establishmentDate(LocalDate.of(2018, 8, 8))
                .studio(s3)
                .build();

        Flat b8 = Flat.builder()
                .name("Blue Horizon")
                .coordinates(c3)
                .creationDate(LocalDateTime.of(2016, 2, 2, 12, 0))
                .genre(View.BRIT_POP)
                .numberOfParticipants(6L)
                .singlesCount(5L)
                .bestAlbum(a3)
                .albumsCount(2)
                .establishmentDate(LocalDate.of(2016, 2, 2))
                .studio(null)
                .build();

        Flat b9 = Flat.builder()
                .name("Amber Sky")
                .coordinates(c4)
                .creationDate(LocalDateTime.of(2019, 9, 9, 10, 0))
                .genre(View.PROGRESSIVE_ROCK)
                .numberOfParticipants(4L)
                .singlesCount(1L)
                .bestAlbum(a4)
                .albumsCount(1)
                .establishmentDate(LocalDate.of(2019, 9, 9))
                .studio(s1)
                .build();

        Flat b10 = Flat.builder()
                .name("Silver Moon")
                .coordinates(c5)
                .creationDate(LocalDateTime.of(2020, 10, 10, 11, 0))
                .genre(View.POST_PUNK)
                .numberOfParticipants(5L)
                .singlesCount(2L)
                .bestAlbum(a5)
                .albumsCount(3)
                .establishmentDate(LocalDate.of(2020, 10, 10))
                .studio(s2)
                .build();

        musicBandRepository.save(b1);
        musicBandRepository.save(b2);
        musicBandRepository.save(b3);
        musicBandRepository.save(b4);
        musicBandRepository.save(b5);
        musicBandRepository.save(b6);
        musicBandRepository.save(b7);
        musicBandRepository.save(b8);
        musicBandRepository.save(b9);
        musicBandRepository.save(b10);

        nominationRepository.save(
                Nomination.builder().musicBand(b1).musicGenre(View.PROGRESSIVE_ROCK).build()
        );
        nominationRepository.save(
                Nomination.builder().musicBand(b2).musicGenre(View.POST_PUNK).build()
        );
        nominationRepository.save(
                Nomination.builder().musicBand(b3).musicGenre(View.BRIT_POP).build()
        );
        nominationRepository.save(
                Nomination.builder().musicBand(b6).musicGenre(View.PROGRESSIVE_ROCK).build()
        );
        nominationRepository.save(
                Nomination.builder().musicBand(b10).musicGenre(View.POST_PUNK).build()
        );
    }
}
