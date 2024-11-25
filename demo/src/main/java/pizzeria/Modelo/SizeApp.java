package pizzeria.Modelo;

public enum SizeApp {
    PEQUENO("pequeno"),
    MEDIANO("mediano"),
    GRANDE("grande");

    private final String size;

    SizeApp(String size) {
        this.size = size;
    }

    public String getValue() {
        return size;
    }

}
