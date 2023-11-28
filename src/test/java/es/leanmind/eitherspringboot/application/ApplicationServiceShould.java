package es.leanmind.eitherspringboot.application;

import es.leanmind.eitherspringboot.application.ApplicationService;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApplicationServiceShould {

    @Test
    public void notAllowForInvalidArguments(){
        var appService = new ApplicationService();

        assertThat(appService.wrap(null, "10"))
                .isEqualTo(Either.left("Text must not be null"));

        assertThat(appService.wrap("some text", null))
                .isEqualTo(Either.left("Column width must not be null"));

        assertThat(appService.wrap("some text", "foo"))
                .isEqualTo(Either.left("Wrong argument. Column with must be a decimal"));
    }

    @Test
    public void wrapTextUsingTheWrapper(){
        var appService = new ApplicationService();
        assertThat(appService.wrap("hola  mundo", "8"))
                .isEqualTo(Either.right("hola\nmundo"));
    }
}
