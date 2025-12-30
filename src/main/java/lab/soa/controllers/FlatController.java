package lab.soa.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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

import lab.soa.dto.requests.flat.FlatRequestCreateDto;
import lab.soa.dto.requests.flat.FlatRequestUpdateDto;
import lab.soa.dto.responses.flat.FlatResponseByIdDto;
import lab.soa.dto.responses.flat.FlatResponseDto;
import lab.soa.dto.responses.flat.WrapperListFlatsResponseDto;
import lab.soa.services.flat.FlatService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/flats")
@RequiredArgsConstructor
public class FlatController {
    private final FlatService flatService;

    @PostMapping(
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<FlatResponseByIdDto> create(@RequestBody FlatRequestCreateDto requestDto) {
        FlatResponseByIdDto responseDto = flatService.create(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping(
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<WrapperListFlatsResponseDto> getAll(
        @RequestParam(required = false) String filter,
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
            flatService.findAll(
                filter,
                pageable
            )
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOneFlatByFilter(
        @RequestParam(required = false) String houseName,
        @RequestParam(required = false) Integer houseYear,
        @RequestParam(required = false) Integer numberOfFlatsOnFloor
    ) {
        flatService.deleteOneFlatByFilter(
            houseName,
            houseYear,
            numberOfFlatsOnFloor
        );
        return ResponseEntity.noContent().build();
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<FlatResponseByIdDto> getById(@PathVariable Long id) {
        FlatResponseByIdDto dto = flatService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(
        value = "/{id}",
        consumes = MediaType.APPLICATION_XML_VALUE,
        produces = MediaType.APPLICATION_XML_VALUE
    )
    public ResponseEntity<FlatResponseByIdDto> update(
        @PathVariable Long id,
        @RequestBody FlatRequestUpdateDto requestDto
    ) {
        FlatResponseByIdDto responseDto = flatService.update(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        flatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
