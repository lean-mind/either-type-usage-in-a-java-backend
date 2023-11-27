package es.leanmind.eitherspringboot.application.errors;

public final class TextIsNull extends ApplicationError {
    private TextIsNull() {
        super("Text must not be null");
    }

    public static ApplicationError instantiate() {
        return new TextIsNull();
    }
}
