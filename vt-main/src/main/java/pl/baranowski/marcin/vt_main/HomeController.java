package pl.baranowski.marcin.vt_main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final RestClient restClient;

    public HomeController(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello Marcin, co sie dzieje";
    }

    @GetMapping("/siema")
    public String siema() {
        return "siema Marcinek";
    }

    @GetMapping("/students/block/{seconds}")
    public ResponseEntity<String> student(@PathVariable Integer seconds) {
        try {
            ResponseEntity<Void> result = restClient.get()
                    .uri("/students/block/" + seconds)
                    .retrieve()
                    .toBodilessEntity();

            log.info("Response: {} on {}", result.getStatusCode(), Thread.currentThread());
            return ResponseEntity.ok(Thread.currentThread().toString());

        } catch (Exception ex) {
            log.error("Request failed on thread {}: {}", Thread.currentThread(), ex.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Request failed: " + ex.getMessage());
        }
    }
}
