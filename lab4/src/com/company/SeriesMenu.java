package com.company;

import com.company.Series.ArticlesSet;
import com.company.Series.BooksSet;
import com.company.Series.Seriesable;
import com.company.SeriesExceptions.IllegalIndexException;

import static com.company.Series.Series.*;

import java.util.Scanner;

public class SeriesMenu {
    public static final String line = "-------------------------------------------------------------------------------\n";

    // region prints
    public static void printRed(String str) {
        System.out.print("\u001B[31m" + str + "\u001B[0m");
    }

    public static void printRedLn(String str) {
        System.out.println("\u001B[31m" + str + "\u001B[0m");
    }

    public static void printGreen(String str) {
        System.out.print("\u001B[32m" + str + "\u001B[0m");
    }

    public static void printGreenLn(String str) {
        System.out.println("\u001B[32m" + str + "\u001B[0m");
    }

    public static void printExit() {
        System.out.print('\n' + "нажмите Enter, чтобы выйти в меню ... ");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
    // endregion

    // region gets
    public static int printGetInt() {
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

    public static int printGetIndex(int maxIndex) {
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

    public static void printTask(String task) {
        System.out.print('\n' + task + '\n' + line);
    }

    public static Seriesable[] printGetSeriesableArr() {
        int len;

        do {
            len = printGetInt();

            if (len < Seriesable.MIN_LEN_OF_ARR) {
                printRedLn("массив должен вмещать хотя бы" + Seriesable.MIN_LEN_OF_ARR + " элемент/-ов");
            } else if (len > Seriesable.MAX_LEN_OF_ARR) {
                printRedLn("слишком большая база");
            } else {
                Seriesable[] seriesableArr = new Seriesable[len];
                printGreenLn("массив размером в " + len + " элементов успешно создан");
                return seriesableArr;
            }
        } while (true);
    }

    public static void printDbAsTitlesOfEls(Seriesable[] db) {
        System.out.print("база данных: ");
        if (db == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n' + line);

            for (int i = 0; i < db.length; i++) {
                System.out.print("[" + i + "] ");
                if (db[i] == null) {
                    System.out.println("элемент не задан");
                } else {
                    System.out.println('«' + db[i].getTitle() + '»');
                }
            }
        }
    }

    public static void printSetElOfDb(Seriesable[] db) {
        if (db == null) {
            printRedLn("операция невозможна: база данных не задана");
        } else {
            System.out.println("задайте индекс элемента,\n" +
                    "который хотите изменить\n" +
                    "(нумерация начинается с нуля):");
            int index = printGetIndex(db.length - 1);

            Scanner scan = new Scanner(System.in);
            String str;

            System.out.print("задание элемента под индексом " + index + '\n' + line);
            do {
                System.out.print("выберите тип элемента\n" +
                        line +
                        "1 -- ArticleSeries\n" +
                        "2 -- BookSeries\n" +
                        line +
                        "выбор ... ");
                str = scan.nextLine();
                System.out.println();

                if (str.equals("1")) {
                    db[index] = printGetArticlesSeries();
                    break;
                } else if (str.equals("2")) {
                    db[index] = printGetBooksSeries();
                    break;
                } else {
                    printRedLn("ошибка: неверный пункт меню");
                }
            } while (true);
        }
    }

    private static ArticlesSet printGetArticlesSeries() {
        System.out.print("введите название сборника ................................. ");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();

        int numOfArticles = printGetNumOfElsInSeries();
        int numOfAbstractPages = printGetNumOfStartPages();
        ArticlesSet as = new ArticlesSet(title, numOfAbstractPages, numOfArticles);
        printGreenLn("сборник успешно создан");
        System.out.println();

        System.out.print("заполните сборник названиями статей и их количеством страниц\n" + line);
        printSetElsOfSeries(as);

        return as;
    }

    private static BooksSet printGetBooksSeries() {
        System.out.print("введите название сборника ........................... ");
        Scanner scan = new Scanner(System.in);
        String title = scan.nextLine();

        int numOfBooks = printGetNumOfElsInSeries();
        int numOfPrefacePages = printGetNumOfStartPages();
        BooksSet bs = new BooksSet(title, numOfPrefacePages, numOfBooks);
        printGreenLn("сборник успешно создан");
        System.out.println();

        System.out.print("заполните сборник названиями книг и их количеством страниц\n" + line);
        printSetElsOfSeries(bs);

        return bs;
    }

    private static int printGetNumOfElsInSeries() {
        int num;

        do {
            System.out.print("задание количества элементов в серии: ... ");
            num = printGetInt();

            if (num < Seriesable.MIN_NUM_OF_ELS_OF_SERIES) {
                printRedLn("серия должна содержать хотя бы " + Seriesable.MIN_NUM_OF_ELS_OF_SERIES + " элемент/-та/-ов");
            } else if (num > Seriesable.MAX_NUM_OF_ELS_OF_SERIES) {
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

    private static void printSetElsOfSeries(Seriesable s) {
        if (s == null) {
            printRedLn("операция невозможна: серия не задана");
        } else {
            for (int i = 0; i < s.getNumOfEls(); i++) {
                System.out.print("элемент под индексом  " + "[" + i + "]" + '\n' + line);
                try {
                    if (!printSetElOfSeries(s, i)) {
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

    private static boolean printSetElOfSeries(Seriesable s, int index) throws Exception {
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

    public static void printDb(Seriesable[] db) {
        System.out.print("база данных: ");
        if (db == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n' + line);

            for (int i = 0; i < db.length; i++) { // по элементам БД
                System.out.print("[" + i + "] ");
                printSeries(db[i]);
                System.out.print(line + line);
            }
        }
    }

    private static void printSeries(Seriesable s) {
        if (s == null) {
            printRedLn("серия не задана");
        } else {
            System.out.println('«' + s.getTitle() + '»');
            System.out.print(line);
            System.out.println(s);
        }
    }

    private static void printElsOfSeries(Seriesable s) {
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

    public static void printGetArrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] s) {
        Seriesable[] arr;

        try {
            arr = getArrWithTwoElsWithSameSumOfPagesWithoutStart(s);
            printGreenLn("база данных успешно разделена");
            System.out.println();

            printDb(arr);
        } catch (Exception exc) {
            printRedLn(exc.getMessage());
        }
    }

    public static void printSplitDbIntoTwoArticlesAndBooksArrs(Seriesable[] s) {
        if (s == null) {
            printRedLn("операция невозможна: база данных не задана");
        } else {
            try {
                ArticlesSet[] as = getArticlesSeriesArrFromSeriesableArr(s);
                BooksSet[] bs = getBooksSeriesArrFromSeriesableArr(s);
                printGreenLn("база данных разбита на два массива, в которых хранятся однотипные элементы");
                System.out.println();

                printDb(as);
                printDb(bs);
            } catch (Exception exc) {
                printRedLn(exc.getMessage());
            }
        }
    }
}
