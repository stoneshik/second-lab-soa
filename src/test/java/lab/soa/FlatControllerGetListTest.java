package lab.soa;

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
                content().contentType(MediaType.APPLICATION_XML),
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
                xpath("/FlatsPage/flats/Flat[1]/coordinates/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),

                xpath("/FlatsPage/flats/Flat[2]/id").string("2"),
                xpath("/FlatsPage/flats/Flat[2]/name").string("Second Flat"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/id").string("2"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/x").number(2.0),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/y").string("100"),
                xpath("/FlatsPage/flats/Flat[2]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[2]/area").string("2"),
                xpath("/FlatsPage/flats/Flat[2]/numberOfRooms").string("10"),
                xpath("/FlatsPage/flats/Flat[2]/height").string("2"),
                xpath("/FlatsPage/flats/Flat[2]/view").string("BAD"),
                xpath("/FlatsPage/flats/Flat[2]/transport").string("ENOUGH"),
                xpath("/FlatsPage/flats/Flat[2]/house/id").string("1"),
                xpath("/FlatsPage/flats/Flat[2]/house/name").string("Second House"),
                xpath("/FlatsPage/flats/Flat[2]/house/year").string("2001"),
                xpath("/FlatsPage/flats/Flat[2]/house/numberOfFlatsOnFloor").string("12"),

                xpath("/FlatsPage/flats/Flat[3]/id").string("3"),
                xpath("/FlatsPage/flats/Flat[3]/name").string("Third Flat"),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/id").string("3"),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/x").number(100.0),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/y").string("200"),
                xpath("/FlatsPage/flats/Flat[3]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[3]/area").string("3"),
                xpath("/FlatsPage/flats/Flat[3]/numberOfRooms").string("3"),
                xpath("/FlatsPage/flats/Flat[3]/height").string("3"),
                xpath("/FlatsPage/flats/Flat[3]/view").string("YARD"),
                xpath("/FlatsPage/flats/Flat[3]/transport").string("NONE"),
                xpath("/FlatsPage/flats/Flat[3]/house/id").string("3"),
                xpath("/FlatsPage/flats/Flat[3]/house/name").string("Third House"),
                xpath("/FlatsPage/flats/Flat[3]/house/year").string("2002"),
                xpath("/FlatsPage/flats/Flat[3]/house/numberOfFlatsOnFloor").string("8"),

                xpath("/FlatsPage/flats/Flat[4]/id").string("4"),
                xpath("/FlatsPage/flats/Flat[4]/name").string("Fourth Flat"),
                xpath("/FlatsPage/flats/Flat[4]/coordinates/id").string("4"),
                xpath("/FlatsPage/flats/Flat[4]/coordinates/x").number(110.1),
                xpath("/FlatsPage/flats/Flat[4]/coordinates/y").string("1234"),
                xpath("/FlatsPage/flats/Flat[4]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[4]/area").string("4"),
                xpath("/FlatsPage/flats/Flat[4]/numberOfRooms").string("4"),
                xpath("/FlatsPage/flats/Flat[4]/height").string("4"),
                xpath("/FlatsPage/flats/Flat[4]/view").string("GOOD"),
                xpath("/FlatsPage/flats/Flat[4]/transport").string("NORMAL"),
                xpath("/FlatsPage/flats/Flat[4]/house/id").string("4"),
                xpath("/FlatsPage/flats/Flat[4]/house/name").string("Fourth House"),
                xpath("/FlatsPage/flats/Flat[4]/house/year").string("2003"),
                xpath("/FlatsPage/flats/Flat[4]/house/numberOfFlatsOnFloor").string("1"),

                xpath("/FlatsPage/totalElements").string("4"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("4")
            );
    }

    @Test
    void getListFlatsByPageParams_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,desc")
            .param("page", "1")
            .param("size", "2");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),

                xpath("/FlatsPage/flats/Flat[1]/id").string("4"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("Fourth Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/id").string("4"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(110.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("1234"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("4"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("4"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("4"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("GOOD"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("NORMAL"),
                xpath("/FlatsPage/flats/Flat[1]/house/id").string("4"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("Fourth House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2003"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("1"),

                xpath("/FlatsPage/flats/Flat[2]/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/name").string("Third Flat"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/x").number(100.0),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/y").string("200"),
                xpath("/FlatsPage/flats/Flat[2]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[2]/area").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/numberOfRooms").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/height").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/view").string("YARD"),
                xpath("/FlatsPage/flats/Flat[2]/transport").string("NONE"),
                xpath("/FlatsPage/flats/Flat[2]/house/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/house/name").string("Third House"),
                xpath("/FlatsPage/flats/Flat[2]/house/year").string("2002"),
                xpath("/FlatsPage/flats/Flat[2]/house/numberOfFlatsOnFloor").string("8"),

                xpath("/FlatsPage/totalElements").string("4"),
                xpath("/FlatsPage/totalPages").string("2"),
                xpath("/FlatsPage/currentPage").string("1"),
                xpath("/FlatsPage/pageSize").string("2")
            );
    }

    @Test
    void getListFlatsByFilterEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
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
                xpath("/FlatsPage/flats/Flat[1]/coordinates/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(1.1),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("123"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("STREET"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("FEW"),
                xpath("/FlatsPage/flats/Flat[1]/house/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("First House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2000"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("9"),


                xpath("/FlatsPage/totalElements").string("4"),
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
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x[ne]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),

                xpath("/FlatsPage/flats/Flat[1]/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("Second Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(2.0),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("100"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("BAD"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("ENOUGH"),
                xpath("/FlatsPage/flats/Flat[1]/house/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("Second House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2001"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("12"),

                xpath("/FlatsPage/flats/Flat[2]/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/name").string("Third Flat"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/x").number(100.0),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/y").string("200"),
                xpath("/FlatsPage/flats/Flat[2]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[2]/area").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/numberOfRooms").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/height").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/view").string("YARD"),
                xpath("/FlatsPage/flats/Flat[2]/transport").string("NONE"),
                xpath("/FlatsPage/flats/Flat[2]/house/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/house/name").string("Third House"),
                xpath("/FlatsPage/flats/Flat[2]/house/year").string("2002"),
                xpath("/FlatsPage/flats/Flat[2]/house/numberOfFlatsOnFloor").string("8"),

                xpath("/FlatsPage/flats/Flat[3]/id").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/name").string("Fourth Flat"),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/id").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/x").number(110.1),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/y").string("1234"),
                xpath("/FlatsPage/flats/Flat[3]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[3]/area").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/numberOfRooms").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/height").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/view").string("GOOD"),
                xpath("/FlatsPage/flats/Flat[3]/transport").string("NORMAL"),
                xpath("/FlatsPage/flats/Flat[3]/house/id").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/house/name").string("Fourth House"),
                xpath("/FlatsPage/flats/Flat[3]/house/year").string("2003"),
                xpath("/FlatsPage/flats/Flat[3]/house/numberOfFlatsOnFloor").string("1"),

                xpath("/FlatsPage/totalElements").string("4"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("3")
            );
    }

    @Test
    void getListFlatsByFilterGreaterThan_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x[gt]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),

                xpath("/FlatsPage/flats/Flat[1]/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("Second Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(2.0),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("100"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("BAD"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("ENOUGH"),
                xpath("/FlatsPage/flats/Flat[1]/house/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("Second House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2001"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("12"),

                xpath("/FlatsPage/flats/Flat[2]/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/name").string("Third Flat"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/x").number(100.0),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/y").string("200"),
                xpath("/FlatsPage/flats/Flat[2]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[2]/area").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/numberOfRooms").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/height").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/view").string("YARD"),
                xpath("/FlatsPage/flats/Flat[2]/transport").string("NONE"),
                xpath("/FlatsPage/flats/Flat[2]/house/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/house/name").string("Third House"),
                xpath("/FlatsPage/flats/Flat[2]/house/year").string("2002"),
                xpath("/FlatsPage/flats/Flat[2]/house/numberOfFlatsOnFloor").string("8"),

                xpath("/FlatsPage/flats/Flat[3]/id").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/name").string("Fourth Flat"),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/id").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/x").number(110.1),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/y").string("1234"),
                xpath("/FlatsPage/flats/Flat[3]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[3]/area").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/numberOfRooms").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/height").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/view").string("GOOD"),
                xpath("/FlatsPage/flats/Flat[3]/transport").string("NORMAL"),
                xpath("/FlatsPage/flats/Flat[3]/house/id").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/house/name").string("Fourth House"),
                xpath("/FlatsPage/flats/Flat[3]/house/year").string("2003"),
                xpath("/FlatsPage/flats/Flat[3]/house/numberOfFlatsOnFloor").string("1"),

                xpath("/FlatsPage/totalElements").string("4"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("4")
            );
    }

    @Test
    void getListFlatsByFilterLessThan_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x[lt]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                content().xml("""
                    <?xml version="1.0" encoding="UTF-8"?>
                    <FlatsPage>
                        <flats></flats>
                        <page>0</page>
                        <totalElements>4</totalElements>
                        <totalPages>0</totalPages>
                        <currentPage>0</currentPage>
                        <pageSize>0</pageSize>
                    </FlatsPage>
                """)
            );
    }

    @Test
    void getListFlatsByFilterGreaterThanEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x[gte]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),

                xpath("/FlatsPage/flats/Flat[1]/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("Second Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(2.0),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("100"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("BAD"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("ENOUGH"),
                xpath("/FlatsPage/flats/Flat[1]/house/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("Second House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2001"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("12"),

                xpath("/FlatsPage/flats/Flat[2]/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/name").string("Third Flat"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/x").number(100.0),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/y").string("200"),
                xpath("/FlatsPage/flats/Flat[2]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[2]/area").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/numberOfRooms").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/height").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/view").string("YARD"),
                xpath("/FlatsPage/flats/Flat[2]/transport").string("NONE"),
                xpath("/FlatsPage/flats/Flat[2]/house/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/house/name").string("Third House"),
                xpath("/FlatsPage/flats/Flat[2]/house/year").string("2002"),
                xpath("/FlatsPage/flats/Flat[2]/house/numberOfFlatsOnFloor").string("8"),

                xpath("/FlatsPage/flats/Flat[3]/id").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/name").string("Fourth Flat"),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/id").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/x").number(110.1),
                xpath("/FlatsPage/flats/Flat[3]/coordinates/y").string("1234"),
                xpath("/FlatsPage/flats/Flat[3]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[3]/area").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/numberOfRooms").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/height").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/view").string("GOOD"),
                xpath("/FlatsPage/flats/Flat[3]/transport").string("NORMAL"),
                xpath("/FlatsPage/flats/Flat[3]/house/id").string("4"),
                xpath("/FlatsPage/flats/Flat[3]/house/name").string("Fourth House"),
                xpath("/FlatsPage/flats/Flat[3]/house/year").string("2003"),
                xpath("/FlatsPage/flats/Flat[3]/house/numberOfFlatsOnFloor").string("1"),

                xpath("/FlatsPage/totalElements").string("4"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("4")
            );
    }

    @Test
    void getListFlatsByFilterLessThanEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x[lte]:1.1");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),

                xpath("/FlatsPage/flats/Flat[1]/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("Second Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(2.0),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("100"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("BAD"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("ENOUGH"),
                xpath("/FlatsPage/flats/Flat[1]/house/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("Second House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2001"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("12"),

                xpath("/FlatsPage/totalElements").string("4"),
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
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x[interval]:1.1,2.0");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),

                xpath("/FlatsPage/flats/Flat[1]/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("Second Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(2.0),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("100"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("BAD"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("ENOUGH"),
                xpath("/FlatsPage/flats/Flat[1]/house/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("Second House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2001"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("12"),

                xpath("/FlatsPage/totalElements").string("4"),
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
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x[range]:1.1,2.0");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),

                xpath("/FlatsPage/flats/Flat[1]/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("Second Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(2.0),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("100"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("BAD"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("ENOUGH"),
                xpath("/FlatsPage/flats/Flat[1]/house/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("Second House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2001"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("12"),

                xpath("/FlatsPage/flats/Flat[2]/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/name").string("Third Flat"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/x").number(100.0),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/y").string("200"),
                xpath("/FlatsPage/flats/Flat[2]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[2]/area").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/numberOfRooms").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/height").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/view").string("YARD"),
                xpath("/FlatsPage/flats/Flat[2]/transport").string("NONE"),
                xpath("/FlatsPage/flats/Flat[2]/house/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/house/name").string("Third House"),
                xpath("/FlatsPage/flats/Flat[2]/house/year").string("2002"),
                xpath("/FlatsPage/flats/Flat[2]/house/numberOfFlatsOnFloor").string("8"),

                xpath("/FlatsPage/totalElements").string("4"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("2")
            );
    }

    @Test
    void getListFlatsByFiltersEqualsAndGreaterThan_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "2")
            .param("filter", "coordinates.x[gte]:1.1_coordinates.y[lt]:101");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/FlatsPage").exists(),
                xpath("/FlatsPage/flats").exists(),

                xpath("/FlatsPage/flats/Flat[1]/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/name").string("Second Flat"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/id").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/x").number(2.0),
                xpath("/FlatsPage/flats/Flat[1]/coordinates/y").string("100"),
                xpath("/FlatsPage/flats/Flat[1]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[1]/area").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/numberOfRooms").string("10"),
                xpath("/FlatsPage/flats/Flat[1]/height").string("2"),
                xpath("/FlatsPage/flats/Flat[1]/view").string("BAD"),
                xpath("/FlatsPage/flats/Flat[1]/transport").string("ENOUGH"),
                xpath("/FlatsPage/flats/Flat[1]/house/id").string("1"),
                xpath("/FlatsPage/flats/Flat[1]/house/name").string("Second House"),
                xpath("/FlatsPage/flats/Flat[1]/house/year").string("2001"),
                xpath("/FlatsPage/flats/Flat[1]/house/numberOfFlatsOnFloor").string("12"),

                xpath("/FlatsPage/flats/Flat[2]/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/name").string("Third Flat"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/x").number(100.0),
                xpath("/FlatsPage/flats/Flat[2]/coordinates/y").string("200"),
                xpath("/FlatsPage/flats/Flat[2]/creationDate").exists(),
                xpath("/FlatsPage/flats/Flat[2]/area").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/numberOfRooms").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/height").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/view").string("YARD"),
                xpath("/FlatsPage/flats/Flat[2]/transport").string("NONE"),
                xpath("/FlatsPage/flats/Flat[2]/house/id").string("3"),
                xpath("/FlatsPage/flats/Flat[2]/house/name").string("Third House"),
                xpath("/FlatsPage/flats/Flat[2]/house/year").string("2002"),
                xpath("/FlatsPage/flats/Flat[2]/house/numberOfFlatsOnFloor").string("8"),

                xpath("/FlatsPage/totalElements").string("4"),
                xpath("/FlatsPage/totalPages").string("1"),
                xpath("/FlatsPage/currentPage").string("0"),
                xpath("/FlatsPage/pageSize").string("2")
            );
    }
}
