package lab.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class FlatControllerGetListTest extends SpringBootApplicationTest {
    @Test
    void getEmptyListFlats_ReturnsResponseWithStatusOk() throws Exception {
        setupEmptyDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith("application/json"),
                content().xml("""
                    <?xml version="1.0" encoding="UTF-8"?>
                    <FlatsPage>
                        <flats></flats>
                        <page>0</page>
                        <totalElements>0</totalElements>
                        <totalPages>0</totalPages>
                        <currentPage>0</currentPage>
                        <pageSize>0</pageSize>
                    </FlatsPage>
                """)
            );
    }

    @Test
    void getListFlats_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByPageParams_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "-id")
            .param("page", "1")
            .param("size", "2");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id")
            .param("page", "0")
            .param("size", "2")
            .param("filter", "coordinates.x[eq]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterNotEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id")
            .param("page", "0")
            .param("size", "2")
            .param("filter", "coordinates.x[ne]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterGreaterThan_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id")
            .param("page", "0")
            .param("size", "2")
            .param("filter", "coordinates.x[gt]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterLessThan_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id")
            .param("page", "0")
            .param("size", "2")
            .param("filter", "coordinates.x[lt]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterGreaterThanEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id")
            .param("page", "0")
            .param("size", "2")
            .param("filter", "coordinates.x[gte]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterLessThanEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id")
            .param("page", "0")
            .param("size", "2")
            .param("filter", "coordinates.x[lte]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterInterval_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id")
            .param("page", "0")
            .param("size", "2")
            .param("filter", "coordinates.x[interval]:1.1,2.0");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterRange_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id")
            .param("page", "0")
            .param("size", "2")
            .param("filter", "coordinates.x[range]:1.1,2.0");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFiltersEqualsAndGreaterThan_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id")
            .param("page", "0")
            .param("size", "2")
            .param("filter", "coordinates.x[eq]:1.1;coordinates.y[gt]:100");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),
                xpath("/FlatsPage/flats/Flat[1]/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("First Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/FlatsPage/totalElements").string("1"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("1")
            );
    }
}

