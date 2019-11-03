package com.company;

import com.company.Exceptions.NullSeriesableObjectException;
import com.company.Series.Seriesable;

import java.util.Scanner;

import static com.company.MenuPrints.*;

class Menu {
    public static void main(String[] args) {
        Seriesable[] sArr = null; // сборник серий (сборник сборников)
        Seriesable s = null;

        Scanner scan = new Scanner(System.in);
        String menuItem;

        do {
            System.out.print(LINE +
                    "РАБОТА С БАЗОЙ:\n" +
                    LINE +
                    " 1 -- вывести полную информацию базы\n" +
                    LINE +
                    " 2 -- создать базу\n" +
                    " 3 -- задание элемента базы\n" +
                    LINE +
                    " 4 -- найти в базе объекты,\n" +
                    "      функциональный метод которых возвращают одинаковый результат,\n" +
                    "      поместить такие объекты в массив\n" +
                    " 5 -- разбить базу на два массива,\n" +
                    "      в которых будут храниться однотипные элементы\n" +
                    LINE +
                    " 6 -- считать базу из байтового потока\n" +
                    " 7 -- считать базу из текстового потока\n" +
                    " 8 -- десериализовать базу\n" +
                    LINE +
                    " 9 -- записать базу в байтовый поток\n" +
                    "10 -- записать базу в символьный поток\n" +
                    "11 -- сериализовать базу\n" +
                    LINE +
                    LINE +
                    "РАБОТА С ОБЪЕКТОМ:\n" +
                    LINE +
                    "12 -- показать содержимое объекта\n" +
                    LINE +
                    "13 -- создать и заполнить объект Seriesable\n" +
                    "14 -- считать из байтового потока\n" +
                    "15 -- считать из текстового потока\n" +
                    "16 -- десериализовать объект\n" +
                    LINE +
                    "17 -- записать объект в байтовый поток\n" +
                    "18 -- записать объект в символьный поток\n" +
                    "19 -- сериализовать объект\n" +
                    LINE +
                    LINE +
                    "ДЛЯ ТЕСТИРОВАНИЯ:\n" +
                    LINE +
                    "-1 -- создать и заполнить базу автоматически\n" +
                    "-2 -- создать и заполнить базу автоматически так,\n" +
                    "      чтобы были элементы,\n" +
                    "      у которых функциональные методы возвращают одинаковый результат\n" +
                    LINE +
                    "-3 -- создать и заполнить объект Seriesable автоматически\n" +
                    LINE +
                    "0 -- выйти\n" +
                    LINE +
                    "выбор ... ");
            menuItem = scan.nextLine();

            switch (menuItem) {
                // region РАБОТА С БАЗОЙ
                case "1":
                    printTask(" 1 -- вывести полную информацию базы");
                    printSerArr(sArr);
                    break;

                case "2":
                    printTask(" 2 -- создать базу");
                    System.out.print("задание размера базы: ");
                    sArr = printGetSerArr();
                    break;

                case "3":
                    printTask(" 3 -- задание элемента базы");
                    printSerArrAsTitlesOfEls(sArr);
                    System.out.println();
                    printSetElOfArr(sArr);
                    break;

                case "4":
                    printTask(" 4 -- найти в базе объекты,\n" +
                            "      функциональный метод которых возвращают одинаковый результат,\n" +
                            "      поместить такие объекты в массив");
                    printGetArrWithTwoElsWithSameSumOfPagesWithoutStart(sArr);
                    break;

                case "5":
                    printTask(" 5 -- разбить базу на два массива,\n" +
                            "      в которых будут храниться однотипные элементы");
                    printSplitArrIntoTwoArticlesAndBooksArrs(sArr);
                    break;

                case "6":
                    printTask(" 6 -- считать базу из байтового потока");
                    try {
                        sArr = printInputBytesAsSerArr();
                    } catch (NullSeriesableObjectException exc) {
                        printRedLn(exc.getMessage());
                    }
                    break;

                case "7":
                    printTask(" 7 -- считать базу из текстового потока");
                    try {
                        sArr = printReadTextAsSerArr();
                    } catch (NullSeriesableObjectException exc) {
                        printRedLn(exc.getMessage());
                    }
                    break;

                case "8":
                    printTask(" 8 -- десериализовать базу");
                    try {
                        sArr = printDeserializeSerArr();
                    } catch (NullSeriesableObjectException exc) {
                        printRedLn(exc.getMessage());
                    }
                    break;

                case "9":
                    printTask(" 9 -- записать базу в байтовый поток");
                    printOutputSerArrAsBytes(sArr);
                    break;

                case "10":
                    printTask("10 -- записать базу в символьный поток");
                    printWriteSerArrAsText(sArr);
                    break;

                case "11":
                    printTask("11 -- сериализовать базу");
                    printSerializeSerArr(sArr);
                    break;
                // endregion

                // region РАБОТА С ОБЪЕКТОМ
                case "12":
                    printTask("12 -- показать содержимое объекта");
                    System.out.println(s);
                    break;

                case "13":
                    printTask("13 -- создать и заполнить объект Seriesable");
                    s = printGetAndSetSer();
                    break;

                case "14":
                    printTask("14 -- считать из байтового потока");
                    try {
                        s = printInputBytesAsSer();
                    } catch (NullSeriesableObjectException exc) {
                        printRedLn(exc.getMessage());
                    }
                    break;

                case "15":
                    printTask("15 -- считать из текстового потока");
                    try {
                        s = printReadTextAsSer();
                    } catch (NullSeriesableObjectException exc) {
                        printRedLn(exc.getMessage());
                    }
                    break;

                case "16":
                    printTask("16 -- десериализовать объект");
                    try {
                        s = printDeserializeSer();
                    } catch (NullSeriesableObjectException exc) {
                        printRedLn(exc.getMessage());
                    }
                    break;

                case "17":
                    printTask("17 -- записать объект в байтовый поток");
                    printOutputSerAsBytes(s);
                    break;

                case "18":
                    printTask("18 -- записать объект в текстовый поток");
                    printWriteSerAsText(s);
                    break;

                case "19":
                    printTask("19 -- сериализовать объект");
                    printSerializeSer(s);
                    break;
                // endregion

                // region ДЛЯ ТЕСТИРОВАНИЯ
                case "-1":
                    printTask("-1 -- создать и заполнить базу автоматически");
                    sArr = Testing.createAndFillInDbWithFiveElsAutomatically();
                    break;

                case "-2":
                    printTask("-2 -- создать и заполнить базу автоматически так,\n" +
                            "      чтобы были элементы,\n" +
                            "      у которых функциональные методы возвращают одинаковый результат");
                    sArr = Testing.createAndFillInDbWithFiveElsAutomatically();
                    Testing.setTwoSerWithSameSumOfPagesWithoutStart(sArr);
                    break;

                case "-3":
                    printTask("-3 -- создать и заполнить объект Seriesable автоматически");
                    s = Testing.createAndFillSerAutomatically();
                    break;
                // endregion

                default:
                    break;
            }
            printExit();
            System.out.println();
        } while (!menuItem.equals("0"));
    }
}
