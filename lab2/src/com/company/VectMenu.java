package com.company;

import com.company.Vectors.Vector;

import java.util.Scanner;
import java.io.IOException;
import java.util.IllegalFormatException;

class VectMenu {
    public static final String line = "-----------------------------------------------\n";

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

    public static void clearOutput() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printExit() {
        System.out.print("нажмите Enter, чтобы выйти в меню ... ");
        try {
            System.in.read();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void printMenuItem(String item) {
        System.out.print(item + '\n' + line);
    }

    public static void printVect(Vector v) {
        if (v == null)
            printGreenLn("не задан");
        else {
            System.out.println(v);
        }
    }

    public static void printVects(Vector v1, Vector v2, Vector v3) {
        printGreen("1-ый вектор: ");
        printVect(v1);

        printGreen("2-ой вектор: ");
        printVect(v2);

        printGreen("3-ий вектор: ");
        printVect(v3);
    }

    public static void printVectInfo(Vector v) {
        if (v == null) {
            System.out.println("вектор не задан");
        } else {
            System.out.println("норма вектора  ... " + roundToTwoDecPlaces(v.getEuclidNorm()));
            System.out.println("мин.  значение ... " + roundToTwoDecPlaces(v.getMinVal()));
            System.out.println("макс. значение ... " + roundToTwoDecPlaces(v.getMaxVal()));
        }
    }

    private static double roundToTwoDecPlaces(double num) {
        return (double) Math.round(num * 100) / 100;
    }

    private static double printGetNum() {
        double num;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите число ... ");
            str = scan.nextLine();
            System.out.println();

            try {
                num = Double.parseDouble(str);
                break;
            } catch (NumberFormatException exc) {
                printRedLn("ошибка: неверный формат числа");
            } catch (Exception exc) {
                printRedLn("ошибка: введённая строка не является числом");
            }
        } while (true);

        return num;
    }

    private static int printGetIndex(Vector v) {
        int index;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите индекс элемента,\n" +
                    "который хотите изменить\n" +
                    "(нумерация начинается с нуля)\n" +
                    line +
                    "выбор ... ");
            str = scan.nextLine();
            System.out.println();

            try {
                index = Integer.parseInt(str);
                if (index < 0 || index > v.getLen() - 1) {
                    throw new IndexOutOfBoundsException();
                }
                break;
            } catch (IndexOutOfBoundsException exc) {
                printRedLn("ошибка: неверный индекс");
            } catch (IllegalFormatException exc) {
                printRedLn("ошибка: неверный формат введёной строки");
            } catch (Exception exc) {
                printRedLn("ошибка: неверная строка");
            }
        } while (true);

        return index;
    }

    public static Vector printGetVect() {
        Vector v;
        double el;
        Scanner scan = new Scanner(System.in);
        String str;
        String[] strSplited;

        do {
            System.out.println("введите вектор так, чтобы элементы разделялись пробелом:");
            str = scan.nextLine();
            strSplited = str.split(" ");

            try {
                v = new Vector(strSplited.length);
                for (int i = 0; i < v.getLen(); i++) {
                    el = Double.parseDouble(strSplited[i]);
                    v.setEl(i, el);
                }
                break;
            } catch (IllegalFormatException exc) {
                printRedLn("ошибка: неверный формат введёной строки");
            } catch (Exception exc) {
                printRedLn("ошибка: неверная строка");
            }
        } while (true);

        return v;
    }

    public static void printSortVect(Vector v) {
        Scanner scan = new Scanner(System.in);
        String m;

        do {
            System.out.print("выберите в каком порядке хотите отсортировать вектор\n" +
                    line +
                    "1 -- в порядке возрастания\n" +
                    "2 -- в порядке убывания\n" +
                    line +
                    "выбор ... ");
            m = scan.next();
            System.out.println();

            if (m.equals("1")) {
                v.sortUpWithBubble();
                System.out.print("вектор в порядке возрастания:" + v);
                break;
            } else if (m.equals("2")) {
                v.sortDownWithBubble();
                System.out.print("вектор в порядке убывания:" + v);
                break;
            } else {
                printRedLn("ошибка: неверный пункт меню");
                System.out.println();
            }
        } while (true);
    }

    public static void printMultVectByNum(Vector v) {
        if (v == null) {
            printRedLn("ошибка: вектор не задан");
        } else {
            double num = printGetNum();
            v.multByNum(num);
            printGreenLn("вектор умножен на число:\n" + v);
        }
    }

    private static void printVectByGetEl(Vector v) {
        if (v == null) {
            System.out.println("не задан");
        } else {
            StringBuilder strBuf = new StringBuilder();
            for (int i = 0; i < v.getLen(); i++) {
                strBuf.append("\t").append(v.getEl(i));
            }
            System.out.println(strBuf.toString());
        }
    }

    public static void printChangeEl(Vector v) {
        printVectByGetEl(v);

        if (v == null) {
            printRedLn("операция невозможна: вектор не задан");
        } else {
            System.out.print(line);

            int index;
            index = printGetIndex(v);

            double num = printGetNum();
            v.setEl(index, num);

            printGreenLn("элемент под индексом " + index + " успешно замён на " + num);
            printVectByGetEl(v);
        }
    }
}
