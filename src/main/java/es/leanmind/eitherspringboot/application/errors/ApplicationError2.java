package es.leanmind.eitherspringboot.application.errors;

public interface ApplicationError2 {

  class ColumnIsNull2 implements ApplicationError2 {
    public final String message = "Column width must not be null";
  }

  class ColumnMustBeDecimal2 implements ApplicationError2 {
    public final String message = "Column width must be decimal";
  }

  class TextIsNull2 implements ApplicationError2 {
    public final String message = "Text must not be null";
  }

  class UnexpectedError2 implements ApplicationError2 {
    public final String message;

    public UnexpectedError2(String message) {
      this.message = message;
    }
  }

}
