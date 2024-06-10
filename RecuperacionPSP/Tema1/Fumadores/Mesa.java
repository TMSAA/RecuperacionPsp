package Fumadores;

public class Mesa {

    private Ingredientes ingrediente1;
    private Ingredientes ingrediente2;
    private boolean pausado = false;

    public synchronized void retirar(Ingredientes ingrediente) {
        while ((ingrediente == ingrediente1 || ingrediente == ingrediente2 || ingrediente1 == null || ingrediente2 == null) || pausado)
            try {
                wait();
            } catch (InterruptedException e) {
            }
        ingrediente1 = ingrediente2 = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }

    public synchronized void colocar(Ingredientes ingrediente1, Ingredientes ingrediente2) {
        while ((this.ingrediente1 != null && this.ingrediente2 != null) || pausado)
            try {
                wait();
            } catch (InterruptedException e) {
            }
        this.ingrediente1 = ingrediente1;
        this.ingrediente2 = ingrediente2;
        notifyAll();
    }

    public synchronized void pausarHilos() {
        pausado = true;
    }
    
    public synchronized void reanudarHilos() {
        pausado = false;
        notifyAll();
    }
}
