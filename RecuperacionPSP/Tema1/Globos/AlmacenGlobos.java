package Globos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlmacenGlobos {
	private List<Globo> globos;
	private int maxGlobos = 10;
	private List<Globo> globosHinchar = new ArrayList<>();
	private boolean paused = false;

	public AlmacenGlobos() {
		globos = new ArrayList<>();
		for (int i = 1; i <= maxGlobos; i++) {
			globos.add(new Globo(i));
		}
	}

	public synchronized Globo entregarGlobo(String hinchaId) throws InterruptedException {
		while (globos.isEmpty() || globosHinchar.size() >= 3 || paused) {
			wait();
		}
		Globo globo = globos.remove(0);
		globosHinchar.add(globo);
		System.out.println("GLOBO " + globo.getId() + " ENTREGADO A " + hinchaId);
		Main.actualizar("GLOBO " + globo.getId() + " ENTREGADO A " + hinchaId + "\n");
		return globo;
	}

	public synchronized void globoPinchado(Globo globo, String pinchaId) {
		globosHinchar.remove(globo);
		System.out.println("GLOBO " + globo.getId() + " PINCHADO POR " + pinchaId);
		Main.actualizar("GLOBO " + globo.getId() + " PINCHADO POR " + pinchaId + "\n");
		notifyAll();
	}

	public synchronized void globoEstallado(Globo globo) {
		globosHinchar.remove(globo);
		System.out.println("GLOBO " + globo.getId() + " ESTALLA");
		Main.actualizar("GLOBO " + globo.getId() + " ESTALLA\n");
		notifyAll();
	}

	public synchronized List<Globo> getGlobosHinchar() {
		return Collections.unmodifiableList(globosHinchar);
	}

	public synchronized void pausar() {
		paused = true;

	}

	public synchronized void reanudar() {
		paused = false;
		notifyAll();
	}
}
