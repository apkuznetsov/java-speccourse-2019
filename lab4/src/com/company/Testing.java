package com.company;

import com.company.Series.ArticlesSeries;
import com.company.Series.BooksSeries;
import com.company.Series.Seriesable;

import java.util.Random;

import static com.company.MenuPrints.printGreenLn;

class Testing {
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

    static Seriesable[] createAndFillInDbWithFiveElsAutomatically() {
        Seriesable[] sArr = createSerArrWithFiveEls();
        setElsInSerArrWithFiveEls(sArr);

        printGreenLn("база успешно создана и заполнена");

        return sArr;
    }

    private static Seriesable[] createSerArrWithFiveEls() {
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

    private static int getRandNumOfStartPages() {
        return getRandInt(Seriesable.MIN_NUM_OF_START_PAGES, Seriesable.MAX_NUM_OF_START_PAGES);
    }

    private static int getRandInt(int min, int max) {
        int num;

        Random rand = new Random();
        num = min + rand.nextInt(max - min + 1);

        return num;
    }

    private static void setElsInSerArrWithFiveEls(Seriesable[] sArr) {
        final int index0 = 0;
        final int index1 = 1;
        final int index2 = 2;
        final int index3 = 3;
        final int index4 = 4;

        sArr[index0].setEl(index0, EL_1);
        sArr[index0].setEl(index1, EL_2);
        sArr[index0].setEl(index2, EL_3);
        sArr[index0].setEl(index3, EL_4);
        sArr[index0].setEl(index4, EL_5);

        sArr[index1].setEl(index0, EL_6);
        sArr[index1].setEl(index1, EL_7);
        sArr[index1].setEl(index2, EL_8);
        sArr[index1].setEl(index3, EL_9);
        sArr[index1].setEl(index4, EL_10);

        sArr[index2].setEl(index0, EL_11);
        sArr[index2].setEl(index1, EL_12);
        sArr[index2].setEl(index2, EL_13);
        sArr[index2].setEl(index3, EL_14);
        sArr[index2].setEl(index4, EL_15);

        sArr[index3].setEl(index0, EL_16);
        sArr[index3].setEl(index1, EL_17);
        sArr[index3].setEl(index2, EL_18);
        sArr[index3].setEl(index3, EL_19);
        sArr[index3].setEl(index4, EL_20);

        sArr[index4].setEl(index0, EL_21);
        sArr[index4].setEl(index1, EL_22);
        sArr[index4].setEl(index2, EL_23);
        sArr[index4].setEl(index3, EL_24);
        sArr[index4].setEl(index4, EL_25);

        sArr[index0].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sArr[index0].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sArr[index0].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sArr[index0].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sArr[index0].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sArr[index1].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sArr[index1].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sArr[index1].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sArr[index1].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sArr[index1].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sArr[index2].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sArr[index2].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sArr[index2].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sArr[index2].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sArr[index2].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sArr[index3].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sArr[index3].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sArr[index3].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sArr[index3].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sArr[index3].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sArr[index4].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sArr[index4].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sArr[index4].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sArr[index4].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sArr[index4].setNumOfPagesOfEl(index4, getRandNumOfPages());
    }

    private static int getRandNumOfPages() {
        return getRandInt(Seriesable.MIN_NUM_OF_PAGES_OF_EL, Seriesable.MAX_NUM_OF_PAGES_OF_EL);
    }

    static Seriesable createAndFillSerAutomatically() {
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

    static void setTwoSerWithSameSumOfPagesWithoutStart(Seriesable[] sArr) {
        int lastIndex = sArr.length - 1;

        int firstIndex = getRandInt(0, lastIndex);
        int secondIndex = getRandInt(0, lastIndex);

        Seriesable s1 = sArr[firstIndex];
        Seriesable s2 = sArr[secondIndex];

        int sameNumOfPages;

        for (int i = 0; i < s1.getNumOfEls(); i++) {
            sameNumOfPages = s1.getNumOfPages(i);
            s2.setNumOfPagesOfEl(i, sameNumOfPages);
        }

        printGreenLn("база успешно создана и заполнена");
    }
}
