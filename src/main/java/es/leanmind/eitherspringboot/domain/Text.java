package es.leanmind.eitherspringboot.domain;

public class Text {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    public Text wrapFirstLine(ColumnWidth columnWidth) {
        var wrappingPoint = getWrappingPoint(columnWidth);
        return new Text(text.substring(0, Math.min(wrappingPoint, text.length())).trim() + "\n");
    }

    public Text removeFirstLine(ColumnWidth columnWidth) {
        if (text.length() <= columnWidth.getWidth()) {
            return new Text("");
        }
        var wrappingPoint = getWrappingPoint(columnWidth);
        return new Text(text.substring(wrappingPoint).trim());
    }

    public boolean fitsIn(ColumnWidth columnWidth) {
        return text.length() <= columnWidth.getWidth();
    }

    public Text concat(Text other) {
        return new Text(text + other.text);
    }

    private int getWrappingPoint(ColumnWidth columnWidth) {
        int lastSpace = text.lastIndexOf(" ");
        if (lastSpace == -1) {
            return columnWidth.getWidth();
        }
        return Math.min(lastSpace, columnWidth.getWidth());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Text text1 = (Text) other;
        return text.equals(text1.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        return text;
    }

}

