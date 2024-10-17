package main;

/**
 *
 * @author Timoteo
 */
import conversorAngulos.ConversorAngulos;
import matrix.Matrix;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        double[][] datosMatrizHomogenea = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};

        Matrix matrizHomogenea = new Matrix(datosMatrizHomogenea);
        double[][] x1 = {{1, 5}, {3, 2}};
        double[][] x2 = {{6, 9}, {1, 2}};

        Matrix m1 = new Matrix(x1);
        Matrix m2 = new Matrix(x2);
        Matrix res = m1.multiplicarMatrix(m2);

    }

}
