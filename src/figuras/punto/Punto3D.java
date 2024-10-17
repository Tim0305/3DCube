/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras.punto;

/**
 *
 * @author Timoteo
 */
public class Punto3D {

    public double x;
    public double y;
    public double z;

    public Punto3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Punto3D(Punto2D punto) {
        this(punto.x, punto.y, 0);
    }
}
