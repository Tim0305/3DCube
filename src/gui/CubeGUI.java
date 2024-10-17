/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Timoteo
 */
public class CubeGUI extends JFrame {

    private JButton cmdArriba;
    private JButton cmdAbajo;
    private JButton cmdIzquierda;
    private JButton cmdDerecha;
    private JButton cmdZoomP;
    private JButton cmdZoomN;

    private int width;
    private int height;

    public CubeGUI(int width, int height) {
        super();

        // Incializacion de variables
        this.width = width;
        this.height = height;

        cmdArriba = new JButton("Arriba");
        cmdAbajo = new JButton("Abajo");
        cmdIzquierda = new JButton("Izquierda");
        cmdDerecha = new JButton("Derecha");
        cmdZoomN = new JButton("Menos");
        cmdZoomP = new JButton("Mas");

        config();
    }

    public CubeGUI() {
        this(500, 500);
    }

    private void config() {
        setTitle("Cubo 3D");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
