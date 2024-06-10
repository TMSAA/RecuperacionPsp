package CadenaMontaje;

public class Empaquetador extends Thread{



	Productos producto;
	Cadena cadena;
	
	public Empaquetador(Productos producto, Cadena cadena) {
		super();
		this.producto = producto;
		this.cadena = cadena;
	}
	
	@Override
	public void run() {
		while(true) {
			cadena.Empaquetar(producto);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
	}
	
	
	
}
