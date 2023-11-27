package es.leanmind.eitherspringboot.application;

import es.leanmind.eitherspringboot.application.errors.ColumnIsNull;
import es.leanmind.eitherspringboot.application.errors.ColumnMustBeDecimal;
import es.leanmind.eitherspringboot.application.errors.TextIsNull;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApplicationServiceShould {

    @Test
    public void notAllowForInvalidArguments(){
        var appService = new ApplicationService();

        assertThat(appService.wrap(null, "10").getLeft().getMessage())
                .isEqualTo(Either.left(TextIsNull.instantiate()).getLeft().getMessage());

        assertThat(appService.wrap("some text", null).getLeft().getMessage())
                .isEqualTo(Either.left(ColumnIsNull.instantiate()).getLeft().getMessage());

        assertThat(appService.wrap("some text", "foo").getLeft().getMessage())
                .isEqualTo(Either.left(ColumnMustBeDecimal.instantiate()).getLeft().getMessage());
    }

    @Test
    public void wrapTextUsingTheWrapper(){
        var appService = new ApplicationService();
        assertThat(appService.wrap("hola  mundo", "8"))
                .isEqualTo(Either.right("hola\nmundo"));
    }
}
