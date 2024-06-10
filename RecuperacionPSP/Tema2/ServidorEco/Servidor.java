package ServidorEco;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		int port = 9999;

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Servidor escuchando en puerto " + port);

			while (true) {
				try (Socket socket = serverSocket.accept()) {
					System.out.println("Cliente conectado " + socket.getInetAddress());

					DataInputStream input = new DataInputStream(socket.getInputStream());
					DataOutputStream output = new DataOutputStream(socket.getOutputStream());

					String message;
					while (!(message = input.readUTF()).equals("exit")) {
						System.out.println("Recibido del cliente: " + message);
						output.writeUTF(message); 
						output.flush();

					}

				} catch (IOException e) {
					System.out.println("Error en la conexion con el cliente");
				}
			}
		} catch (Exception e) {
			System.out.println("Error con el puerto");
		}
	}
}
