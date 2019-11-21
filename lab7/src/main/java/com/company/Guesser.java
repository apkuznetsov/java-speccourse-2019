package com.company;

import java.util.Random;

public class Guesser {
    public static final int DEFAULT_MIN_NUMB = 0;
    public static final int DEFAULT_MAX_NUMB = 1000;

    private int minNumb;
    private int maxNumb;

    public Guesser(int minNumb, int maxNumb) {
        if (minNumb < DEFAULT_MIN_NUMB) {
            throw new IllegalArgumentException("слишком маленькое минимальное число");
        }

        if (maxNumb > DEFAULT_MAX_NUMB) {
            throw new IllegalArgumentException("слишком большое максимальное число");
        }

        if (maxNumb < minNumb) {
            throw new IllegalArgumentException("максимальное число больше минимального");
        }

        this.minNumb = minNumb;
        this.maxNumb = maxNumb;
    }

    private static int getRandInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("минимальное число больше максимального");
        }

        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }

    public int getMinNumb() {
        return minNumb;
    }

    public void setMinNumb(int newMin) {
        if (newMin < DEFAULT_MIN_NUMB) {
            throw new IllegalArgumentException("слишком маленькое минимальное число");
        }

        if (newMin > maxNumb) {
            throw new IllegalArgumentException("минимальное число больше максимального");
        }

        minNumb = newMin;
    }

    public int getMaxNumb() {
        return maxNumb;
    }

    public void setMaxNumb(int newMax) {
        if (newMax > DEFAULT_MAX_NUMB) {
            throw new IllegalArgumentException("слишком большое максимальное число");
        }

        if (newMax < minNumb) {
            throw new IllegalArgumentException("максимальное число меньше минимального");
        }

        maxNumb = newMax;
    }

    public int guess() {
        return getRandInt(minNumb, maxNumb);
    }
}
