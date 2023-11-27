package domain;

import io.vavr.control.Either;

import java.io.PrintStream;

public class ApplicationService {

    public Either<String, String> wrap(String text, String columnWidth) {
        if (text == null){
            return Either.left("Text must not be null");
        }
        if (columnWidth == null){
            return Either.left("Column width must not be null");
        }
        if (!columnWidth.matches("\\d+")) {
            return Either.left("Wrong argument. Column with must be a decimal");
        }

        return TextWrapper
                .wrap(text, Integer.parseInt(columnWidth))
                .map(Text::toString)
                .mapLeft(Throwable::getMessage);
    }
}
