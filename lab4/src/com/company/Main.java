package com.company;

import com.company.Exceptions.NullSeriesableObjectException;
import com.company.Series.Seriesable;

import java.util.Scanner;

import static com.company.Menu.*;

public class Main {
    public static void main(String[] args) {
        Seriesable[] db = null; // сборник серий (сборник сборников)
        Seriesable set = null;


        Scanner scan = new Scanner(System.in);
        String m;

        do {
            System.out.print("РАБОТА С БАЗОЙ:\n" +
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
                    "РАБОТА С ОБЪЕКТОМ:\n" +
                    line +
                    " 6 -- создать и заполнить объект Seriesable\n" +
                    " 7 -- считать из байтового потока\n" +
                    " 8 -- считать из текстового потока\n" +
                    " 9 -- десериализовать объект\n" +
                    line +
                    "10 -- записать объект в байтовый поток\n" +
                    "11 -- записать объект в символьный поток\n" +
                    "12 -- сериализовать объект\n" +
                    line +
                    "13 -- показать содержимое объекта\n" +
                    line +
                    "ДЛЯ ТЕСТИРОВАНИЯ:\n" +
                    line +
                    "-1 -- создать и заполнить базу автоматически\n" +
                    "-2 -- создать и заполнить базу автоматически так,\n" +
                    "      чтобы были элементы,\n" +
                    "      у которых функциональные методы возвращают одинаковый результат\n" +
                    line +
                    "-3 -- создать и заполнить объект Seriesable автоматически\n" +
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

                case "6":
                    printTask(" 6 -- создать и заполнить объект Seriesable");
                    set = printGetAndSetSeriesable();
                    break;

                case "7":
                    printTask(" 7 -- считать из байтового потока");
                    try {
                        set = printInputBytesAsSeriesableAndGet();
                    } catch (NullSeriesableObjectException exc) {
                        printRedLn(exc.getMessage());
                    }
                    break;

                case "8":
                    printTask(" 8 -- считать из текстового потока");
                    try {
                        set = printReadTextAsSeriesableAndGet();
                    } catch (NullSeriesableObjectException exc) {
                        printRedLn(exc.getMessage());
                    }
                    break;

                case "9":
                    printTask(" 9 -- десериализовать объект");
                    try {
                        set = printDeserializeSeriesableAndGet();
                    } catch (NullSeriesableObjectException exc) {
                        printRedLn(exc.getMessage());
                    }
                    break;

                case "10":
                    printTask("10 -- записать объект в байтовый поток");
                    printOutputSeriesableAsBytes(set);
                    break;

                case "11":
                    printTask("11 -- записать объект в текстовый поток");
                    printWriteSeriesableAsText(set);
                    break;

                case "12":
                    printTask("12 -- сериализовать объект");
                    printSerializeSeriesable(set);
                    break;

                case "13":
                    printTask("13 -- показать содержимое объекта");
                    System.out.println(set);
                    break;

                case "-1":
                    printTask("-1 -- создать и заполнить базу автоматически");
                    db = Testing.createAndFillInDbWithFiveElsAutomatically();
                    break;

                case "-2":
                    printTask("-2 -- создать и заполнить базу автоматически так,\n" +
                            "      чтобы были элементы,\n" +
                            "      у которых функциональные методы возвращают одинаковый результат");
                    db = Testing.createAndFillInDbWithFiveElsAutomatically();
                    Testing.setTwoSeriesableWithSameSumOfPagesWithoutStart(db);
                    break;

                case "-3":
                    printTask("-3 -- создать и заполнить объект Seriesable автоматически");
                    set = Testing.createAndFillSeriesableAutomatically();
                    break;

                default:
                    break;
            }
            printExit();
            System.out.println();
        } while (!m.equals("0"));
    }
}
