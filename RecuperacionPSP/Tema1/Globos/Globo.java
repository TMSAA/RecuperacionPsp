package Globos;

public class Globo {

    private int id;
    private int volumen;

    public Globo(int id) {
        this.id = id;
        this.volumen = 0;
    }

    public int getId() {
        return id;
    }

    public int getVolumen() {
        return volumen;
    }

    public void incrementarVolumen() {
        volumen++;
    }

    public boolean estallado() {
        return volumen > 5;
    }
}
