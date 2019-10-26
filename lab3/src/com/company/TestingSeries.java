package com.company;

import com.company.Series.ArticlesSet;
import com.company.Series.BooksSet;
import com.company.Series.Seriesable;

import java.util.Random;

public class TestingSeries {
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

    public static Seriesable[] createAndFillInDbWithFiveElsAutomatically() {
        Seriesable[] db = createSeriesableArrWithFiveEls();
        setElsInSeriesableArrWithFiveEls(db);

        return db;
    }

    private static Seriesable[] createSeriesableArrWithFiveEls() {
        final int five = 5;

        Seriesable[] s = new Seriesable[five];

        s[0] = getSeriesableWithRandGeneratedType(TITLE_1, getRandNumOfStartPages(), five);
        s[1] = getSeriesableWithRandGeneratedType(TITLE_2, getRandNumOfStartPages(), five);
        s[2] = getSeriesableWithRandGeneratedType(TITLE_3, getRandNumOfStartPages(), five);
        s[3] = getSeriesableWithRandGeneratedType(TITLE_4, getRandNumOfStartPages(), five);
        s[4] = getSeriesableWithRandGeneratedType(TITLE_5, getRandNumOfStartPages(), five);

        return s;
    }

    private static Seriesable getSeriesableWithRandGeneratedType(String title, int numOfStartPages, int numOfEls) {
        Seriesable s;

        int choice = getRandInt(1, 2);

        if (choice == 1) {
            s = new ArticlesSet(title, numOfStartPages, numOfEls);
        } else {
            s = new BooksSet(title, numOfStartPages, numOfEls);
        }

        return s;
    }

    private static int getRandNumOfStartPages() {
        return getRandInt(Seriesable.MIN_NUM_OF_START_PAGES, Seriesable.MAX_NUM_OF_START_PAGES);
    }

    private static int getRandInt(int min, int max) {
        int num;

        Random rand = new Random();
        num = min + rand.nextInt(max - min + 1);

        return num;
    }

    private static void setElsInSeriesableArrWithFiveEls(Seriesable[] db) {
        final int index0 = 0;
        final int index1 = 1;
        final int index2 = 2;
        final int index3 = 3;
        final int index4 = 4;

        db[index0].setEl(index0, EL_1);
        db[index0].setEl(index1, EL_2);
        db[index0].setEl(index2, EL_3);
        db[index0].setEl(index3, EL_4);
        db[index0].setEl(index4, EL_5);

        db[index1].setEl(index0, EL_6);
        db[index1].setEl(index1, EL_7);
        db[index1].setEl(index2, EL_8);
        db[index1].setEl(index3, EL_9);
        db[index1].setEl(index4, EL_10);

        db[index2].setEl(index0, EL_11);
        db[index2].setEl(index1, EL_12);
        db[index2].setEl(index2, EL_13);
        db[index2].setEl(index3, EL_14);
        db[index2].setEl(index4, EL_15);

        db[index3].setEl(index0, EL_16);
        db[index3].setEl(index1, EL_17);
        db[index3].setEl(index2, EL_18);
        db[index3].setEl(index3, EL_19);
        db[index3].setEl(index4, EL_20);

        db[index4].setEl(index0, EL_21);
        db[index4].setEl(index1, EL_22);
        db[index4].setEl(index2, EL_23);
        db[index4].setEl(index3, EL_24);
        db[index4].setEl(index4, EL_25);

        db[index0].setNumOfPagesOfEl(index0, getRandNumOfPages());
        db[index0].setNumOfPagesOfEl(index1, getRandNumOfPages());
        db[index0].setNumOfPagesOfEl(index2, getRandNumOfPages());
        db[index0].setNumOfPagesOfEl(index3, getRandNumOfPages());
        db[index0].setNumOfPagesOfEl(index4, getRandNumOfPages());

        db[index1].setNumOfPagesOfEl(index0, getRandNumOfPages());
        db[index1].setNumOfPagesOfEl(index1, getRandNumOfPages());
        db[index1].setNumOfPagesOfEl(index2, getRandNumOfPages());
        db[index1].setNumOfPagesOfEl(index3, getRandNumOfPages());
        db[index1].setNumOfPagesOfEl(index4, getRandNumOfPages());

        db[index2].setNumOfPagesOfEl(index0, getRandNumOfPages());
        db[index2].setNumOfPagesOfEl(index1, getRandNumOfPages());
        db[index2].setNumOfPagesOfEl(index2, getRandNumOfPages());
        db[index2].setNumOfPagesOfEl(index3, getRandNumOfPages());
        db[index2].setNumOfPagesOfEl(index4, getRandNumOfPages());

        db[index3].setNumOfPagesOfEl(index0, getRandNumOfPages());
        db[index3].setNumOfPagesOfEl(index1, getRandNumOfPages());
        db[index3].setNumOfPagesOfEl(index2, getRandNumOfPages());
        db[index3].setNumOfPagesOfEl(index3, getRandNumOfPages());
        db[index3].setNumOfPagesOfEl(index4, getRandNumOfPages());

        db[index4].setNumOfPagesOfEl(index0, getRandNumOfPages());
        db[index4].setNumOfPagesOfEl(index1, getRandNumOfPages());
        db[index4].setNumOfPagesOfEl(index2, getRandNumOfPages());
        db[index4].setNumOfPagesOfEl(index3, getRandNumOfPages());
        db[index4].setNumOfPagesOfEl(index4, getRandNumOfPages());
    }

    private static int getRandNumOfPages() {
        return getRandInt(Seriesable.MIN_NUM_OF_PAGES_OF_EL, Seriesable.MAX_NUM_OF_PAGES_OF_EL);
    }

    public static void setTwoSeriesableWithSameSumOfPagesWithoutStart(Seriesable[] s) {
        int lastIndex = s.length - 1;

        int firstIndex = getRandInt(0, lastIndex);
        int secondIndex = getRandInt(0, lastIndex);

        Seriesable firstSeriesable = s[firstIndex];
        Seriesable secondSeriesable = s[secondIndex];

        int sameNumOfPages;

        for (int i = 0; i < firstSeriesable.getNumOfEls(); i++) {
            sameNumOfPages = firstSeriesable.getNumOfPages(i);
            secondSeriesable.setNumOfPagesOfEl(i, sameNumOfPages);
        }
    }
}
