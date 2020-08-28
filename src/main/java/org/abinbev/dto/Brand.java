package org.abinbev.dto;

/**
 * Brand model
 */
public enum Brand {
    AGUILA("Aguila"),
    BECKS("Beck's Blue"),
    BRAHMA("Brahma"),
    BUDWEISER("Budweiser"),
    CASS("Cass"),
    EAGLE("Eagle Lager"),
    HERO("Hero"),
    JUPILER("Jupiler"),
    LEFFE("Leffe"),
    MODELO("Modelo Especial"),
    PATAGONIA("Patagonia 24.7"),
    VICTORIA("Victoria"),
    WALS("Wäls Brut");

    private final String name;

    Brand(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                '}';
    }
}
