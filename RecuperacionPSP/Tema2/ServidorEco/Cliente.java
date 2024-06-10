package ServidorEco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		String serverAddress = "localhost";
		int port = 9999;

		try (Socket socket = new Socket(serverAddress, port);
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				Scanner scanner = new Scanner(System.in)) {

			System.out.println("Conectado al servidor en " + serverAddress + ":" + port);
			System.out.println("Escribe algo y presiona Enter (escribe 'exit' para salir):");

			while (true) {
				String userInput = scanner.nextLine();
				out.writeUTF(userInput);
				out.flush();

				if ("exit".equalsIgnoreCase(userInput)) {
					break;
				}

				String response = in.readUTF();
				System.out.println("Respuesta del servidor: " + response);
			}

		} catch (IOException e) {
			System.out.println("Error al conectar con el servidor: " + e.getMessage());
		}
	}
}
