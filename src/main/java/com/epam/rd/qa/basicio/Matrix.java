package com.epam.rd.qa.basicio;

public class Matrix {

    private final int rows;
    private final int cols;
    private final double[][] mainMatrix;

    public Matrix(int rows, int cols) throws MatrixException {
        if (rows < 1 || cols < 1) {
            throw new MatrixException("");
        }
        this.rows = rows;
        this.cols = cols;
        this.mainMatrix = new double[this.rows][this.cols];
    }

    public Matrix(double[][] values) {
        if (values.length < 1 || values[0].length < 1) {
            throw new MatrixException();
        }

        for (int i = 0; i < values.length - 1; i++) {
            if (values[i].length != values[i + 1].length) {
                throw new MatrixException("Bad size of matrix");
            }
        }
        this.rows = values.length;
        this.cols = values[0].length;
        this.mainMatrix = values;
    }

    public int getRows() throws MatrixException {
        if (this.mainMatrix.length < 1) {
            throw new MatrixException("Bad size of matrix");
        }
        return this.mainMatrix.length;
    }

    public int getColumns() throws MatrixException {
        if (this.mainMatrix[0].length < 1) {
            throw new MatrixException("Bad size of matrix");
        }
        return this.mainMatrix[0].length;
    }

    public double[][] toArray() {
        return this.mainMatrix;
    }

    public double get(int row, int col) throws MatrixException {
        if (row < 0 || col < 0 || row >= this.getRows() || col >= this.getColumns()) {
            throw new MatrixException("Bad size of matrix");
        }
        return this.mainMatrix[row][col];
    }

    public void set(int row, int col, double value) {
        if (row < 0 || col < 0 || row >= this.getRows() || col >= this.getColumns()) {
            throw new MatrixException("You should check values before set");
        }
        this.mainMatrix[row][col] = value;
    }

    public Matrix add(Matrix other) throws MatrixException {
        if (this.getRows() != other.getRows() || this.getColumns() != other.getColumns()) {
            throw new MatrixException();
        }

        Matrix addMatrix = new Matrix(this.getRows(), this.getColumns());
        for (int r = 0; r < addMatrix.getRows(); r++) {
            for (int l = 0; l < addMatrix.getColumns(); l++) {
                addMatrix.set(r, l, this.get(r, l) + other.get(r, l));
            }
        }
        return addMatrix;
    }

    public Matrix subtract(Matrix other) throws MatrixException {
        if (this.getRows() != other.getRows() || this.getColumns() != other.getColumns()) {
            throw new MatrixException();
        }
        Matrix subtractMatrix = new Matrix(this.getRows(), other.getColumns());
        for (int r = 0; r < subtractMatrix.getRows(); r++) {
            for (int l = 0; l < subtractMatrix.getColumns(); l++) {
                subtractMatrix.set(r, l, this.get(r, l) - other.get(r, l));
            }
        }
        return subtractMatrix;
    }

    public Matrix multiply(Matrix other) throws MatrixException {
        if (this.getColumns() != other.getRows()) {
            throw new MatrixException();
        }
        double[][] tempArray = new double[this.getRows()][other.getColumns()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < other.getColumns(); j++) {
                for (int k = 0; k < other.getRows(); k++) {
                    tempArray[i][j] += this.mainMatrix[i][k] * other.get(k, j);
                }
            }
        }
        return new Matrix(tempArray);
    }
}
