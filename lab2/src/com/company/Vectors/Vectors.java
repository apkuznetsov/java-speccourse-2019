package com.company.Vectors;

public class Vectors {
    public static Vector getSumOfTwoVects(Vector v1, Vector v2) {
        if (v1.getLen() != v2.getLen()) {
            throw new UnsupportedOperationException("операция не определена: разные длины векторов");
        }

        Vector v3 = new Vector(v1.getLen());
        double val;

        for (int i = 0; i < v3.getLen(); i++) {
            val = v1.getEl(i) + v2.getEl(i);
            v3.setEl(i, val);
        }

        return v3;
    }

    public static double getScalarProduct(Vector v1, Vector v2) {
        if (v1.getLen() != v2.getLen()) {
            throw new UnsupportedOperationException("операция не определена: разные длины векторов");
        }

        double scalarProduct = 0;

        for (int i = 0; i < v1.getLen(); i++) {
            scalarProduct += v1.getEl(i) * v2.getEl(i);
        }

        return scalarProduct;
    }
}
