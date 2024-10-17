/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conversorAngulos;

/**
 *
 * @author Timoteo
 */
public class ConversorAngulos {

    public static double toGrados(double radianes) {
        return radianes * 180 / Math.PI;
    }

    public static double toRadianes(double grados) {
        return grados * Math.PI / 180;
    }
}
