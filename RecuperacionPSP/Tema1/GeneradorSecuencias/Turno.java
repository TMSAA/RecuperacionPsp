package GeneradorSecuencias;

import java.util.List;

public class Turno {

    private List<Character> letras;
    private int turnoActual;

    public Turno(List<Character> letras) {
        this.letras = letras;
        this.turnoActual = 0;
    }

    public synchronized char getTurno() {
        return letras.get(turnoActual);
    }

    public synchronized void avanzarTurno() {
        turnoActual = (turnoActual + 1) % letras.size();
    }
}
