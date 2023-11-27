package es.leanmind.eitherspringboot.infrastructure;

import domain.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Controller {

    private final ApplicationService appService;

    public static void main(String[] args) {
        SpringApplication.run(Controller.class, args);
    }

    @Autowired
    public Controller(ApplicationService appService){
        this.appService = appService;
    }

    @GetMapping("/wrap-text")
    public ResponseEntity<String> wrapText(@RequestParam(value = "text", defaultValue = "") String text,
                           @RequestParam(value = "columnWidth", defaultValue = "") String columnWidth) {
        return appService
                .wrap(text, columnWidth)
                .map(t -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(text))
                .getOrElseGet(e -> ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(e));
    }
}
