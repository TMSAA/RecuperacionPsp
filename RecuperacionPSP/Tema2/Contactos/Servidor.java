package Contactos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors;

public class Servidor {
	
	public static void main(String[] args) throws IOException {
		int port = 9999;
		
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Servidor escuchando en puerto " + port);
			Agenda agenda = new Agenda();
			ExecutorService executor = Executors.newFixedThreadPool(1000);
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("Cliente conectado: " + socket.getInetAddress());
				executor.submit(new Peticion(socket,agenda));
			}
		} catch (Exception e) {
			System.out.println("Error con el puerto");
		}
	}
}
