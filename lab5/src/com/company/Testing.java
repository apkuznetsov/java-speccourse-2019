package com.company;

import com.company.Series.ArticlesSeries;
import com.company.Series.BooksSeries;
import com.company.Series.Seriesable;
import com.company.Threads.ReadingThread;
import com.company.Threads.WritingThread;

import java.util.Random;

import static com.company.MenuItems.printGreenLn;

public class Testing {
    public static final int ITERATIONS_OF_STARTS_OF_THREADS = 5;

    // region titles
    private static final String TITLE_1 = "Каталог лучших услуг в географическом регионе";
    private static final String TITLE_2 = "Список лучших рассказов 1913 года";
    private static final String TITLE_3 = "Книга больших новостных фотографий";
    private static final String TITLE_4 = "Академический журнал, содержащий статьи по определенной теме";
    private static final String TITLE_5 = "Каталог состоит из текстов и фотографий";
    // endregion

    // region els
    private static final String EL_1 = "Мастер и Маргарита";
    private static final String EL_2 = "Преступление и наказание";
    private static final String EL_3 = "Война и мир";
    private static final String EL_4 = "Собачье сердце";
    private static final String EL_5 = "Идиот";
    private static final String EL_6 = "Братья Карамазовы";
    private static final String EL_7 = "Двенадцать стульев";
    private static final String EL_8 = "Мёртвые души";
    private static final String EL_9 = "Отцы и дети";
    private static final String EL_10 = "Анна Каренина";
    private static final String EL_11 = "Три товарища";
    private static final String EL_12 = "Граф Монте-Кристо";
    private static final String EL_13 = "Евгений Онегин";
    private static final String EL_14 = "Отверженные";
    private static final String EL_15 = "Горе от ума";
    private static final String EL_16 = "Золотой теленок";
    private static final String EL_17 = "Бесы";
    private static final String EL_18 = "Ревизор";
    private static final String EL_19 = "Капитанская дочка";
    private static final String EL_20 = "Триумфальная арка";
    private static final String EL_21 = "Униженные и оскорблённые";
    private static final String EL_22 = "Село Степанчиково и его обитатели";
    private static final String EL_23 = "Повести Белкина";
    private static final String EL_24 = "Приключения Шерлока Холмса";
    private static final String EL_25 = "Подросток";
    // endregion

    static Seriesable[] getSarrThenSetWithFiveElsAutomatically() {
        Seriesable[] sarr = getSarrWithFiveEls();
        setElsInSarrWithFiveEls(sarr);

        printGreenLn("массив успешно создан и заполнен");

        return sarr;
    }

    private static Seriesable[] getSarrWithFiveEls() {
        final int five = 5;

        Seriesable[] s = new Seriesable[five];

        s[0] = getSerWithRandGeneratedType(TITLE_1, getRandNumOfStartPages(), five);
        s[1] = getSerWithRandGeneratedType(TITLE_2, getRandNumOfStartPages(), five);
        s[2] = getSerWithRandGeneratedType(TITLE_3, getRandNumOfStartPages(), five);
        s[3] = getSerWithRandGeneratedType(TITLE_4, getRandNumOfStartPages(), five);
        s[4] = getSerWithRandGeneratedType(TITLE_5, getRandNumOfStartPages(), five);

        return s;
    }

    private static Seriesable getSerWithRandGeneratedType(String title, int numOfStartPages, int numOfEls) {
        Seriesable s;

        int choice = getRandInt(1, 2);

        if (choice == 1) {
            s = new ArticlesSeries(title, numOfStartPages, numOfEls);
        } else {
            s = new BooksSeries(title, numOfStartPages, numOfEls);
        }

        return s;
    }

    public static int getRandNumOfStartPages() {
        return getRandInt(Seriesable.MIN_NUM_OF_START_PAGES, Seriesable.MAX_NUM_OF_START_PAGES);
    }

    private static int getRandInt(int min, int max) {
        int num;

        Random rand = new Random();
        num = min + rand.nextInt(max - min + 1);

        return num;
    }

