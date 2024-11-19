package info.touret.apifirst.sample;

import info.touret.apifirst.sample.generated.Guitar;
import info.touret.apifirst.sample.generated.Guitars;
import io.github.microcks.testcontainers.MicrocksContainersEnsemble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.testcontainers.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT, properties = {
        "server.port=8042",
        "management.server.port=9042"
})
@Import(TestGuitarConfiguration.class)
class GuitarControllerTest {

    @LocalServerPort
    private int port = 8042;

    public static final String GUITAR_API_PREFIX = "/guitars";

    @Autowired
    private TestRestTemplate restTemplate;

    private String url = "http://localhost:" + port + GUITAR_API_PREFIX;

    @BeforeEach
    void setUp() {
        Testcontainers.exposeHostPorts(port);
    }

    @Autowired
    protected MicrocksContainersEnsemble microcksEnsemble;


    @Test
    void should_return_200_guitars() {
        var response = restTemplate.getForEntity(url, Guitars.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(2, response.getBody().getGuitars().size());
    }

    @Test
    void should_create_guitar_successfully() {
        var response = restTemplate.postForEntity(url, new Guitar("Fender", "Stratocaster", null), Guitar.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(201)));
    }

    @Test
    void should_update_guitar_successfully() {
        var response = restTemplate.exchange(url + "/1", HttpMethod.PUT, new HttpEntity<Guitar>(new Guitar("Fender", "Stratocaster", null)), Guitar.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(200)));
    }

    @Test
    void should_delete_guitar_successfully() {
        var response = restTemplate.exchange(url + "/1", HttpMethod.DELETE, new HttpEntity<Guitar>(new Guitar("Fender", "Stratocaster", null)), Guitar.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(204)));
    }
}
