package es.leanmind.eitherspringboot.domain;

import io.vavr.control.Either;

public class TextWrapper {

    public static Either<IllegalArgumentException, Text> wrap(String text, int columnWidth) {
        return ColumnWidth
                .create(columnWidth)
                .map(width -> wrap(new Text(text), width));
    }

    private static Text wrap(Text text, ColumnWidth columnWidth) {
        if (text.fitsIn(columnWidth)) {
            return text;
        } else {
            Text wrappedText = text.wrapFirstLine(columnWidth);
            Text remainingText = text.removeFirstLine(columnWidth);
            return wrappedText.concat(wrap(remainingText, columnWidth));
        }
    }
}

