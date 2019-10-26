package com.company;

import com.company.Series.Seriesable;

import java.util.Scanner;

import static com.company.SeriesMenu.*;

public class Main {
    public static void main(String[] args) {
        Seriesable[] db = null; // сборник серий (сборник сборников)

        Scanner scan = new Scanner(System.in);
        String m;

        do {
            System.out.print("меню\n" +
                    line +
                    " 1 -- создать базу\n" +
                    " 2 -- задание элемента базы\n" +
                    " 3 -- вывести полную информацию базы\n" +
                    " 4 -- найти в массиве объекты,\n" +
                    "      функциональный метод которых возвращают одинаковый результат,\n" +
                    "      поместить такие объекты в другой массив\n" +
                    " 5 -- разбить исходный массив на два массива,\n" +
                    "      в которых будут храниться однотипные элементы\n" +
                    line +
                    "-1 -- создать и заполнить базу автоматически\n" +
                    "-2 -- создать и заполнить базу автоматически так,\n" +
                    "      чтобы были элементы,\n" +
                    "      у которых функциональные методы возвращают одинаковый результат\n" +
                    line +
                    "0 -- выйти\n" +
                    line +
                    "выбор ... ");
            m = scan.nextLine();

            switch (m) {
                case "1":
                    printTask(" 1 -- создать базу");
                    System.out.print("задание размера базы: ");
                    db = printGetSeriesableArr();
                    break;

                case "2":
                    printTask(" 2 -- задание элемента базы");
                    printDbAsTitlesOfEls(db);
                    System.out.println();

                    printSetElOfDb(db);
                    break;

                case "3":
                    printTask(" 3 -- вывести полную информацию базы");
                    printDb(db);
                    break;

                case "4":
                    printTask(" 4 -- найти в массиве объекты,\n" +
                            "      функциональный метод которых возвращают одинаковый результат,\n" +
                            "      поместить такие объекты в другой массив");
                    printGetArrWithTwoElsWithSameSumOfPagesWithoutStart(db);
                    break;

                case "5":
                    printTask(" 5 -- разбить исходный массив на два массива,\n" +
                            "      в которых будут храниться однотипные элементы");
                    printSplitDbIntoTwoArticlesAndBooksArrs(db);
                    break;

                case "-1":
                    printTask("-1 -- создать и заполнить базу автоматически");
                    db = TestingSeries.createAndFillInDbWithFiveElsAutomatically();
                    printGreenLn("база успешно создана и заполнена");
                    break;

                case "-2":
                    printTask("-2 -- создать и заполнить базу автоматически так,\n" +
                            "      чтобы были элементы,\n" +
                            "      у которых функциональные методы возвращают одинаковый результат");
                    db = TestingSeries.createAndFillInDbWithFiveElsAutomatically();
                    TestingSeries.setTwoSeriesableWithSameSumOfPagesWithoutStart(db);
                    printGreenLn("база успешно создана и заполнена");
                    break;

                default:
                    break;
            }
            printExit();
            System.out.println();
        } while (!m.equals("0"));
    }
}
