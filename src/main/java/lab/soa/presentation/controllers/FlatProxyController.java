package lab.soa.presentation.controllers;

import java.util.Enumeration;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import lab.soa.domain.models.BalconyType;
import lab.soa.domain.models.PriceType;
import lab.soa.domain.models.SortType;
import lab.soa.domain.models.TransportType;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/flats")
@RequiredArgsConstructor
public class FlatProxyController {
    private final RestTemplate restTemplate;

    @GetMapping("/find-with-balcony/{priceType}/{balconyType}")
    public ResponseEntity<?> findWithBalcony(
        @PathVariable PriceType priceType,
        @PathVariable BalconyType balconyType,
        HttpServletRequest originalRequest
    ) {
        String targetUrl = String.format(
            "/api/v1/flats/find-with-balcony/%s/%s",
            priceType.name(),
            balconyType.name()
        );
        HttpHeaders headers = copyHeaders(originalRequest);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
            targetUrl,
            HttpMethod.GET,
            entity,
            String.class
        );
        return ResponseEntity
            .status(response.getStatusCode())
            .headers(response.getHeaders())
            .body(response.getBody());
    }

    @GetMapping("/get-ordered-by-time-to-metro/{transportType}/{sortType}")
    public ResponseEntity<?> getOrderedByTimeToMetro(
        @PathVariable TransportType transportType,
        @PathVariable SortType sortType,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        HttpServletRequest originalRequest
    ) {
        String baseUrl = String.format(
            "/api/v1/flats/get-ordered-by-time-to-metro/%s/%s",
            transportType.name(),
            sortType.name()
        );
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
            .queryParam("page", page)
            .queryParam("size", size);
        originalRequest.getParameterMap().forEach((key, values) -> {
            if (!key.equals("page") && !key.equals("size")) {
                for (String value : values) {
                    builder.queryParam(key, value);
                }
            }
        });
        String targetUrl = builder.toUriString();
        HttpHeaders headers = copyHeaders(originalRequest);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
            targetUrl,
            HttpMethod.GET,
            entity,
            String.class
        );
        return ResponseEntity
            .status(response.getStatusCode())
            .headers(response.getHeaders())
            .body(response.getBody());
    }

    private HttpHeaders copyHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            while (headerValues.hasMoreElements()) {
                headers.add(headerName, headerValues.nextElement());
            }
        }
        return headers;
    }
}
