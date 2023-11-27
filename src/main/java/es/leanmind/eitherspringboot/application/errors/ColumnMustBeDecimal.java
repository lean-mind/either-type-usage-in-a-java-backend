package es.leanmind.eitherspringboot.application.errors;

public final class ColumnMustBeDecimal extends ApplicationError {
    private ColumnMustBeDecimal() {
        super("Wrong argument. Column with must be a decimal");
    }

    public static ApplicationError instantiate() {
        return new ColumnMustBeDecimal();
    }

}
