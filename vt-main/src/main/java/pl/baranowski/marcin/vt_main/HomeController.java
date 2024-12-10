package pl.baranowski.marcin.vt_main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final RestClient restClient;

    public HomeController(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:8090").build();
    }

//    @GetMapping("/block/{seconds}")
//    public String home(@PathVariable Integer seconds) {
//        ResponseEntity<Void> result = restClient.get()
//                .uri("/block/" + seconds)
//                .retrieve()
//                .toBodilessEntity();
//        log.info("{} on {}", result.getStatusCode(), Thread.currentThread());
//        return Thread.currentThread().toString();
//    }

    @GetMapping("/")
    public String hello() {
        return "Hello Marcin";
    }

    @GetMapping("/siema")
    public String siema() {
        return "siema Marcinek";
    }

    @GetMapping("/students/block/{seconds}")
    public String student(@PathVariable Integer seconds) {
        ResponseEntity<Void> result = restClient.get()
                .uri("/students/block/" + seconds)
                .retrieve()
                .toBodilessEntity();
        log.info("{} on {}", result.getStatusCode(), Thread.currentThread());
        return Thread.currentThread().toString();
    }
}
