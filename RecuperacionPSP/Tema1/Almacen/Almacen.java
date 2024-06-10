package Almacen;

public class Almacen {
    private int almacenados = 0;
    private String[] productos;
    private boolean pausado = false;

    public Almacen(int capacidad) {
        productos = new String[capacidad];
    }

    public synchronized void almacenar(String producto) {
        while (almacenados == productos.length || pausado) // almacén lleno o pausado
            try {
                wait();
            } catch (InterruptedException e) {}
        productos[almacenados++] = producto;
        System.out.println("Producto almacenado - Stock: " + almacenados);
        Main.actualizar("Producto almacenado - Stock: " + almacenados + "\n");
        notifyAll();
    }

    public synchronized String retirar() {
        while (almacenados == 0 || pausado) // almacén vacío o pausado
            try {
                wait();
            } catch (InterruptedException e) {}
        String producto = productos[--almacenados];
        System.out.println("Producto consumido - Stock: " + almacenados);
        Main.actualizar("Producto consumido - Stock: " + almacenados + "\n");
        notifyAll();
        return producto;
    }

    public synchronized void pausarHilos() {
        pausado = true;
    }

    public synchronized void reanudarHilos() {
        pausado = false;
        notifyAll();
    }
}
