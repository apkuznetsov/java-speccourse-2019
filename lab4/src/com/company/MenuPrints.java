package com.company;

import com.company.Exceptions.IllegalIndexException;
import com.company.Exceptions.NullSeriesableObjectException;
import com.company.Series.ArticlesSeries;
import com.company.Series.BooksSeries;
import com.company.Series.InputAndOutputSeriesable;
import com.company.Series.Seriesable;

import java.io.*;
import java.util.Scanner;

import static com.company.Series.InputAndOutputSeriesable.*;
import static com.company.Series.InputAndOutputSeriesableArray.*;
import static com.company.Series.Series.*;

class MenuPrints {
    // region константы
    static final String LINE = "-------------------------------------------------------------------------------\n";

    private static final String BYTES_FILE_WITH_SER = "serAsBytes.bin";
    private static final String TEXT_FILE_WITH_SER = "serAsText.txt";
    private static final String SERIALIZED_FILE_WITH_SER = "serSerialized.bin";

    private static final String BYTES_FILE_WITH_SER_ARR = "serArrAsBytes.bin";
    private static final String TEXT_FILE_WITH_SER_ARR = "serArrAsText.txt";
    private static final String SERIALIZED_FILE_WITH_SER_ARR = "serArrSerialized.bin";
    // endregion

    // region принты
    static void printRed(String str) {
        System.out.print("\u001B[31m" + str + "\u001B[0m");
    }

    static void printRedLn(String str) {
        System.out.println("\u001B[31m" + str + "\u001B[0m");
    }

    static void printGreen(String str) {
        System.out.print("\u001B[32m" + str + "\u001B[0m");
    }

    static void printGreenLn(String str) {
        System.out.println("\u001B[32m" + str + "\u001B[0m");
    }

    static void printTask(String task) {
        System.out.print('\n' + task + '\n' + LINE);
    }

    static void printExit() {
        System.out.print('\n' + "нажмите Enter, чтобы выйти в меню ... ");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
    // endregion

    // region вывести БД
    static void printSerArrAsTitlesOfEls(Seriesable[] sArr) {
        System.out.print("база данных: ");
        if (sArr == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n' + LINE);

            for (int i = 0; i < sArr.length; i++) {
                System.out.print("[" + i + "] ");
                if (sArr[i] == null) {
                    System.out.println("элемент не задан");
                } else {
                    System.out.println('«' + sArr[i].getTitle() + '»');
                }
            }
        }
    }

    static void printSerArr(Seriesable[] sArr) {
        System.out.print("база данных: ");
        if (sArr == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n' + LINE);

            for (int i = 0; i < sArr.length; i++) { // по элементам БД
                System.out.print("[" + i + "] ");
                printSer(sArr[i]);
                System.out.print(LINE + LINE);
            }
        }
    }

    private static void printSer(Seriesable s) {
        if (s == null) {
            printRedLn("серия не задана");
        } else {
            System.out.println('«' + s.getTitle() + '»');
            System.out.print(LINE);
            System.out.println(s);
        }
    }

    private static void printElsOfSer(Seriesable s) {
        if (s == null) {
            System.out.println("серия не задана");
        } else {
            for (int i = 0; i < s.getNumOfEls(); i++) {
                System.out.print(i + ") ");

                if (s.getEl(i) == null) {
                    printRedLn("элемент на задан");
                } else {
                    System.out.println(s.getEl(i) +
                            " (кол-во страниц -- " + s.getNumOfPages(i) + ')');
                }
            }
        }
    }
    // endregion

    // region геты
    static Seriesable[] printGetSerArr() {
        int len;

        do {
            len = printGetInt();

            if (len < Seriesable.MIN_LEN_OF_ARR) {
                printRedLn("массив должен вмещать хотя бы" + Seriesable.MIN_LEN_OF_ARR + " элемент/-ов");
            } else if (len > Seriesable.MAX_LEN_OF_ARR) {
                printRedLn("слишком большая база");
            } else {
                Seriesable[] sArr = new Seriesable[len];
                printGreenLn("массив размером в " + len + " элементов успешно создан");
                return sArr;
            }
        } while (true);
    }

    private static int printGetInt() {
        int num;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите число ... ");
            str = scan.nextLine();

            try {
                num = Integer.parseInt(str);
                break;
            } catch (NumberFormatException exc) {
                printRedLn("ошибка: введённая строка не является числом");
            }
        } while (true);

        return num;
    }

