package GeneradorSecuencias;

public class EscribeLetras extends Thread{

    private char letra;
    private int repeticiones;
    private Turno turno;

    public EscribeLetras(char letra, int repeticiones, Turno turno) {
        this.letra = Character.toUpperCase(letra);
        this.repeticiones = repeticiones;
        this.turno = turno;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (turno) {
                    while (turno.getTurno() != letra) {
                        turno.wait();
                    }
                    for (int i = 0; i < repeticiones; i++) {
                        System.out.print(letra);
                        Thread.sleep(500); 
                    }
                    turno.avanzarTurno();
                    turno.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
