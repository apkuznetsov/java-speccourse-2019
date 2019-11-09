package com.company.Series;

import com.company.Exceptions.NullSeriesableObjectException;

import java.io.*;

public class InputAndOutputSeriesable {
    // region запись объекта
    public static void outputSerAsBytes(Seriesable s, OutputStream out) {
        s.outputAsBytes(out);
    }

    public static void writeSerAsText(Seriesable s, Writer out) {
        s.writeAsText(out);
    }

    public static void serializeSer(Seriesable s, OutputStream out) {
        ObjectOutputStream serializer;
        try {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(s);
            serializer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
    // endregion

    // region считывание объекта
    public static Seriesable inputBytesAsSer(InputStream in) throws NullSeriesableObjectException, ClassNotFoundException {
        Seriesable s;

        DataInputStream dataInputter;
        try {
            dataInputter = new DataInputStream(in);

            String className = dataInputter.readUTF();
            String title = dataInputter.readUTF();
            int numOfStartPages = dataInputter.readInt();
            int numOfEls = dataInputter.readInt();

            s = getNewSerByClassName(className, title, numOfStartPages, numOfEls);

            final int len = s.getNumOfEls();
            String el;
            int numOfPages;
            for (int index = 0; index < len; index++) {
                el = dataInputter.readUTF();
                numOfPages = dataInputter.readInt();

                s.setEl(index, el);
                s.setNumOfPagesOfEl(index, numOfPages);
            }
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            s = null;
        } catch (ClassNotFoundException exc) {
            throw new ClassNotFoundException(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }

    private static Seriesable getNewSerByClassName(String className, String title, int numOfStartPages, int numOfEls) throws ClassNotFoundException {
        if (className.equals(ArticlesSeries.class.getName())) {
            return new ArticlesSeries(title, numOfStartPages, numOfEls);
        } else if (className.equals(BooksSeries.class.getName())) {
            return new BooksSeries(title, numOfStartPages, numOfEls);
        } else {
            throw new ClassNotFoundException("ошибка: такого класса не существует");
        }
    }

    public static Seriesable readTextAsSer(BufferedReader bf) throws NullSeriesableObjectException, ClassNotFoundException {
        Seriesable s;

        try {
            String className = bf.readLine();
            String title = bf.readLine();
            int numOfStartPages = Integer.parseInt(bf.readLine());
            int numOfEls = Integer.parseInt(bf.readLine());

            s = getNewSerByClassName(className, title, numOfStartPages, numOfEls);

            final int len = s.getNumOfEls();
            String el;
            int numOfPages;
            for (int index = 0; index < len; index++) {
                el = bf.readLine();
                numOfPages = Integer.parseInt(bf.readLine());

                s.setEl(index, el);
                s.setNumOfPagesOfEl(index, numOfPages);
            }
        } catch (IOException | NumberFormatException exc) {
            System.out.println(exc.getMessage());
            s = null;
        } catch (ClassNotFoundException exc) {
            throw new ClassNotFoundException(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }

    public static Seriesable deserializeSer(InputStream in) throws NullSeriesableObjectException {
        Seriesable s;

        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
            s = (Seriesable) deserializer.readObject();
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
            s = null;
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }
    // endregion
}
