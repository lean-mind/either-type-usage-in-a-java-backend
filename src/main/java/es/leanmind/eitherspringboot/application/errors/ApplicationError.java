package es.leanmind.eitherspringboot.application.errors;

public sealed class ApplicationError permits ColumnIsNull, ColumnMustBeDecimal, TextIsNull, UnexpectedError {
    private final String message;

    protected ApplicationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}