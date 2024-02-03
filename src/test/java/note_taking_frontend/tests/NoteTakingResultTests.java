package note_taking_frontend.tests;

import note_taking_frontend.entity.NoteTakingResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class NoteTakingResultTests {

    Logger logger = LoggerFactory.getLogger(NoteTakingResultTests.class);

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8080/note")
                .build();
    }

    @Test
    void createTwoNotes() {
        webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("note", "Create a sample test note 1")
                        .build())
                .exchange()
                .expectStatus().isOk();

        webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("note", "Create a sample test note 2")
                        .build())
                .exchange()
                .expectStatus().isOk();

        logger.info("Success");
    }

    @Test
    void getAllNotes() {
        List<NoteTakingResult> result = webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/list").build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(NoteTakingResult.class)
                .returnResult()
                .getResponseBody();
        result.stream().forEach(res -> logger.info(res.toString()));
    }
}