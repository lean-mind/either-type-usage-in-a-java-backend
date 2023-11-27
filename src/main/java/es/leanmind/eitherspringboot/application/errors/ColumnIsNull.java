package es.leanmind.eitherspringboot.application.errors;

public final class ColumnIsNull extends ApplicationError {
    private ColumnIsNull() {
        super("Column width must not be null");
    }

    public static ApplicationError instantiate() {
        return new ColumnIsNull();
    }
}
