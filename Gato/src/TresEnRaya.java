import javax.swing.*; // Importa las clases necesarias para crear una interfaz gráfica
import java.awt.*; // Importa las clases necesarias para el diseño de la interfaz
import java.awt.event.ActionEvent; // Importa la clase para manejar eventos de acciones
import java.awt.event.ActionListener; // Importa la clase para escuchar eventos de acciones
import java.util.Random; // Importa la clase Random para generar números aleatorios

public class TresEnRaya extends JFrame implements ActionListener {
    // Matriz de botones 3x3 para representar el tablero del juego
    private JButton[][] botones = new JButton[3][3];
    // Variable para alternar entre los jugadores 'X' y 'O'
    private boolean jugadorX; // true para 'X', false para 'O'

    // Constructor de la clase TresEnRaya
    public TresEnRaya() {
        setTitle("Tres en Raya"); // Establece el título de la ventana
        setSize(300, 300); // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        setLayout(new GridLayout(3, 3)); // Utiliza un diseño de cuadrícula 3x3 para la ventana

        // Inicializa los botones y los añade a la ventana
        for (int i = 0; i < 3; i++) { //Un corazón
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton(""); // Crea un nuevo botón
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 60)); // Establece la fuente y el tamaño del texto del botón
                botones[i][j].setFocusPainted(false); // Elimina el borde del botón cuando está enfocado
                botones[i][j].addActionListener(this); // Añade un ActionListener al botón
                add(botones[i][j]); // Añade el botón a la ventana
            }
        }

        // Determina aleatoriamente quién comienza
        Random random = new Random();
        jugadorX = random.nextBoolean(); // true para 'X', false para 'O'
        String primerJugador = jugadorX ? "X" : "O";
        JOptionPane.showMessageDialog(this, "El jugador " + primerJugador + " empieza!");

        setVisible(true); // Hace visible la ventana
    }

    // Método llamado cuando se hace clic en un botón
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonClicado = (JButton) e.getSource(); // Obtiene el botón que fue clicado
        if (botonClicado.getText().equals("")) { // Verifica si el botón está vacío
            if (jugadorX) { // Si es el turno del jugador 'X'
                botonClicado.setText("X"); // Establece el texto del botón a 'X'
            } else { // Si es el turno del jugador 'O'
                botonClicado.setText("O"); // Establece el texto del botón a 'O'
            }
            jugadorX = !jugadorX; // Cambia el turno del jugador
            comprobarGanador(); // Verifica si hay un ganador o empate
        }
    }

    // Método para verificar si hay un ganador o un empate
    private void comprobarGanador() {
        // Comprobar filas y columnas para un ganador
        for (int i = 0; i < 3; i++) {
            // Verifica cada fila
            if (botones[i][0].getText().equals(botones[i][1].getText()) &&
                botones[i][1].getText().equals(botones[i][2].getText()) &&
                !botones[i][0].getText().equals("")) {
                anunciarGanador(botones[i][0].getText()); // Anuncia el ganador
            }
            // Verifica cada columna
            if (botones[0][i].getText().equals(botones[1][i].getText()) &&
                botones[1][i].getText().equals(botones[2][i].getText()) &&
                !botones[0][i].getText().equals("")) {
                anunciarGanador(botones[0][i].getText()); // Anuncia el ganador
            }
        }

        // Comprobar diagonales para un ganador
        if (botones[0][0].getText().equals(botones[1][1].getText()) &&
            botones[1][1].getText().equals(botones[2][2].getText()) &&
            !botones[0][0].getText().equals("")) {
            anunciarGanador(botones[0][0].getText()); // Anuncia el ganador
        }
        if (botones[0][2].getText().equals(botones[1][1].getText()) &&
            botones[1][1].getText().equals(botones[2][0].getText()) &&
            !botones[0][2].getText().equals("")) {
            anunciarGanador(botones[0][2].getText()); // Anuncia el ganador
        }

        // Comprobar si el tablero está lleno para un empate
        boolean lleno = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botones[i][j].getText().equals("")) { // Si algún botón está vacío
                    lleno = false; // El tablero no está lleno
                    break;
                }
            }
        }
        if (lleno) { // Si el tablero está lleno
            JOptionPane.showMessageDialog(this, "Es un empate!"); // Muestra un mensaje de empate
            reiniciarTablero(); // Reinicia el tablero
        }
    }

    // Método para anunciar el ganador y reiniciar el tablero
    private void anunciarGanador(String ganador) {
        JOptionPane.showMessageDialog(this, "El jugador " + ganador + " ha ganado!"); // Muestra un mensaje con el ganador
        reiniciarTablero(); // Reinicia el tablero
    }

    // Método para reiniciar el tablero
    private void reiniciarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText(""); // Limpia el texto de cada botón
            }
        }
        // Determina aleatoriamente quién comienza para el nuevo juego
        Random random = new Random();
        jugadorX = random.nextBoolean(); // true para 'X', false para 'O'
        String primerJugador = jugadorX ? "X" : "O";
        JOptionPane.showMessageDialog(this, "El jugador " + primerJugador + " empieza!");
    }

    // Método principal para iniciar el juego
    public static void main(String[] args) {
        new TresEnRaya(); // Crea una nueva instancia de TresEnRaya
    }
}
