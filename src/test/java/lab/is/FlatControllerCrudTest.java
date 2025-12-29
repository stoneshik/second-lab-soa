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
class FlatControllerCrudTest extends SpringBootApplicationTest {
    @Test
    void createMusicBand_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        String xmlRequest = """
        <?xml version="1.0" encoding="UTF-8"?>
        <Flat>
            <name>Test Flat</name>
            <coordinates>
                <x>1.1</x>
                <y>123</y>
            </coordinates>
            <area>1</area>
            <numberOfRooms>1</numberOfRooms>
            <height>10</height>
            <view>STREET</view>
            <transport>FEW</transport>
            <house>
                <name>First House</name>
                <year>2000</year>
                <numberOfFlatsOnFloor>9</numberOfFlatsOnFloor>
            </house>
        </Flat>
        """;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/api/v1/flats")
            .contentType(MediaType.APPLICATION_XML)
            .content(xmlRequest);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/Flat/id").string("1"),
                xpath("/Flat/name").string("Test Flat"),
                xpath("/Flat/coordinates/x").number(1.1),
                xpath("/Flat/coordinates/y").string("123"),
                xpath("/Flat/creationDate").exists(),
                xpath("/Flat/area").string("1"),
                xpath("/Flat/numberOfRooms").string("1"),
                xpath("/Flat/height").string("10"),
                xpath("/Flat/view").string("STREET"),
                xpath("/Flat/transport").string("FEW"),
                xpath("/Flat/house/name").string("First House"),
                xpath("/Flat/house/year").string("2000"),
                xpath("/Flat/house/numberOfFlatsOnFloor").string("9")
            );
    }

    @Test
    void createFlat_ReturnsResponseWithStatusUnprocessableEntity() throws Exception {
        setupDb();
        String xmlRequest = """
        <?xml version="1.0" encoding="UTF-8"?>
        <Flat>
            <name>Test Flat</name>
            <coordinates>
                <x>1.1</x>
                <y>123</y>
            </coordinates>
            <area>0</area>
            <numberOfRooms>1</numberOfRooms>
            <height>10</height>
            <view>STREET</view>
            <transport>FEW</transport>
            <house>
                <name>First House</name>
                <year>2000</year>
                <numberOfFlatsOnFloor>9</numberOfFlatsOnFloor>
            </house>
        </Flat>
        """;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/api/v1/flats")
            .contentType(MediaType.APPLICATION_XML)
            .content(xmlRequest);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isUnprocessableEntity(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/Error").exists(),
                xpath("/Error/messages").exists(),
                xpath("/Error/messages/message").exists(),
                xpath("/Error/time").exists(),
                xpath("/Error/messages/message[1]").string("Flat field area equals or less than 0")
            );
    }

    @Test
    void getFlatById_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        Long id = 1L;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats/{id}", id);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/Flat/id").string("1"),
                xpath("/Flat/name").string("First Flat"),
                xpath("/Flat/coordinates/x").number(1.1),
                xpath("/Flat/coordinates/y").string("123"),
                xpath("/Flat/creationDate").exists(),
                xpath("/Flat/area").string("1"),
                xpath("/Flat/numberOfRooms").string("1"),
                xpath("/Flat/height").string("10"),
                xpath("/Flat/view").string("STREET"),
                xpath("/Flat/transport").string("FEW"),
                xpath("/Flat/house/name").string("First House"),
                xpath("/Flat/house/year").string("2000"),
                xpath("/Flat/house/numberOfFlatsOnFloor").string("9")
            );
    }

    @Test
    void getFlatById_ReturnsResponseWithStatusNotFound() throws Exception {
        setupDb();
        Long id = 100L;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats/{id}", id);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isNotFound(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/Error").exists(),
                xpath("/Error/message").string("Not found flat by id error"),
                xpath("/Error/time").exists()
            );
    }

    @Test
    void putFlatById_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        Long id = 1L;
        String xmlRequest = """
        <?xml version="1.0" encoding="UTF-8"?>
        <Flat>
            <name>Updated Flat</name>
            <coordinates>
                <x>100.1</x>
                <y>123</y>
            </coordinates>
            <area>1</area>
            <numberOfRooms>1</numberOfRooms>
            <height>10</height>
            <view>STREET</view>
            <transport>FEW</transport>
            <house>
                <name>First House</name>
                <year>2000</year>
                <numberOfFlatsOnFloor>9</numberOfFlatsOnFloor>
            </house>
        </Flat>
        """;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/api/v1/flats/{id}", id)
            .contentType(MediaType.APPLICATION_XML)
            .content(xmlRequest);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/Flat/id").string("1"),
                xpath("/Flat/name").string("Updated Flat"),
                xpath("/Flat/coordinates/x").number(100.1),
                xpath("/Flat/coordinates/y").string("123"),
                xpath("/Flat/creationDate").exists(),
                xpath("/Flat/area").string("1"),
                xpath("/Flat/numberOfRooms").string("1"),
                xpath("/Flat/height").string("10"),
                xpath("/Flat/view").string("STREET"),
                xpath("/Flat/transport").string("FEW"),
                xpath("/Flat/house/name").string("First House"),
                xpath("/Flat/house/year").string("2000"),
                xpath("/Flat/house/numberOfFlatsOnFloor").string("9")
            );
    }

    @Test
    void putFlatById_ReturnsResponseWithStatusUnprocessableEntity() throws Exception {
        setupDb();
        Long id = 1L;
        String xmlRequest = """
        <?xml version="1.0" encoding="UTF-8"?>
        <Flat>
            <name>Updated Flat</name>
            <coordinates>
                <x>100.1</x>
                <y>123</y>
            </coordinates>
            <area>0</area>
            <numberOfRooms>1</numberOfRooms>
            <height>10</height>
            <view>STREET</view>
            <transport>FEW</transport>
            <house>
                <name>First House</name>
                <year>2000</year>
                <numberOfFlatsOnFloor>9</numberOfFlatsOnFloor>
            </house>
        </Flat>
        """;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/api/v1/flats/{id}", id)
            .contentType(MediaType.APPLICATION_XML)
            .content(xmlRequest);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isUnprocessableEntity(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/Error").exists(),
                xpath("/Error/messages").exists(),
                xpath("/Error/messages/message").exists(),
                xpath("/Error/time").exists(),
                xpath("/Error/messages/message[1]").string("Flat field area equals or less than 0")
            );
    }

    @Test
    void putFlatById_ReturnsResponseWithStatusNotFound() throws Exception {
        setupDb();
        Long id = 100L;
        String xmlRequest = """
        <?xml version="1.0" encoding="UTF-8"?>
        <Flat>
            <name>Updated Flat</name>
            <coordinates>
                <x>100.1</x>
                <y>123</y>
            </coordinates>
            <area>1</area>
            <numberOfRooms>1</numberOfRooms>
            <height>10</height>
            <view>STREET</view>
            <transport>FEW</transport>
            <house>
                <name>First House</name>
                <year>2000</year>
                <numberOfFlatsOnFloor>9</numberOfFlatsOnFloor>
            </house>
        </Flat>
        """;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/api/v1/flats/{id}", id)
            .contentType(MediaType.APPLICATION_XML)
            .content(xmlRequest);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isNotFound(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/Error").exists(),
                xpath("/Error/message").string("Not found flat by id error"),
                xpath("/Error/time").exists()
            );
    }

    @Test
    void deleteFlatById_ReturnsResponseWithStatusNoContent() throws Exception {
        setupDb();
        final Long id = 1L;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats/{id}", id);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isNoContent()
            );
    }

    @Test
    void deleteFlatById_ReturnsResponseWithStatusNotFound() throws Exception {
        setupDb();
        final Long id = 100L;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats/{id}", id);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isNotFound()
            );
    }

    @Test
    void deleteOneFlatByFilterHouseName_ReturnsResponseWithStatusNoContent() throws Exception {
        setupDb();
        final String houseName = "First House";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats")
            .param("houseName", houseName);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isNoContent()
            );
    }

    @Test
    void deleteOneFlatByFilterHouseYear_ReturnsResponseWithStatusNoContent() throws Exception {
        setupDb();
        final String houseYear = "2000";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats")
            .param("houseYear", houseYear);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isNoContent()
            );
    }

    @Test
    void deleteOneFlatByFilterNumberOfFlatsOnFloor_ReturnsResponseWithStatusNoContent() throws Exception {
        setupDb();
        final String numberOfFlatsOnFloor = "9";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats")
            .param("numberOfFlatsOnFloor", numberOfFlatsOnFloor);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isNoContent()
            );
    }

    @Test
    void deleteOneFlatByAllFilters_ReturnsResponseWithStatusNoContent() throws Exception {
        setupDb();
        final String houseName = "First House";
        final String houseYear = "2000";
        final String numberOfFlatsOnFloor = "9";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats")
            .param("houseName", houseName)
            .param("houseYear", houseYear)
            .param("numberOfFlatsOnFloor", numberOfFlatsOnFloor);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isNoContent()
            );
    }

    @Test
    void deleteOneFlatByFilter_ReturnsResponseWithStatusNotFound() throws Exception {
        setupDb();
        final String houseName = "Not Found House";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats")
            .param("houseName", houseName);

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isNotFound()
            );
    }

    @Test
    void getFlatsAmountHeight_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats/amount/height");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/Response").exists(),
                xpath("/Response").string("123")
            );
    }

    @Test
    void getFlatsGroupByHeight_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats/group-by/height");

        mockMvc
            .perform(requestBuilder)
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_XML),
                xpath("/groups").exists(),
                xpath("/groups/group[0]").exists(),
                xpath("/groups/group[0]/height").string("10"),
                xpath("/groups/group[0]/count").string("8")
            );
    }
}
