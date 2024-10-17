package matrix;

/**
 *
 * @author Timoteo
 */
public class Matrix {

    private int rows;
    private int columns;
    private double[][] matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
    }

    public Matrix(double matrix[][]) {
        this(matrix.length, matrix[0].length);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                setElement(i, j, matrix[i][j]);
            }
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows, matrix.columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                setElement(i, j, matrix.getElement(i, j));
            }
        }
    }

    public void setElement(int row, int col, double value) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            matrix[row][col] = value;
        } else {
            throw new RuntimeException("Indice fuera de rango");
        }
    }

    public double getElement(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            return matrix[row][col];
        } else {
            throw new RuntimeException("Indice fuera de rango");
        }
    }

    public Matrix multiplicarMatrix(Matrix matrix) {
        if (columns != matrix.rows) {
            throw new RuntimeException("Matrices invalidas al momento de multiplicar");
        }

        // El resultado de una mult siempre es asi
        Matrix res = new Matrix(rows, matrix.columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                double value = 0;

                for (int k = 0; k < columns; k++) {
                    value += this.matrix[i][k] * matrix.getElement(k, j);
                }

                res.setElement(i, j, value);
            }
        }

        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sb.append(matrix[i][j]).append("\t"); // Usa tabulador para separar columnas
            }
            sb.append("\n"); // Nueva línea para cada fila
        }
        return sb.toString();
    }

}
