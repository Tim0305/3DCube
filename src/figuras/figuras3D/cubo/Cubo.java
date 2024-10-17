package figuras.figuras3D.cubo;

import conversorAngulos.ConversorAngulos;
import figuras.punto.Punto3D;
import java.util.ArrayList;
import java.util.List;
import matrix.Matrix;

/**
 *
 * @author Timoteo
 */
public class Cubo {

    private Matrix matrizHomogenea;
    private List<Matrix> matricesTraslaciones;
    private Matrix rx;
    private Matrix ry;
    private Matrix rz;
    private Matrix zoom;
    private double lado;
    private Punto3D origen;
    private final int X_INDEX = 0;
    private final int Y_INDEX = 1;
    private final int Z_INDEX = 2;

    public List<Punto3D> puntos;

    public Cubo(double lado, Punto3D origen) {
        this.lado = lado;
        matricesTraslaciones = new ArrayList<>();

        initMatrizHomogenea();
        initMatrizRotacionX();
        initMatrizRotacionY();
        initMatrizRotacionZ();
        initMatrizZoom();
        moverCubo(origen);
    }

    public Cubo(double lado) {
        this(lado, new Punto3D(0, 0, 0));
    }

    private void initMatrizHomogenea() {
        double[][] datosMatrizHomogenea = {{1, 0, 0, origen.x}, {0, 1, 0, origen.y}, {0, 0, 1, origen.z}, {0, 0, 0, 1}};
        matrizHomogenea = new Matrix(datosMatrizHomogenea);
    }

    private void moverCubo(Punto3D posicion) {
        this.origen = posicion;

        matricesTraslaciones.clear();

        for (int i = 0; i < 16; i++) {
            Matrix m = new Matrix(matrizHomogenea);

            switch (i) {
                case 0:
                    m.setElement(X_INDEX, 3, lado + origen.x);
                    break;
                case 1:
                    m.setElement(Y_INDEX, 3, lado + origen.y);
                    break;
                case 2:
                    m.setElement(X_INDEX, 3, (lado * -1) + origen.x);
                    break;
                case 3:
                    m.setElement(Y_INDEX, 3, (lado * -1) + origen.y);
                    break;
                case 4:
                    m.setElement(Z_INDEX, 3, lado + origen.z);
                    break;
                case 5:
                    m.setElement(X_INDEX, 3, lado + origen.x);
                    break;
                case 6:
                    m.setElement(Y_INDEX, 3, lado + origen.y);
                    break;
                case 7:
                    m.setElement(Z_INDEX, 3, (lado * -1) + origen.z);
                    break;
                case 8:
                    m.setElement(X_INDEX, 3, (lado * -1) + origen.x);
                    break;
                case 9:
                    m.setElement(Z_INDEX, 3, lado + origen.z);
                    break;
                case 10:
                    m.setElement(Y_INDEX, 3, (lado * -1) + origen.y);
                    break;
                case 11:
                    m.setElement(X_INDEX, 3, lado + origen.x);
                    break;
                case 12:
                    m.setElement(Z_INDEX, 3, (lado * -1) + origen.z);
                    break;
                case 13:
                    m.setElement(Y_INDEX, 3, lado + origen.y);
                    break;
                case 14:
                    m.setElement(Z_INDEX, 3, lado + origen.z);
                    break;
                case 15:
                    m.setElement(X_INDEX, 3, (lado * -1) + origen.x);
                    break;
            }

            matricesTraslaciones.add(m);
        }
    }

    private void initMatrizRotacionX() {
        rx = new Matrix(matrizHomogenea);
        rotateX(0);
    }

    private void initMatrizRotacionY() {
        ry = new Matrix(matrizHomogenea);
        rotateY(0);
    }

    private void initMatrizRotacionZ() {
        rz = new Matrix(matrizHomogenea);
        rotateZ(0);
    }

    private void initMatrizZoom() {
        zoom = new Matrix(matrizHomogenea);
        setZoom(1);
    }

    public void setZoom(double scale) {
        if (scale <= 0) {
            throw new RuntimeException("No se puede tener un zoom nulo o negativo");
        }

        zoom.setElement(0, 0, scale);
        zoom.setElement(1, 1, scale);
        zoom.setElement(2, 2, scale);
    }

    public void rotateX(double angulo) {
        rx.setElement(1, 1, Math.cos(ConversorAngulos.toRadianes(angulo)));
        rx.setElement(1, 2, Math.sin(ConversorAngulos.toRadianes(angulo)) * -1);
        rx.setElement(2, 1, Math.sin(ConversorAngulos.toRadianes(angulo)));
        rx.setElement(2, 2, Math.cos(ConversorAngulos.toRadianes(angulo)));
    }

    public void rotateY(double angulo) {
        ry.setElement(0, 0, Math.cos(ConversorAngulos.toRadianes(angulo)));
        ry.setElement(0, 2, Math.sin(ConversorAngulos.toRadianes(angulo)));
        ry.setElement(2, 0, Math.sin(ConversorAngulos.toRadianes(angulo)) * -1);
        ry.setElement(2, 2, Math.cos(ConversorAngulos.toRadianes(angulo)));
    }

    public void rotateZ(double angulo) {
        rz.setElement(0, 0, Math.cos(ConversorAngulos.toRadianes(angulo)));
        rz.setElement(0, 1, Math.sin(ConversorAngulos.toRadianes(angulo)) * -1);
        rz.setElement(1, 0, Math.sin(ConversorAngulos.toRadianes(angulo)));
        rz.setElement(1, 1, Math.cos(ConversorAngulos.toRadianes(angulo)));
    }
}
