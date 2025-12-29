package lab.is.controllers;

import java.net.URI;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lab.is.bd.entities.View;
import lab.is.dto.requests.flat.FlatRequestCreateDto;
import lab.is.dto.requests.flat.FlatRequestUpdateDto;
import lab.is.dto.responses.flat.FlatResponseDto;
import lab.is.dto.responses.flat.WrapperListFlatsResponseDto;
import lab.is.services.musicband.MusicBandService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/flats")
@RequiredArgsConstructor
public class FlatController {
    private static final String URI_RESOURCE = "/api/v1/music-bands";
    private final MusicBandService musicBandService;

    @GetMapping
    public ResponseEntity<WrapperListFlatsResponseDto> getAll(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) View genre,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) String bestAlbumName,
        @RequestParam(required = false) String studioName,
        @RequestParam(required = false) String studioAddress,
        @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
            musicBandService.findAll(
                name,
                genre,
                description,
                bestAlbumName,
                studioName,
                studioAddress,
                pageable
            )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlatResponseDto> getById(@PathVariable Long id) {
        FlatResponseDto dto = musicBandService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid FlatRequestCreateDto dto) {
        Long createdId = musicBandService.create(dto).getId();
        URI location = URI.create(URI_RESOURCE + "/" + createdId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
        @PathVariable Long id,
        @RequestBody @Valid FlatRequestUpdateDto dto
    ) {
        musicBandService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        musicBandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
