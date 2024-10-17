/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras.punto;

import conversorAngulos.ConversorAngulos;

/**
 *
 * @author Timoteo
 */
public class Punto2D {

    public double x;
    public double y;

    public Punto2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Punto2D(Punto3D punto, double angulo) {
        double anguloRadianes = ConversorAngulos.toRadianes(angulo);
        this.x = punto.x - (punto.z * Math.cos(anguloRadianes));
        this.y = punto.y - (punto.z * Math.sin(anguloRadianes));
    }
}
