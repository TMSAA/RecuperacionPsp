package CadenaMontaje;

public class Cadena {

	private Productos[] productos;
	private int n;
	private int empaquetados = 0;
	private boolean pausa = false;

	public Cadena(int n) {
		super();
		productos = new Productos[n];
	}

	public synchronized void Colocar(Productos producto) {
		while (n == productos.length | pausa) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
		System.out.println("Producto "+producto+" colocado");
		Main.actualizar("Producto "+producto+" colocado\n");
		productos[n++] = producto;
		notifyAll();
	}
	
	public synchronized void Empaquetar(Productos producto) {
		while (n == 0 | pausa) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread();
			}
		}
		for (int i = 0; i < productos.length; i++) {
			if(productos[i] == producto) {
				System.out.println("Producto "+producto+" empaquetado");
				Main.actualizar("Producto "+producto+" empaquetado\n");
				empaquetados++;
				System.out.println("Total productos empaquetados: "+empaquetados);
				Main.actualizar("Total productos empaquetados: "+empaquetados+"\n");
				productos[i] = null;
			}
		}
		notifyAll();
	}

	 public synchronized void pausarHilos() {
	        pausa = true;
	    }

	    public synchronized void reanudarHilos() {
	        pausa = false;
	        notifyAll();
	    }
}
