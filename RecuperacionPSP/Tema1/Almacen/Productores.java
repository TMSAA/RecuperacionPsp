package Almacen;

import java.util.Random;

public class Productores extends Thread{

	Almacen almacen = new Almacen(10);
	Random random = new Random();
	
	public Productores(Almacen almacen) {
		super();
		this.almacen = almacen;
	}

	@Override
	public void run() {
		while(true) {
			String producto = new String();
			almacen.almacenar(producto);
			try {
				Thread.sleep(random.nextInt(501) + 100);
			} catch (InterruptedException e) {}
		}
	}
}