    private static int printGetIndex(int maxIndex) {
        int index;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите индекс ... ");
            str = scan.nextLine();
            System.out.println();

            try {
                index = Integer.parseInt(str);
                if (index < 0 || index > maxIndex) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalIndexException exc) {
                printRedLn("ошибка: неверный индекс");
            } catch (Exception exc) {
                printRedLn("ошибка: введённая строка не является числом");
            }
        } while (true);

        return index;
    }
    // endregion

    // region геты и сеты БД + объекта
    static void printSetElOfArr(Seriesable[] db) {
        if (db == null) {
            printRedLn("операция невозможна: база данных не задана");
        } else {
            System.out.println("задайте индекс элемента,\n" +
                    "который хотите изменить\n" +
                    "(нумерация начинается с нуля):");
            int index = printGetIndex(db.length - 1);

            Scanner scan = new Scanner(System.in);
            String str;

            System.out.print("задание элемента под индексом " + index + '\n' + LINE);
            do {
                System.out.print("выберите тип элемента\n" +
                        LINE +
                        "1 -- " + ArticlesSeries.class.getName() + "\n" +
                        "2 -- " + BooksSeries.class.getName() + "\n" +
                        LINE +
                        "выбор ... ");
                str = scan.nextLine();
                System.out.println();

                if (str.equals("1")) {
                    db[index] = printGetAndSetArticlesSer();
                    break;
                } else if (str.equals("2")) {
                    db[index] = printGetAndSetBooksSer();
                    break;
                } else {
                    printRedLn("ошибка: неверный пункт меню");
                }
            } while (true);
        }
    }

    private static ArticlesSeries printGetAndSetArticlesSer() {
        System.out.print("введите название сборника ................................. ");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();

        int numOfArticles = printGetNumOfElsInSer();
        int numOfAbstractPages = printGetNumOfStartPages();
        ArticlesSeries as = new ArticlesSeries(title, numOfAbstractPages, numOfArticles);
        printGreenLn("сборник успешно создан");
        System.out.println();

        System.out.print("заполните сборник названиями статей и их количеством страниц\n" + LINE);
        printSetElsOfSer(as);

        return as;
    }

    private static BooksSeries printGetAndSetBooksSer() {
        System.out.print("введите название сборника ........................... ");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();

        int numOfBooks = printGetNumOfElsInSer();
        int numOfPrefacePages = printGetNumOfStartPages();
        BooksSeries bs = new BooksSeries(title, numOfPrefacePages, numOfBooks);
        printGreenLn("сборник успешно создан");
        System.out.println();

        System.out.print("заполните сборник названиями книг и их количеством страниц\n" + LINE);
        printSetElsOfSer(bs);

        return bs;
    }

    private static int printGetNumOfElsInSer() {
        int num;

        do {
            System.out.print("задание количества элементов в серии: ... ");
            num = printGetInt();

            if (num < Seriesable.MIN_NUM_OF_ELS_OF_SER) {
                printRedLn("серия должна содержать хотя бы " + Seriesable.MIN_NUM_OF_ELS_OF_SER + " элемент/-та/-ов");
            } else if (num > Seriesable.MAX_NUM_OF_ELS_OF_SER) {
                printRedLn("слишком большая серия");
            } else {
                return num;
            }
        } while (true);
    }

    private static int printGetNumOfStartPages() {
        int num;

        do {
            System.out.print("задание количества страниц в предисловии/аннотации каждого элемента серии: ");
            num = printGetInt();

            if (num < Seriesable.MIN_NUM_OF_START_PAGES) {
                printRedLn("предисловие/аннотация должно/-на быть хотя бы в " + Seriesable.MIN_NUM_OF_START_PAGES + " страницу/-ц");
            } else if (num > Seriesable.MAX_NUM_OF_START_PAGES) {
                printRedLn("слишком много страниц в предисловии/аннотации");
            } else {
                return num;
            }
        } while (true);
    }

    private static void printSetElsOfSer(Seriesable s) {
        if (s == null) {
            printRedLn("операция невозможна: серия не задана");
        } else {
            for (int i = 0; i < s.getNumOfEls(); i++) {
                System.out.print("элемент под индексом  " + "[" + i + "]" + '\n' + LINE);
                try {
                    if (!printSetElOfSer(s, i)) {
                        i--;
                    }
                } catch (Exception exc) {
                    printRedLn(exc.getMessage());
                } finally {
                    System.out.println();
                }
            }
        }
    }

    private static boolean printSetElOfSer(Seriesable s, int index) throws Exception {
        if (s == null) {
            throw new UnsupportedOperationException("операция невозможна: серия не задана");
        } else {
            try {
                System.out.print("название ............................... ");
                Scanner scan = new Scanner(System.in);
                String title = scan.nextLine();
                s.setEl(index, title);

                System.out.print("количество страниц ... ");
                int numOfPages = printGetNumOfPages();
                s.setNumOfPagesOfEl(index, numOfPages);

                return true;
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exc) {
                printRed(exc.getMessage());
                return false;
            } catch (Exception exc) {
                throw new Exception(exc.getMessage());
            }
        }
    }

    private static int printGetNumOfPages() {
        int num;

        do {
            num = printGetInt();
            if (num < Seriesable.MIN_NUM_OF_PAGES_OF_EL) {
                printRedLn("ошибка: должна/-но быть хотя бы " + Seriesable.MIN_NUM_OF_PAGES_OF_EL + " страница/-цы/-ц");
            } else if (num > Seriesable.MAX_NUM_OF_PAGES_OF_EL) {
                printRedLn("слишком много страниц");
            } else {
                return num;
            }
        } while (true);
    }

    static Seriesable printGetAndSetSer() {
        Seriesable s;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("выберите тип элемента\n" +
                    LINE +
                    "1 -- " + ArticlesSeries.class.getName() + "\n" +
                    "2 -- " + BooksSeries.class.getName() + "\n" +
                    LINE +
                    "выбор ... ");
            str = scan.nextLine();
            System.out.println();

            if (str.equals("1")) {
                s = printGetAndSetArticlesSer();
                break;
            } else if (str.equals("2")) {
                s = printGetAndSetBooksSer();
                break;
            } else {
                printRedLn("ошибка: неверный пункт меню");
            }
        } while (true);

        return s;
    }
    // endregion

    // region деление базы
    static void printGetArrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] sArr) {
        Seriesable[] arrWithTwoElsWithSameSumOfPagesWithoutStart;

        try {
            arrWithTwoElsWithSameSumOfPagesWithoutStart = getSerArrWithTwoElsWithSameSumOfPagesWithoutStart(sArr);
            printGreenLn("база данных успешно разделена");
            System.out.println();

            printSerArr(arrWithTwoElsWithSameSumOfPagesWithoutStart);
        } catch (Exception exc) {
            printRedLn(exc.getMessage());
        }
    }

    static void printSplitArrIntoTwoArticlesAndBooksArrs(Seriesable[] sArr) {
        if (sArr == null) {
            printRedLn("операция невозможна: база данных не задана");
        } else {
            try {
                ArticlesSeries[] as = getArticlesSerArrFromSerArr(sArr);
                BooksSeries[] bs = getBooksSerArrFromSerArr(sArr);
                printGreenLn("база данных разбита на два массива, в которых хранятся однотипные элементы");
                System.out.println();

                printSerArr(as);
                printSerArr(bs);
            } catch (Exception exc) {
                printRedLn(exc.getMessage());
            }
        }
    }
    // endregion

    // region запись объекта
    static void printOutputSerAsBytes(Seriesable s) {
        if (s == null) {
            printRedLn("операция невозможна: объект не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(BYTES_FILE_WITH_SER);
                InputAndOutputSeriesable.outputSerAsBytes(s, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                printGreenLn("объект успешно записан в байтовый поток");
            } catch (IOException exc) {
                printRedLn(exc.getMessage());
            }
        }
    }

    static void printWriteSerAsText(Seriesable s) {
        if (s == null) {
            printRedLn("операция невозможна: объект не задан");
        } else {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(TEXT_FILE_WITH_SER);
                InputAndOutputSeriesable.writeSerAsText(s, fileWriter);
                fileWriter.flush();
                fileWriter.close();

                printGreenLn("объект успешно записан в текстовый поток");
            } catch (IOException exc) {
                printRedLn(exc.getMessage());
            }
        }
    }

    static void printSerializeSer(Seriesable s) {
        if (s == null) {
            printRedLn("операция невозможна: объект не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(SERIALIZED_FILE_WITH_SER);
                InputAndOutputSeriesable.serializeSer(s, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                printGreenLn("объект успешно сериализован");
            } catch (IOException exc) {
                printRedLn(exc.getMessage());
            }
        }
    }
    // endregion

    // region запись массива
    static void printOutputSerArrAsBytes(Seriesable[] sArr) {
        if (sArr == null) {
            printRedLn("операция невозможна: массив не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(BYTES_FILE_WITH_SER_ARR);
                outputSerArrAsBytes(sArr, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                printGreenLn("объект успешно записан в байтовый поток");
            } catch (IOException exc) {
                printRedLn(exc.getMessage());
            }
        }
    }

    static void printWriteSerArrAsText(Seriesable[] sArr) {
        if (sArr == null) {
            printRedLn("операция невозможна: массив не задан");
        } else {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(TEXT_FILE_WITH_SER_ARR);
                writeSerArrAsText(sArr, fileWriter);
                fileWriter.flush();
                fileWriter.close();

                printGreenLn("массив успешно записан в текстовый поток");
            } catch (IOException exc) {
                printRedLn(exc.getMessage());
            }
        }
    }

    static void printSerializeSerArr(Seriesable[] sArr) {
        if (sArr == null) {
            printRedLn("операция невозможна: массив не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(SERIALIZED_FILE_WITH_SER_ARR);
                serializeSerArr(sArr, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                printGreenLn("массив успешно сериализован");
            } catch (IOException exc) {
                printRedLn(exc.getMessage());
            }
        }
    }
    // endregion

    // region считывание объекта
    static Seriesable printInputBytesAsSer() throws NullSeriesableObjectException {
        Seriesable s = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(BYTES_FILE_WITH_SER);
            s = inputBytesAsSer(fileInputter);
            fileInputter.close();

            printGreenLn("объект успешно считан из байтового потока (файла)");
        } catch (IOException | NullSeriesableObjectException | ClassNotFoundException exc) {
            printRedLn(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }

    static Seriesable printReadTextAsSer() throws NullSeriesableObjectException {
        Seriesable s = null;

        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader(TEXT_FILE_WITH_SER);
            bufferedReader = new BufferedReader(fileReader);

            s = readTextAsSer(bufferedReader);

            bufferedReader.close();
            fileReader.close();

            printGreenLn("объект успешно считан из тектового потока (файла)");
        } catch (IOException | NullSeriesableObjectException | ClassNotFoundException exc) {
            printRedLn(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }

    static Seriesable printDeserializeSer() throws NullSeriesableObjectException {
        Seriesable s = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(SERIALIZED_FILE_WITH_SER);
            s = deserializeSer(fileInputter);
            fileInputter.close();

            printGreenLn("объект успешно десериализован (из файла)");
        } catch (IOException | NullSeriesableObjectException exc) {
            printRedLn(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }
    // endregion

    // region считывание массива
    static Seriesable[] printInputBytesAsSerArr() throws NullSeriesableObjectException {
        Seriesable[] sArr = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(BYTES_FILE_WITH_SER_ARR);
            sArr = inputBytesAsSerArr(fileInputter);
            fileInputter.close();

            printGreenLn("массив успешно считан из байтового потока (файла)");
        } catch (IOException | NullSeriesableObjectException | ClassNotFoundException exc) {
            printRedLn(exc.getMessage());
        }

        if (sArr == null) {
            throw new NullSeriesableObjectException("не удалось считать массив Seriesable[]");
        }

        return sArr;
    }

    static Seriesable[] printReadTextAsSerArr() throws NullSeriesableObjectException {
        Seriesable[] sArr = null;

        FileReader fileReader;
        try {
            fileReader = new FileReader(TEXT_FILE_WITH_SER_ARR);
            sArr = readTextAsSerArr(fileReader);
            fileReader.close();

            printGreenLn("массив успешно считан из тектового потока (файла)");
        } catch (IOException | NullSeriesableObjectException | ClassNotFoundException exc) {
            printRedLn(exc.getMessage());
        }

        if (sArr == null) {
            throw new NullSeriesableObjectException("не удалось считать массив Seriesable[]");
        }

        return sArr;
    }

    static Seriesable[] printDeserializeSerArr() throws NullSeriesableObjectException {
        Seriesable[] sArr = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(SERIALIZED_FILE_WITH_SER_ARR);
            sArr = deserializeSerArr(fileInputter);
            fileInputter.close();

            printGreenLn("массив успешно десериализована (из файла)");
        } catch (IOException | NullSeriesableObjectException exc) {
            printRedLn(exc.getMessage());
        }

        if (sArr == null) {
            throw new NullSeriesableObjectException("не удалось десериализовать массив Seriesable[]");
        }

        return sArr;
    }
    // endregion
}
