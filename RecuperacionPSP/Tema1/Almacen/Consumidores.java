package Almacen;

import java.util.Random;

public class Consumidores extends Thread{

	Almacen almacen = new Almacen(10);
	Random ramdom = new Random();
	
	public Consumidores(Almacen almacen) {
		super();
		this.almacen = almacen;
	}

	@Override
	public void run() {
		while(true) {
			almacen .retirar();
			try {
				Thread.sleep(ramdom.nextInt(501)+100);
			} catch (InterruptedException e) {}
		}
	}
}
