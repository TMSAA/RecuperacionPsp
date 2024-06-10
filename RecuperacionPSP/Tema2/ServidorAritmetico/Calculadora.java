package ServidorAritmetico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculadora extends JFrame {

    private static final long serialVersionUID = 1L;
	private JLabel lblResultado = new JLabel(" ");
    private JPanel pnlBotones = new JPanel(new GridLayout(4, 4));
    private JPanel pnlIgual = new JPanel(new GridLayout(1, 1));
    private JButton[] botones = { new JButton("1"), new JButton("2"), new JButton("3"), new JButton("+"),
            new JButton("4"), new JButton("5"), new JButton("6"), new JButton("-"), new JButton("7"), new JButton("8"),
            new JButton("9"), new JButton("*"), new JButton("C"), new JButton("0"), new JButton(","), new JButton("/"),
            new JButton("=") };
    private Dimension dmVentana = new Dimension(300, 440);

    private double resultado = 0;
    private double numero;
    private static final int SUMA = 1;
    private static final int RESTA = 2;
    private static final int MULTIPLICACION = 3;
    private static final int DIVISION = 4;
    private static final int NINGUNO = 0;
    private int operador = Calculadora.NINGUNO;
    private boolean hayPunto = false;
    private boolean nuevoNumero = true;
    private NumberFormat nf = NumberFormat.getInstance();

    public Calculadora() {
        Dimension dmPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dmPantalla.width - dmVentana.width) / 2;
        int y = (dmPantalla.height - dmVentana.height) / 2;
        this.setLocation(x, y);
        this.setSize(dmVentana);
        this.setTitle("Calculadora");

        lblResultado.setBackground(Color.white);
        lblResultado.setOpaque(true);
        lblResultado.setFont(new Font("Arial", Font.PLAIN, 32));
        PulsaRaton pr = new PulsaRaton();
        for (int i = 0; i < botones.length - 1; i++) {
            pnlBotones.add(botones[i]);
            botones[i].addActionListener(pr);
        }

        pnlIgual.add(botones[botones.length - 1]);
        botones[botones.length - 1].addActionListener(pr);

        pnlIgual.setPreferredSize(new Dimension(0, 50));
        this.add(lblResultado, BorderLayout.NORTH);
        this.add(pnlBotones);
        this.add(pnlIgual, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        lblResultado.setText("0,0");
        lblResultado.setHorizontalAlignment(JLabel.RIGHT);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Calculadora();
    }

    class PulsaRaton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton origen = (JButton) e.getSource();
            String texto = origen.getText();
            switch (texto) {
            case "+":
                operar(Calculadora.SUMA);
                break;
            case "-":
                operar(Calculadora.RESTA);
                break;
            case "*":
                operar(Calculadora.MULTIPLICACION);
                break;
            case "/":
                operar(Calculadora.DIVISION);
                break;
            case ",":
                if (!nuevoNumero) {
                    if (!hayPunto) {
                        String rdo = lblResultado.getText();
                        lblResultado.setText(rdo + ",");
                    }
                } else {
                    lblResultado.setText("0,");
                    nuevoNumero = false;
                }
                hayPunto = true;
                break;
            case "C":
                lblResultado.setText("0,0");
                nuevoNumero = true;
                hayPunto = false;
                break;
            case "=":
                if (operador != Calculadora.NINGUNO) {
                    try {
                        Socket socket = new Socket("localhost", 9999);
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                        DataInputStream input = new DataInputStream(socket.getInputStream());

                        String operacion = "";
                        switch (operador) {
                        case Calculadora.SUMA:
                            operacion = "suma";
                            break;
                        case Calculadora.RESTA:
                            operacion = "resta";
                            break;
                        case Calculadora.MULTIPLICACION:
                            operacion = "multiplicacion";
                            break;
                        case Calculadora.DIVISION:
                            operacion = "division";
                            break;
                        }

                        output.writeUTF(operacion);
                        output.writeDouble(resultado);
                        output.writeDouble(Double.parseDouble(lblResultado.getText().replace(",", ".")));

                        String resultadoServidor = input.readUTF();
                        lblResultado.setText(resultadoServidor.split(": ")[1]);

                        operador = Calculadora.NINGUNO;
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            default:
                String rdo = lblResultado.getText();
                if (nuevoNumero) {
                    lblResultado.setText(texto);
                } else {
                    lblResultado.setText(rdo + texto);
                }
                nuevoNumero = false;
                break;
            }
        }
    }

    public void operar(int operacion) {
        if (!nuevoNumero) {
            String rdo = lblResultado.getText();
            if (!rdo.isEmpty()) {
                Number n = null;
                try {
                    n = (Number) nf.parse(rdo);
                    numero = n.doubleValue();
                } catch (ParseException ex) {

                }
                resultado = numero;
                operador = operacion;
                lblResultado.setText(nf.format(resultado));
                nuevoNumero = true;
                hayPunto = false;
            }
        }
    }
}
