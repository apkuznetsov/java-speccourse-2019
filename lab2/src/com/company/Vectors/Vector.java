package com.company.Vectors;

import java.lang.Math;

public class Vector {
    private double[] els;

    public Vector(int len) {
        els = new double[len];
    }

    public int getLen() {
        return els.length;
    }

    public double getEl(int index) {
        return els[index];
    }

    public double getMinVal() {
        double min = els[0];

        for (int i = 1; i < els.length; i++) {
            if (els[i] < min) {
                min = els[i];
            }
        }

        return min;
    }

    public double getMaxVal() {
        double max = els[0];

        for (int i = 1; i < els.length; i++) {
            if (els[i] > max) {
                max = els[i];
            }
        }

        return max;
    }

    public void setEl(int index, double val) {
        els[index] = val;
    }

    public void sortUpWithBubble() {
        boolean isSwaped;
        double temp;

        do {
            isSwaped = false;
            for (int i = 0; i < els.length - 1; i++) {
                if (els[i] > els[i + 1]) {
                    temp = els[i];
                    els[i] = els[i + 1];
                    els[i + 1] = temp;
                    isSwaped = true;
                }
            }
        } while (isSwaped);
    }

    public void sortDownWithBubble() {
        boolean isSwaped;
        double temp;

        do {
            isSwaped = false;
            for (int i = 0; i < els.length - 1; i++) {
                if (els[i] < els[i + 1]) {
                    temp = els[i];
                    els[i] = els[i + 1];
                    els[i + 1] = temp;
                    isSwaped = true;
                }
            }
        } while (isSwaped);
    }

    public double getEuclidNorm() {
        double sumOfSquaredEls = 0;

        for (double el : els) {
            sumOfSquaredEls += Math.pow(el, 2);
        }

        return Math.sqrt(sumOfSquaredEls);
    }

    public void multByNum(double num) {
        for (int i = 0; i < els.length; i++) {
            els[i] = els[i] * num;
        }
    }

    @Override
    public String toString() {
        StringBuilder strBuf = new StringBuilder();
        for (double el : els) {
            strBuf.append("\t").append(el);
        }

        return strBuf.toString();
    }
}
