package mk.ukim.finki.lab6;

import java.util.ArrayList;

class Matrix<T> {
    private int numRows;
    private int numColumns;
    private ArrayList<ArrayList<T>> matrix;

    public Matrix(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        matrix = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < numColumns; ++j)
                matrix.get(i).add(null);
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public T getElementAt(int row, int col) {
        return matrix.get(row).get(col);
    }

    public void setElementAt(int row, int col, T value) {
        matrix.get(row).set(col, value);
    }

    public void fill(T element) {
        for (int i = 0; i < numRows; ++i)
            for (int j = 0; j < numColumns; ++j)
                matrix.get(i).set(j, element);
    }

    public void insertRow(int row) {
        if (isValidRowIndex(row)) {
            matrix.add(row, new ArrayList<>());
            for (int j = 0; j < numColumns; ++j)
                matrix.get(row).add(null);
            ++numRows;
        } else throw new ArrayIndexOutOfBoundsException(row);
    }

    public void deleteRow(int row) {
        if (isValidRowIndex(row)) {
            matrix.remove(row);
            --numRows;
        } else throw new ArrayIndexOutOfBoundsException(row);
    }

    public void insertColumn(int col) {
        if (isValidColumnIndex(col)) {
            for (int i = 0; i < numRows; ++i)
                matrix.get(i).add(col, null);
            ++numColumns;
        } else throw new ArrayIndexOutOfBoundsException(col);
    }

    public void deleteColumn(int col) {
        if (isValidColumnIndex(col)) {
            for (int i = 0; i < numRows; ++i)
                matrix.get(i).remove(col);
            --numColumns;
        } else throw new ArrayIndexOutOfBoundsException(col);
    }

    public void resize(int rows, int cols) {
        if (rows < numRows && cols < numColumns) {
            numRows = rows;
            numColumns = cols;
        } else if (rows < numRows) {
            numRows = rows;
            for (int i = 0; i < numRows; ++i)
                for (int j = numColumns; j < cols; ++j)
                    matrix.get(i).add(j, null);
            numColumns = cols;
        } else if (cols < numColumns) {
            numColumns = cols;
            for (int i = numRows; i < rows; ++i) {
                matrix.add(i, new ArrayList<>());
                for (int j = 0; j < cols; ++j)
                    matrix.get(i).add(j, null);
            }
            numRows = rows;
        } else {
            for (int i = numRows; i < rows; ++i) {
                matrix.add(i, new ArrayList<>());
                for (int j = 0; j < cols; ++j)
                    matrix.get(i).add(j, null);
            }
            for (int i = 0; i < numRows; ++i)
                for (int j = numColumns; j < cols; ++j)
                    matrix.get(i).add(j, null);
            numRows = rows;
            numColumns = cols;
        }
    }

    private boolean isValidColumnIndex(int columnIndex) {
        return columnIndex >= 0 && columnIndex <= getNumColumns();
    }

    private boolean isValidRowIndex(int rowIndex) {
        return rowIndex >= 0 && rowIndex <= getNumRows();
    }
}
