package es.leanmind.eitherspringboot.application.errors;

public final class UnexpectedError extends ApplicationError {

    private UnexpectedError(String errorMessage) {
        super(errorMessage);
    }

    public static ApplicationError instantiate(String errorMessage) {
        return new ApplicationError(errorMessage);
    }

}
