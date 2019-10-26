package com.company;

import com.company.Vectors.Vector;
import com.company.Vectors.Vectors;

import java.util.Scanner;

import static com.company.VectMenu.*;

public class Main {
    public static void main(String[] args) {
        Vector v1 = null, v2 = null, v3 = null;

        String m;
        boolean isSelected;
        Scanner scan = new Scanner(System.in);
        do {
            clearOutput();
            printVects(v1, v2, v3);
            System.out.print("меню\n" +
                    line +
                    "1 -- создать вектор\n" +
                    "2 -- информация о векторах\n" +
                    "3 -- отсортировать вектор\n" +
                    "4 -- умножить вектор на число\n" +
                    line +
                    "5 -- сложить векторы\n" +
                    "6 -- вычислить скалярное произведение векторов\n" +
                    line +
                    "7 -- работа методов доступа к элементам вектора\n" +
                    line +
                    "0 -- выйти\n" +
                    line +
                    "выбор ... ");
            m = scan.next();
            System.out.println();

            clearOutput();
            printVects(v1, v2, v3);
            switch (m) {
                // region 1 -- создать вектор
                case "1":
                    printMenuItem("1 -- создать вектор");

                    String m2;
                    do {
                        System.out.print("выберите вектор, который хотите создать\n" +
                                line +
                                "1 -- 1-ый вектор\n" +
                                "2 -- 2-ой вектор\n" +
                                line +
                                "выбор ... ");
                        m2 = scan.next();
                        System.out.println();

                        if (m2.equals("1")) {
                            System.out.println("1-ый вектор:");
                            v1 = printGetVect();
                            v3 = null;
                            break;
                        } else if (m2.equals("2")) {
                            System.out.println("2-ый вектор:");
                            v2 = printGetVect();
                            v3 = null;
                            break;
                        } else {
                            printRedLn("ошибка: неверный пункт меню");
                        }
                    } while (true);

                    printExit();
                    break;
                // endregion

                // region 2 -- информация о векторах
                case "2":
                    printMenuItem("2 -- информация о векторах");

                    System.out.print("1-ый вектор:\n" + line);
                    printVectInfo(v1);
                    System.out.println();

                    System.out.print("2-ой вектор:\n" + line);
                    printVectInfo(v2);
                    System.out.println();

                    System.out.print("3-ой вектор:\n" + line);
                    printVectInfo(v3);
                    System.out.println();

                    printExit();
                    break;
                // endregion

                // region 3 -- отсортировать вектор
                case "3":
                    printMenuItem("3 -- отсортировать вектор");

                    isSelected = true;
                    do {
                        System.out.print("выберите вектор, который хотите отсортировать\n" +
                                line +
                                "1 -- 1-ый вектор\n" +
                                "2 -- 2-ой вектор\n" +
                                "3 -- 3-ий вектор\n" +
                                line +
                                "выбор ... ");
                        m2 = scan.next();
                        System.out.println();

                        switch (m2) {
                            case "1":
                                if (v1 != null) {
                                    System.out.print("1-ый вектор\n" + line);
                                    printSortVect(v1);
                                    v3 = null;
                                } else {
                                    printRedLn("ошибка: вектор не задан");
                                }
                                break;
                            case "2":
                                if (v2 != null) {
                                    System.out.print("2-ой вектор\n" + line);
                                    printSortVect(v2);
                                    v3 = null;
                                } else {
                                    printRedLn("ошибка: вектор не задан");
                                }
                                break;
                            case "3":
                                if (v3 != null) {
                                    System.out.print("3-ий вектор\n" + line);
                                    printSortVect(v3);
                                    v1 = null;
                                    v2 = null;
                                } else {
                                    printRedLn("ошибка: вектор не задан");
                                }
                                break;
                            default:
                                printRedLn("ошибка: неверный пункт меню");
                                isSelected = false;
                        }
                    } while (!isSelected);

                    printExit();
                    break;
                // endregion

                // region 4 -- умножить вектор на число
                case "4":
                    printMenuItem("4 -- умножить вектор на число");

                    isSelected = true;
                    do {
                        System.out.print("выберите вектор,\n" +
                                "на который хотитет умножить число\n" +
                                line +
                                "1 -- 1-ый вектор\n" +
                                "2 -- 2-ой вектор\n" +
                                "3 -- 3-ий вектор\n" +
                                line +
                                "выбор ... ");
                        m2 = scan.next();
                        System.out.println();

                        switch (m2) {
                            case "1":
                                printMultVectByNum(v1);
                                break;
                            case "2":
                                printMultVectByNum(v2);
                                break;
                            case "3":
                                printMultVectByNum(v3);
                                break;
                            default:
                                printRedLn("ошибка: неверный пункт меню");
                                isSelected = false;
                                break;
                        }
                    } while (!isSelected);

                    printExit();
                    break;
                // endregion

                // region 5 -- сложить векторы
                case "5":
                    printMenuItem("5 -- сложить векторы");

                    if (v1 == null || v2 == null) {
                        printRedLn("операция невозможна: вектор/-ы или не задан/-ы");
                    } else {
                        System.out.println("\t1-ый вектор:" + v1);
                        System.out.println("+");

                        System.out.println("\t2-ой вектор:" + v2);
                        System.out.print(line);

                        try {
                            v3 = Vectors.getSumOfTwoVects(v1, v2);
                            System.out.print("\t3-ий вектор:" + v3);
                        } catch (Exception exc) {
                            System.out.println(exc.getMessage());
                        }
                    }
                    System.out.println();

                    printExit();
                    break;
                // endregion

                // region 6 -- вычислить скалярное произведение векторов
                case "6":
                    printMenuItem("6 -- вычислить скалярное произведение векторов");

                    isSelected = true;
                    double scalar;
                    do {
                        System.out.print("выберите векторы, скалярное произведение\n" +
                                "которых хотите найти\n" +
                                line +
                                "1 -- 1-ый вектор и 2-ой вектор\n" +
                                "2 -- 2-ой вектор и 3-ий вектор\n" +
                                "3 -- 3-ий вектор и 1-ый вектор\n" +
                                line +
                                "выбор ... ");
                        m2 = scan.next();
                        System.out.println();

                        try {
                            switch (m2) {
                                case "1":
                                    if (v1 != null && v2 != null) {
                                        scalar = Vectors.getScalarProduct(v1, v2);
                                        System.out.println("скалярное произведение двух векторов: " + scalar);
                                    } else {
                                        printRedLn("ошибка: вектор не задан");
                                    }
                                    break;
                                case "2":
                                    if (v2 != null && v3 != null) {
                                        scalar = Vectors.getScalarProduct(v2, v3);
                                        System.out.println("скалярное произведение двух векторов: " + scalar);
                                    } else {
                                        printRedLn("ошибка: вектор не задан");
                                    }
                                    break;
                                case "3":
                                    if (v3 != null && v1 != null) {
                                        scalar = Vectors.getScalarProduct(v3, v1);
                                        System.out.println("скалярное произведение двух векторов: " + scalar);
                                    } else {
                                        printRedLn("ошибка: вектор не задан");
                                    }
                                    break;
                                default:
                                    printRedLn("ошибка: неверный пункт меню");
                                    isSelected = false;
                                    break;
                            }
                        } catch (Exception exc) {
                            System.out.println(exc.getMessage());
                        }
                    } while (!isSelected);

                    printExit();
                    break;
                // endregion

                // region 7 -- создать вектор
                case "7":
                    printMenuItem("7 -- работа методов доступа к элементам вектора");

                    do {
                        System.out.print("выберите вектор,\n" +
                                "который хотите изменить\n" +
                                line +
                                "1 -- 1-ый вектор\n" +
                                "2 -- 2-ой вектор\n" +
                                line +
                                "выбор ... ");
                        m2 = scan.next();
                        System.out.println();

                        if (m2.equals("1")) {
                            System.out.println("1-ый вектор:");
                            printChangeEl(v1);
                            System.out.println();
                            break;
                        } else if (m2.equals("2")) {
                            System.out.println("2-ый вектор:");
                            printChangeEl(v2);
                            System.out.println();
                            break;
                        } else {
                            printRedLn("ошибка: неверный пункт меню");
                        }
                    } while (true);

                    printExit();
                    break;
                // endregion

                default:
                    clearOutput();
                    break;
            }
        } while (!m.equals("0"));
    }
}
