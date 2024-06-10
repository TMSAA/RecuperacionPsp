package Parque;

public class Persona extends Thread{

	private Banco banco;
	private long tiempoPaseo;
	private long tiempoSentado;
	
	public Persona(Banco banco,String nombre) {
		super(nombre);
		this.banco = banco;
		tiempoPaseo = (long) ((Math.random()*1000000 % 2001)+1000);
		tiempoSentado = (long) ((Math.random()*1000000% 601)+100);
	}


	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(tiempoPaseo);
				System.out.println(getName()+" llega al banco");
				Main.actualizar(getName()+" llega al banco \n");
				banco.ocuparSitioLibre();
				System.out.println(getName()+ " se a sentado");
				Main.actualizar(getName()+ " se a sentado \n");
				Thread.sleep(tiempoSentado);
				banco.liberarSitioOcupado();
				System.out.println(getName()+" se ha levantado");
				Main.actualizar(getName()+" se ha levantado \n");
			} catch (InterruptedException e) {
				Thread.interrupted();
			}
		}
	} 
}
