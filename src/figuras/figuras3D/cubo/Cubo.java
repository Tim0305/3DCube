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

    // Matrices
    private Matrix matrizHomogenea;
    private List<Matrix> matricesTraslaciones;
    private Matrix rx;
    private Matrix ry;
    private Matrix rz;
    private Matrix scale;
    private Matrix matrizPosicion;

    // Propiedades fisicas del cubo
    private double lado;

    // Origen
    private Punto3D posicion;

    // Constantes
    private final int X_INDEX = 0;
    private final int Y_INDEX = 1;
    private final int Z_INDEX = 2;

    // Puntos vertices
    public List<Punto3D> puntosVerticesCubo;

    public Cubo(double lado) {
        this.lado = lado;

        initMatrizHomogenea();
        initMatrizPosicion();
        initMatrizRotacionX();
        initMatrizRotacionY();
        initMatrizRotacionZ();
        initMatrizScale();
        initMatricesTraslacion();

        // modificar el cubo
        update();
    }

    private void initMatrizHomogenea() {
        double[][] datosMatrizHomogenea = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        matrizHomogenea = new Matrix(datosMatrizHomogenea);
    }

    private void initMatrizPosicion() {
        matrizPosicion = new Matrix(matrizHomogenea);
    }

    private void initMatricesTraslacion() {
        matricesTraslaciones = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            Matrix m = new Matrix(matrizHomogenea);

            switch (i) {
                case 0:
                    m.setElement(X_INDEX, 3, lado);
                    break;
                case 1:
                    m.setElement(Y_INDEX, 3, lado);
                    break;
                case 2:
                    m.setElement(X_INDEX, 3, (lado * -1));
                    break;
                case 3:
                    m.setElement(Y_INDEX, 3, (lado * -1));
                    break;
                case 4:
                    m.setElement(Z_INDEX, 3, lado);
                    break;
                case 5:
                    m.setElement(X_INDEX, 3, lado);
                    break;
                case 6:
                    m.setElement(Y_INDEX, 3, lado);
                    break;
                case 7:
                    m.setElement(Z_INDEX, 3, (lado * -1));
                    break;
                case 8:
                    m.setElement(X_INDEX, 3, (lado * -1));
                    break;
                case 9:
                    m.setElement(Z_INDEX, 3, lado);
                    break;
                case 10:
                    m.setElement(Y_INDEX, 3, (lado * -1));
                    break;
                case 11:
                    m.setElement(X_INDEX, 3, lado);
                    break;
                case 12:
                    m.setElement(Z_INDEX, 3, (lado * -1));
                    break;
                case 13:
                    m.setElement(Y_INDEX, 3, lado);
                    break;
                case 14:
                    m.setElement(Z_INDEX, 3, lado);
                    break;
                case 15:
                    m.setElement(X_INDEX, 3, (lado * -1));
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

    private void initMatrizScale() {
        scale = new Matrix(matrizHomogenea);
        scale(1);
    }

    private void rotateX(double angulo) {
        rx.setElement(1, 1, Math.cos(ConversorAngulos.toRadianes(angulo)));
        rx.setElement(1, 2, Math.sin(ConversorAngulos.toRadianes(angulo)) * -1);
        rx.setElement(2, 1, Math.sin(ConversorAngulos.toRadianes(angulo)));
        rx.setElement(2, 2, Math.cos(ConversorAngulos.toRadianes(angulo)));
    }

    private void rotateY(double angulo) {
        ry.setElement(0, 0, Math.cos(ConversorAngulos.toRadianes(angulo)));
        ry.setElement(0, 2, Math.sin(ConversorAngulos.toRadianes(angulo)));
        ry.setElement(2, 0, Math.sin(ConversorAngulos.toRadianes(angulo)) * -1);
        ry.setElement(2, 2, Math.cos(ConversorAngulos.toRadianes(angulo)));
    }

    private void rotateZ(double angulo) {
        rz.setElement(0, 0, Math.cos(ConversorAngulos.toRadianes(angulo)));
        rz.setElement(0, 1, Math.sin(ConversorAngulos.toRadianes(angulo)) * -1);
        rz.setElement(1, 0, Math.sin(ConversorAngulos.toRadianes(angulo)));
        rz.setElement(1, 1, Math.cos(ConversorAngulos.toRadianes(angulo)));
    }

    private void scale(double scaleValue) {
        if (scaleValue <= 0) {
            throw new RuntimeException("No se puede tener un zoom nulo o negativo");
        }

        scale.setElement(0, 0, scaleValue);
        scale.setElement(1, 1, scaleValue);
        scale.setElement(2, 2, scaleValue);
    }

    private void update() {
        puntosVerticesCubo = new ArrayList<>();
        Matrix rotaciones = rx.multiplicarMatrix(ry).multiplicarMatrix(rz);
        Matrix resultado = scale.multiplicarMatrix(rotaciones).multiplicarMatrix(matrizPosicion);

        // Origen
        double x = resultado.getElement(X_INDEX, 3);
        double y = resultado.getElement(Y_INDEX, 3);
        double z = resultado.getElement(Z_INDEX, 3);
        puntosVerticesCubo.add(new Punto3D(x, y, z));

        // vertices
        for (Matrix matrizTraslacion : matricesTraslaciones) {
            resultado = resultado.multiplicarMatrix(matrizTraslacion);
            x = resultado.getElement(X_INDEX, 3);
            y = resultado.getElement(Y_INDEX, 3);
            z = resultado.getElement(Z_INDEX, 3);

            puntosVerticesCubo.add(new Punto3D(x, y, z));
        }
    }

    public void setRotationX(double angulo) {
        rotateX(angulo);
        update();
    }

    public void setRotationY(double angulo) {
        rotateY(angulo);
        update();
    }

    public void setRotationZ(double angulo) {
        rotateZ(angulo);
        update();
    }

    public void setScale(double scaleValue) {
        scale(scaleValue);
        update();
    }

    public void move(Punto3D posicion) {
        matrizPosicion.setElement(X_INDEX, 3, posicion.x);
        matrizPosicion.setElement(Y_INDEX, 3, posicion.y);
        matrizPosicion.setElement(Z_INDEX, 3, posicion.z);
        update();
    }

    public List<Punto3D> getPuntosVertices() {
        return puntosVerticesCubo;
    }
}