    private static void setElsInSarrWithFiveEls(Seriesable[] sarr) {
        final int index0 = 0;
        final int index1 = 1;
        final int index2 = 2;
        final int index3 = 3;
        final int index4 = 4;

        sarr[index0].setEl(index0, EL_1);
        sarr[index0].setEl(index1, EL_2);
        sarr[index0].setEl(index2, EL_3);
        sarr[index0].setEl(index3, EL_4);
        sarr[index0].setEl(index4, EL_5);

        sarr[index1].setEl(index0, EL_6);
        sarr[index1].setEl(index1, EL_7);
        sarr[index1].setEl(index2, EL_8);
        sarr[index1].setEl(index3, EL_9);
        sarr[index1].setEl(index4, EL_10);

        sarr[index2].setEl(index0, EL_11);
        sarr[index2].setEl(index1, EL_12);
        sarr[index2].setEl(index2, EL_13);
        sarr[index2].setEl(index3, EL_14);
        sarr[index2].setEl(index4, EL_15);

        sarr[index3].setEl(index0, EL_16);
        sarr[index3].setEl(index1, EL_17);
        sarr[index3].setEl(index2, EL_18);
        sarr[index3].setEl(index3, EL_19);
        sarr[index3].setEl(index4, EL_20);

        sarr[index4].setEl(index0, EL_21);
        sarr[index4].setEl(index1, EL_22);
        sarr[index4].setEl(index2, EL_23);
        sarr[index4].setEl(index3, EL_24);
        sarr[index4].setEl(index4, EL_25);

        sarr[index0].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sarr[index0].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sarr[index0].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sarr[index0].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sarr[index0].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sarr[index1].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sarr[index1].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sarr[index1].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sarr[index1].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sarr[index1].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sarr[index2].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sarr[index2].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sarr[index2].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sarr[index2].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sarr[index2].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sarr[index3].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sarr[index3].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sarr[index3].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sarr[index3].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sarr[index3].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sarr[index4].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sarr[index4].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sarr[index4].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sarr[index4].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sarr[index4].setNumOfPagesOfEl(index4, getRandNumOfPages());
    }

    public static int getRandNumOfPages() {
        return getRandInt(Seriesable.MIN_NUM_OF_PAGES_OF_EL, Seriesable.MAX_NUM_OF_PAGES_OF_EL);
    }

    static Seriesable getSerThenSetAutomatically() {
        Seriesable s;

        s = getSerWithRandGeneratedType(TITLE_1, getRandNumOfStartPages(), 5);

        s.setEl(0, EL_1);
        s.setEl(1, EL_2);
        s.setEl(2, EL_3);
        s.setEl(3, EL_4);
        s.setEl(4, EL_5);

        s.setNumOfPagesOfEl(0, getRandNumOfPages());
        s.setNumOfPagesOfEl(1, getRandNumOfPages());
        s.setNumOfPagesOfEl(2, getRandNumOfPages());
        s.setNumOfPagesOfEl(3, getRandNumOfPages());
        s.setNumOfPagesOfEl(4, getRandNumOfPages());

        printGreenLn("объект успешно создан и заполнен");

        return s;
    }

    static void setTwoSerWithSameSumOfPagesWithoutStart(Seriesable[] sarr) {
        int lastIndex = sarr.length - 1;

        int firstIndex = getRandInt(0, lastIndex);
        int secondIndex = getRandInt(0, lastIndex);

        Seriesable s1 = sarr[firstIndex];
        Seriesable s2 = sarr[secondIndex];

        int sameNumOfPages;

        for (int i = 0; i < s1.getNumOfEls(); i++) {
            sameNumOfPages = s1.getNumOfPagesOfEl(i);
            s2.setNumOfPagesOfEl(i, sameNumOfPages);
        }

        printGreenLn("массив успешно создан и заполнен");
    }

    static void testReadingAndWritingThreads(int iterationsOfStartsOfThreads) {
        int numOfStartPages = Testing.getRandNumOfStartPages();
        Seriesable s = new ArticlesSeries("Моё название", numOfStartPages, 5);

        for (int i = 0; i < iterationsOfStartsOfThreads; i++) {
            WritingThread wt = new WritingThread(s);
            ReadingThread rt = new ReadingThread(s);
            wt.start();
            rt.start();
        }
    }
}
