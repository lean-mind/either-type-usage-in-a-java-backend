package es.leanmind.eitherspringboot.domain;

import io.vavr.control.Either;

public class ColumnWidth {
    private final int width;

    private ColumnWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public static Either<IllegalArgumentException, ColumnWidth> create(int width) {
        if (width <= 0) {
            return Either.left(new IllegalArgumentException("Column width has to be one or bigger"));
        } else {
            return Either.right(new ColumnWidth(width));
        }
    }
}

