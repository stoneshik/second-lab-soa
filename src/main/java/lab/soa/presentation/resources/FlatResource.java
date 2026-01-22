package lab.soa.presentation.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lab.soa.domain.models.BalconyType;
import lab.soa.domain.models.PriceType;
import lab.soa.domain.models.SortType;
import lab.soa.domain.models.TransportType;
import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.presentation.dto.requests.flat.FlatRequestCreateDto;
import lab.soa.presentation.dto.requests.flat.FlatRequestUpdateDto;
import lab.soa.presentation.dto.responses.LongValueResponseDto;
import lab.soa.presentation.dto.responses.flat.FlatGroupsByHeightResponseDto;
import lab.soa.presentation.dto.responses.flat.FlatResponseByIdDto;
import lab.soa.presentation.dto.responses.flat.WrapperListFlatsResponseDto;
import lab.soa.presentation.params.sort.SortParam;
import lab.soa.presentation.params.sort.SortParamParser;
import lab.soa.service.filters.flat.FlatFilterParam;
import lab.soa.service.filters.flat.StringToFlatFilterParamConverter;
import lab.soa.service.services.flat.FlatService;

@Stateless
@Path("/flats")
public class FlatResource {
    private static final Logger logger = LoggerFactory.getLogger(FlatResource.class);

    @Context
    private HttpServletRequest request;

    @Inject
    private FlatService flatService;

    @Inject
    private StringToFlatFilterParamConverter stringToFlatFilterParamConverter;

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response create(FlatRequestCreateDto requestDto) {
        FlatResponseByIdDto responseDto = flatService.create(requestDto);
        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAll(
        @QueryParam("filter") List<String> filterStrings,
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("10") int size,
        @QueryParam("sort") List<String> sortStrings
    ) {
        if (page < 0) {
            throw new IncorrectParamException("Page param must be greater or equals 0");
        }
        if (size < 0) {
            throw new IncorrectParamException("Size param must be greater or equals 0");
        }
        List<FlatFilterParam> filterParams = filterStrings != null ?
            filterStrings.stream()
                .map(filterString -> stringToFlatFilterParamConverter.convert(filterString))
                .collect(Collectors.toList()) : null;
        List<SortParam> sortParams = SortParamParser.parseSortStrings(sortStrings);
        WrapperListFlatsResponseDto result = flatService.findAll(
            filterParams,
            page,
            size,
            sortParams
        );
        return Response.ok(result).build();
    }

    @DELETE
    public Response deleteOneFlatByFilter(
        @QueryParam("houseName") String houseName,
        @QueryParam("houseYear") Integer houseYear,
        @QueryParam("numberOfFlatsOnFloor") Integer numberOfFlatsOnFloor
    ) {
        flatService.deleteOneFlatByFilter(
            houseName,
            houseYear,
            numberOfFlatsOnFloor
        );
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getById(@PathParam("id") Long id) {
        FlatResponseByIdDto dto = flatService.findById(id);
        return Response.ok(dto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response update(
        @PathParam("id") Long id,
        FlatRequestUpdateDto requestDto
    ) {
        FlatResponseByIdDto responseDto = flatService.update(id, requestDto);
        return Response.ok(responseDto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        flatService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/sum/height")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAmountOfHeights() {
        LongValueResponseDto dto = flatService.getAmountOfHeights();
        return Response.ok(dto).build();
    }

    @GET
    @Path("/group-by/height")
    @Produces(MediaType.APPLICATION_XML)
    public Response getGroupsByHeight() {
        FlatGroupsByHeightResponseDto dto = flatService.getGroupsByHeight();
        return Response.ok(dto).build();
    }

    @GET
    @Path("/find-with-balcony/{priceType}/{balconyType}")
    public Response findWithBalcony(
        @PathParam("priceType") PriceType priceType,
        @PathParam("balconyType") BalconyType balconyType
    ) {
        logRequest();
        FlatResponseByIdDto flatDto = flatService.findWithBalcony(
            priceType,
            balconyType
        );
        return Response.ok(flatDto).build();
    }

    @GET
    @Path("/get-ordered-by-time-to-metro/{transportType}/{sortType}")
    public Response getOrderedByTimeToMetro(
        @PathParam("transportType") TransportType transportType,
        @PathParam("sortType") SortType sortType,
        @QueryParam("page") @DefaultValue("0") Integer page,
        @QueryParam("size") @DefaultValue("10") Integer size
    ) {
        logRequest();
        WrapperListFlatsResponseDto result = flatService.getFlatsOrderedByTimeToMetro(
            transportType,
            sortType,
            page,
            size
        );
        return Response.ok(result).build();
    }

    private void logRequest() {
        String scheme = request.getScheme();
        String protocol = request.getProtocol();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        boolean isSecure = request.isSecure();
        logger.info(
            "Request: scheme={}, protocol={}, method={}, uri={}, isSecure={}",
            scheme,
            protocol,
            method,
            uri,
            isSecure
        );
    }
}
