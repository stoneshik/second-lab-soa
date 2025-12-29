package lab.is;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.is.bd.entities.Coordinates;
import lab.is.bd.entities.Flat;
import lab.is.bd.entities.House;
import lab.is.bd.entities.Transport;
import lab.is.bd.entities.View;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
abstract class SpringBootApplicationTest {
    @Container
    @ServiceConnection
    static final PostgreSQLContainer<?> postgresSqlContainer =
        new PostgreSQLContainer<>("postgres:16.4")
            .withReuse(false)
            .withDatabaseName("is_service");

    @AfterAll
    void stopContainers() {
        if (postgresSqlContainer.isRunning()) {
            postgresSqlContainer.stop();
        }
    }

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @PersistenceContext
    private EntityManager entityManager;

    protected void setupDb() {
        new TransactionTemplate(transactionManager).execute(
            status -> {
                clearDb();
                createEntitiesInDb();
                forceWritingToDb();
                return null;
            }
        );
    }

    protected void setupEmptyDb() {
        new TransactionTemplate(transactionManager).execute(
            status -> {
                clearDb();
                forceWritingToDb();
                return null;
            }
        );
    }

    private void clearDb() {
        entityManager.createNativeQuery(
            """
            TRUNCATE
                coordinates,
                flats,
                houses
            RESTART IDENTITY CASCADE
            """
        ).executeUpdate();
        entityManager.clear();
    }

    protected void createEntitiesInDb() {
        Coordinates coordinates1 = Coordinates.builder()
            .x(1.1f)
            .y(123L)
            .build();
        Coordinates coordinates2 = Coordinates.builder()
            .x(2.0f)
            .y(100L)
            .build();
        Coordinates coordinates3 = Coordinates.builder()
            .x(100.0f)
            .y(200L)
            .build();
        Coordinates coordinates4 = Coordinates.builder()
            .x(110.1f)
            .y(1234L)
            .build();
        entityManager.persist(coordinates1);
        entityManager.persist(coordinates2);
        entityManager.persist(coordinates3);
        entityManager.persist(coordinates4);

        House house1 = House.builder()
            .name("First House")
            .year(2000)
            .numberOfFlatsOnFloor(9)
            .build();
        House house2 = House.builder()
            .name("Second House")
            .year(2001)
            .numberOfFlatsOnFloor(12)
            .build();
        House house3 = House.builder()
            .name("Third House")
            .year(2002)
            .numberOfFlatsOnFloor(8)
            .build();
        House house4 = House.builder()
            .name("Fourth House")
            .year(2003)
            .numberOfFlatsOnFloor(1)
            .build();
        entityManager.persist(house1);
        entityManager.persist(house2);
        entityManager.persist(house3);
        entityManager.persist(house4);

        Flat flat1 = Flat.builder()
            .name("First Flat")
            .coordinates(coordinates1)
            .area(1)
            .numberOfRooms(1)
            .height(10)
            .view(View.STREET)
            .transport(Transport.FEW)
            .house(house1)
            .build();
        Flat flat2 = Flat.builder()
            .name("Second Flat")
            .coordinates(coordinates2)
            .area(2)
            .numberOfRooms(2)
            .height(2)
            .view(View.BAD)
            .transport(Transport.ENOUGH)
            .house(house2)
            .build();
        Flat flat3 = Flat.builder()
            .name("Third Flat")
            .coordinates(coordinates3)
            .area(3)
            .numberOfRooms(3)
            .height(3)
            .view(View.YARD)
            .transport(Transport.NONE)
            .house(house3)
            .build();
        Flat flat4 = Flat.builder()
            .name("Fourth Flat")
            .coordinates(coordinates4)
            .area(4)
            .numberOfRooms(4)
            .height(4)
            .view(View.GOOD)
            .transport(Transport.NORMAL)
            .house(house4)
            .build();
        entityManager.persist(flat1);
        entityManager.persist(flat2);
        entityManager.persist(flat3);
        entityManager.persist(flat4);
    }

    private void forceWritingToDb() {
        entityManager.flush();
        entityManager.clear();
    }
}
