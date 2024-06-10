package Piscina;

import java.util.Random;

public class Piscina {
	
	private int capacidad;
	private Random r = new Random();

	public Piscina(int capacidad) {
		super();
		this.capacidad = capacidad;
	}
	
	public synchronized void nadar(String nadador) {
		while(capacidad == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
		capacidad--;
		System.out.println(Thread.currentThread().getName()+" comienza nadar");
		int t = 500 + r.nextInt(301);
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
		System.out.println(Thread.currentThread().getName()+" termino en "+t+" ms");
		capacidad++;
		notifyAll();
	}
	
	public synchronized void bucear(String nadador) {
		while(capacidad < 2) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.interrupted();
				}
		}
		capacidad = capacidad - 2;
		System.out.println(Thread.currentThread().getName()+" comienza a bucaer");
		int t = 500 + r.nextInt(301);
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
		System.out.println(Thread.currentThread().getName()+" termino en "+t+" ms");
		capacidad = capacidad +2;
		notifyAll();
	}
}
