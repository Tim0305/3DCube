/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import figuras.figuras3D.cubo.Cubo;
import figuras.punto.Punto2D;
import figuras.punto.Punto3D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Timoteo
 */
public class CubeGUI extends JFrame implements ActionListener {

    private JButton cmdArriba;
    private JButton cmdAbajo;
    private JButton cmdIzquierda;
    private JButton cmdDerecha;
    private JButton cmdZoomP;
    private JButton cmdZoomN;
    private JButton cmdRX;
    private JButton cmdRY;
    private JButton cmdRZ;

    private int width;
    private int height;
    private Cubo cubo;
    private Punto3D posicion;
    private int rx;
    private int ry;
    private int rz;

    public CubeGUI(int width, int height) {
        super();

        // Incializacion de variables
        this.width = width;
        this.height = height;

        cmdArriba = new JButton("Arriba");
        cmdAbajo = new JButton("Abajo");
        cmdIzquierda = new JButton("Izquierda");
        cmdDerecha = new JButton("Derecha");
        cmdZoomP = new JButton("+");
        cmdZoomN = new JButton("-");
        cmdRX = new JButton("RX");
        cmdRY = new JButton("RY");
        cmdRZ = new JButton("RZ");

        cubo = new Cubo(100);
        posicion = new Punto3D(250, 250, 0);
        rx = 0;
        ry = 0;
        rz = 0;
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

        cmdArriba.addActionListener(this);
        cmdAbajo.addActionListener(this);
        cmdDerecha.addActionListener(this);
        cmdIzquierda.addActionListener(this);

        GroupLayout gl = new GroupLayout(getContentPane());

        // Horizontal Group
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(cmdRX, 20, 30, 50)
                                .addComponent(cmdRY, 20, 30, 50)
                                .addComponent(cmdRZ, 20, 30, 50)
                                .addComponent(cmdIzquierda, 50, 100, 150))
                .addGroup(gl.createParallelGroup()
                        .addComponent(cmdArriba, 50, 100, 150)
                        .addComponent(cmdAbajo, 50, 100, 150))
                .addGroup(gl.createParallelGroup()
                        .addComponent(cmdZoomP, 20, 30, 50)
                        .addComponent(cmdZoomN, 20, 30, 50)
                        .addComponent(cmdDerecha, 50, 100, 150))
        );

        // Vertical Group
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addContainerGap(500, 500)
                        .addGroup(gl.createParallelGroup()
                                .addComponent(cmdRX)
                                .addComponent(cmdZoomP))
                        .addGroup(gl.createParallelGroup()
                                .addComponent(cmdRY)
                                .addComponent(cmdZoomN))
                        .addComponent(cmdRZ)
                        .addComponent(cmdArriba)
                        .addGroup(gl.createParallelGroup()
                                .addComponent(cmdIzquierda)
                                .addComponent(cmdAbajo)
                                .addComponent(cmdDerecha)));
        setLayout(gl);
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        List<Punto3D> puntosVertices = cubo.getPuntosVertices();

        for (int i = 0; i < puntosVertices.size() - 1; i++) {
            Punto2D p1 = new Punto2D(puntosVertices.get(i), 30);
            Punto2D p2 = new Punto2D(puntosVertices.get(i + 1), 30);

            g2.drawLine((int) (p1.x + posicion.x), (int) (p1.y + posicion.y), (int) (p2.x + posicion.x), (int) (p2.y + posicion.y));
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmdArriba) {
            posicion.y -= 10;
            cubo.move(posicion);
        }
        if (e.getSource() == cmdAbajo) {
            posicion.y += 10;
            cubo.move(posicion);
        }
        if (e.getSource() == cmdIzquierda) {
            posicion.x -= 10;
            cubo.move(posicion);
        }
        if (e.getSource() == cmdAbajo) {
            posicion.x += 10;
            cubo.move(posicion);
        }

        if (e.getSource() == cmdRX) {
            rx += 10;
            cubo.setRotationX(rx);
        }

        repaint();
    }
}
