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

    // Propiedades fisicas del cubo
    private double lado;

    // Constantes
    private final int X_INDEX = 0;
    private final int Y_INDEX = 1;
    private final int Z_INDEX = 2;

    // Puntos vertices
    public List<Punto3D> puntosVerticesCubo;

    public Cubo(double lado) {
        this.lado = lado;

        initMatrizHomogenea();
        initMatrizRotacionX();
        initMatrizRotacionY();
        initMatrizRotacionZ();
        initMatrizScale();
        initMatricesTraslacion();

        // modificar el cubo
        update();
    }

    // Inicializacion de matrices
    private void initMatrizHomogenea() {
        double[][] datosMatrizHomogenea = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        matrizHomogenea = new Matrix(datosMatrizHomogenea);
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

    // Rotaciones
    private void rotateX(double angulo) {
        double radianes = ConversorAngulos.toRadianes(normalizarAngulo(angulo));
        rx.setElement(1, 1, Math.cos(radianes));
        rx.setElement(1, 2, -Math.sin(radianes));
        rx.setElement(2, 1, Math.sin(radianes));
        rx.setElement(2, 2, Math.cos(radianes));
    }

    private void rotateY(double angulo) {
        double radianes = ConversorAngulos.toRadianes(normalizarAngulo(angulo));
        ry.setElement(0, 0, Math.cos(radianes));
        ry.setElement(0, 2, Math.sin(radianes));
        ry.setElement(2, 0, -Math.sin(radianes));
        ry.setElement(2, 2, Math.cos(radianes));
    }

    private void rotateZ(double angulo) {
        double radianes = ConversorAngulos.toRadianes(normalizarAngulo(angulo));
        rz.setElement(0, 0, Math.cos(radianes));
        rz.setElement(0, 1, -Math.sin(radianes));
        rz.setElement(1, 0, Math.sin(radianes));
        rz.setElement(1, 1, Math.cos(radianes));
    }

    private double normalizarAngulo(double angulo) {
        if (angulo >= 0 && angulo < 360) {
            return angulo;
        }

        angulo = angulo % 360;

        if (angulo < 0) {
            return 360 + angulo;
        }
        return angulo;
    }

    // Cambiar la escala del cubo
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
        Matrix resultado = scale.multiplicarMatrix(rotaciones);

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

    public List<Punto3D> getPuntosVertices() {
        return puntosVerticesCubo;
    }
}
