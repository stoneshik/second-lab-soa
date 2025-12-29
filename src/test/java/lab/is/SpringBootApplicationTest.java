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
                
            RESTART IDENTITY CASCADE
            """
        ).executeUpdate();
        entityManager.clear();
    }

    protected void createEntitiesInDb() {
        
    }

    private void forceWritingToDb() {
        entityManager.flush();
        entityManager.clear();
    }
}
