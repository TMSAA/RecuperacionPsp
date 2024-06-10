package Globos;

import java.util.List;
import java.util.Random;

public class PinchaGlobos extends Thread{

    private String id;
    private AlmacenGlobos almacen;

    public PinchaGlobos(String id, AlmacenGlobos almacen) {
        this.id = id;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                Thread.sleep(random.nextInt(10000) + 1000);
                List<Globo> globosHinchar = almacen.getGlobosHinchar();
                if (!globosHinchar.isEmpty()) {
                    Globo globo = globosHinchar.get(random.nextInt(globosHinchar.size()));
                    almacen.globoPinchado(globo, id);
                }
            }
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }
}
