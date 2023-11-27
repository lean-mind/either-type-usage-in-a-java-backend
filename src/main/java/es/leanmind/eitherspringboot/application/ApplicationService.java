package es.leanmind.eitherspringboot.application;

import es.leanmind.eitherspringboot.application.errors.ColumnIsNull;
import es.leanmind.eitherspringboot.application.errors.ColumnMustBeDecimal;
import es.leanmind.eitherspringboot.application.errors.ApplicationError;
import es.leanmind.eitherspringboot.application.errors.TextIsNull;
import es.leanmind.eitherspringboot.application.errors.UnexpectedError;
import es.leanmind.eitherspringboot.domain.Text;
import es.leanmind.eitherspringboot.domain.TextWrapper;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    public Either<ApplicationError, String> wrap(String text, String columnWidth) {
        if (text == null){
            return Either.left(TextIsNull.instantiate());
        }
        if (columnWidth == null){
            return Either.left(ColumnIsNull.instantiate());
        }
        if (!columnWidth.matches("\\d+")) {
            return Either.left(ColumnMustBeDecimal.instantiate());
        }

        return TextWrapper
                .wrap(text, Integer.parseInt(columnWidth))
                .map(Text::toString)
                .mapLeft(error -> UnexpectedError.instantiate(error.getMessage()));
    }
}
