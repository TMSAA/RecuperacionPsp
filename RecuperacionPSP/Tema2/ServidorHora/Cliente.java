package ServidorHora;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Cliente {
	
	public static void main(String[] args) {
		String serverAddress = "localhost"; // Cambia esto a la dirección IP del servidor si es necesario
		int port = 9999; // Puerto del servidor

		try (Socket socket = new Socket(serverAddress, port)) {
			LocalTime sendTime = LocalTime.now();

			InputStream inputStream = socket.getInputStream();
			byte[] buffer = new byte[8];
			int bytesRead = inputStream.read(buffer);
			String serverTimeString = new String(buffer, 0, bytesRead).trim();
			LocalTime serverTime = LocalTime.parse(serverTimeString, DateTimeFormatter.ofPattern("HH:mm:ss"));

			LocalTime receiveTime = LocalTime.now();
			Duration delay = Duration.between(sendTime, receiveTime);
			LocalTime adjustedTime = serverTime.plusNanos(delay.toNanos() / 2);

			System.out.println("Hora del servidor: " + serverTime);
			System.out.println("Hora ajustada: " + adjustedTime);
			System.out.println("Retraso estimado: " + delay.toMillis() + " ms");

		} catch (IOException e) {
			System.out.println("Error al conectar con el servidor: " + e.getMessage());
		}
	}
}
