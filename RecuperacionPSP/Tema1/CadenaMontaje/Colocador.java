package CadenaMontaje;

import java.util.Random;

public class Colocador extends Thread{

	private Productos producto;
	private Productos[] productos = Productos.values();
	Cadena cadena;
	Random r = new Random();
	
	public Colocador(Cadena cadena) {
		super();
		this.cadena = cadena;
	}
	
	@Override
	public void run() {
		while(true) {
			int alt = r.nextInt(productos.length);
			producto = productos[alt];
			cadena.Colocar(producto);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
	}
}
