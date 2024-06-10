package CenaFilosofos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;

public class Filosofo extends Thread {

	private final int id;
	private final Lock palilloIzquierdo;
	private final Lock palilloDerecho;
	private final Object control = new Object();
	private boolean pausado = false;

	public Filosofo(int id, Lock palilloIzquierdo, Lock palilloDerecho) {
		super();
		this.id = id;
		this.palilloIzquierdo = palilloIzquierdo;
		this.palilloDerecho = palilloDerecho;
	}

	public void pausar() {
        synchronized (control) {
            pausado = true;
        }
    }

    public void reanudar() {
        synchronized (control) {
            pausado = false;
            control.notify();
        }
    }
	public void pensar() {
		System.out.println("Filosofo " + id + " esta pensado");
		Main.actualizar("Filosofo " + id + " esta pensado\n");
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
	}

	public void comer() {
		System.out.println("Filósofo " + id + " está comiendo");
		Main.actualizar("Filósofo " + id + " está comiendo\n");
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
	}

	private void verificarPausa() throws InterruptedException {
        synchronized (control) {
            while (pausado) {
                control.wait();
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                verificarPausa();
                pensar();
                verificarPausa();
                if (id % 2 == 0) { // Estrategia para evitar interbloqueo
                    palilloIzquierdo.lock();
                    palilloDerecho.lock();
                } else {
                    palilloDerecho.lock();
                    palilloIzquierdo.lock();
                }
                try {
                    verificarPausa();
                    comer();
                } finally {
                    palilloIzquierdo.unlock();
                    palilloDerecho.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
