package GeneradorSecuencias;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el patrón:");
        String patron = scanner.nextLine();
        scanner.close();

       
        if (!patron.matches("([a-zA-Z]\\d+)+")) {
            System.err.println("Patrón incorrecto.");
            return;
        }

        Map<Character, Integer> secuencia = new LinkedHashMap<>();
        for (int i = 0; i < patron.length(); i += 2) {
            char letra = patron.charAt(i);
            int repeticiones = Character.getNumericValue(patron.charAt(i + 1));
            if (secuencia.containsKey(letra)) {
                secuencia.put(letra, secuencia.get(letra) + repeticiones);
            } else {
                secuencia.put(letra, repeticiones);
            }
        }

        List<Character> letras = new ArrayList<>(secuencia.keySet());
        Turno turno = new Turno(letras);

        for (Map.Entry<Character, Integer> entry : secuencia.entrySet()) {
            new EscribeLetras(entry.getKey(), entry.getValue(), turno).start();
        }
    }
}
