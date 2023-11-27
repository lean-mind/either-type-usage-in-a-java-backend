package es.leanmind.eitherspringboot.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextWrapperShould {

    @Test
    public void notWrapTextIfItFitsInOneLine() {
        assertEquals("hola", wrap("hola", 4));
    }

    @Test
    public void wrapTextSplittingLines() {
        assertEquals("ho\nla", wrap("hola", 2));
        assertEquals("oh\nla\nla", wrap("ohlala", 2));
    }

    @Test
    public void wrapTextUsingSpacesWhenPossible(){
        assertEquals("hola\nmundo", wrap("hola mundo", 7));
    }

    @Test
    public void doNotWrapInCaseOfWrongColumnWidth() {
        assertEquals("Column width has to be one or bigger", wrap("someText", -7));
    }

    private String wrap(String text, int columnWidth) {
        return TextWrapper
                .wrap(text, columnWidth)
                .map(Text::toString)
                .getOrElseGet(Throwable::getMessage);
    }
}
