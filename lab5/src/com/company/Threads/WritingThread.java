package com.company.Threads;

import com.company.Series.Seriesable;
import com.company.Testing;

public class WritingThread extends Thread {
    private Seriesable s;

    public WritingThread(Seriesable s) {
        this.s = s;
    }

    @Override
    public void run() {
        if (s == null) {
            System.out.println("операция невозможна: объект не задан");
            return;
        }

        for (int index = 0, numOfPages = 0; index < s.getNumOfEls(); index++) {
            s.setEl(index, "Мой текст 1" + index);
            numOfPages = Testing.getRandNumOfPages();
            s.setNumOfPagesOfEl(index, numOfPages);
            System.out.println("WRITE " + numOfPages + " to   position " + index);
        }
    }
}
