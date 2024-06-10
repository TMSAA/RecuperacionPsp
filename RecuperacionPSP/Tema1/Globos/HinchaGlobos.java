package Globos;

public class HinchaGlobos extends Thread{

    private String id;
    private AlmacenGlobos almacen;

    public HinchaGlobos(String id, AlmacenGlobos almacen) {
        this.id = id;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Globo globo = almacen.entregarGlobo(id);
                while (!globo.estallado()) {
                    Thread.sleep(1000);
                    globo.incrementarVolumen();
                    System.out.println("GLOBO " + globo.getId() + " VOLUMEN " + globo.getVolumen());
                    Main.actualizar("GLOBO " + globo.getId() + " VOLUMEN " + globo.getVolumen()+"\n");
                    if (globo.estallado()) {
                        almacen.globoEstallado(globo);
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }
}
