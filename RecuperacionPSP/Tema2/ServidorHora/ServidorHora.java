package ServidorHora;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServidorHora {

	public static void main(String[] args) {
		int port = 9999;
		
		try (ServerSocket serverSocket = new ServerSocket(port)){
			System.out.println("Servidor escochado en el purto "+port);
			
			while(true) {
				try (Socket clienteSocket = serverSocket.accept()){
					LocalTime time = LocalTime.now();
					String timeString = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
					
					OutputStream out = clienteSocket.getOutputStream();
					out.write(timeString.getBytes());
					out.flush();
				} catch (IOException e) {
					System.out.println("Error en la conexion con el cliente");
				}
			}
		} catch (IOException e) {
			System.out.println("Error con el puerto");
		}
		
	}
}
