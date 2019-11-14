package com.company.Series;

import com.company.Exceptions.NullSeriesableException;

import java.io.*;

public class InputAndOutput {
    // region запись объекта
    public static void outputSeriesable(Seriesable s, OutputStream out) {
        s.output(out);
    }

    public static void writeSeriesable(Seriesable s, Writer out) {
        s.write(out);
    }

    public static void serializeSeriesable(Seriesable s, OutputStream out) {
        ObjectOutputStream serializer;
        try {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(s);
            serializer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }
    // endregion

    // region считывание объекта
    public static Seriesable inputSeriesable(InputStream in) throws NullSeriesableException, ClassNotFoundException {
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
            int numOfPages;
            for (int index = 0; index < len; index++) {
                numOfPages = dataInputter.readInt();
                s.setNumOfPagesOfEl(index, numOfPages);
            }
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            s = null;
        } catch (ClassNotFoundException exc) {
            throw new ClassNotFoundException(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableException("не удалось считать Seriesable");
        }

        return s;
    }

    public static Seriesable readSeriesable(Reader in) throws NullSeriesableException, ClassNotFoundException {
        Seriesable s;

        StreamTokenizer st;
        try {
            st = new StreamTokenizer(in);

            st.nextToken();
            String className = st.sval;

            StringBuilder sbTitle = new StringBuilder();
            String currWord;
            st.nextToken();
            while (st.ttype == StreamTokenizer.TT_WORD) {
                currWord = st.sval;
                sbTitle.append(currWord);
                sbTitle.append(' ');

                st.nextToken();
            }
            String title = sbTitle.toString();

            int numOfStartPages = (int) st.nval;

            st.nextToken();
            int numOfEls = (int) st.nval;

            s = getNewSerByClassName(className, title, numOfStartPages, numOfEls);

            final int len = s.getNumOfEls();
            int numOfPages;
            for (int index = 0; index < len; index++) {
                st.nextToken();
                numOfPages = (int) st.nval;
                s.setNumOfPagesOfEl(index, numOfPages);
            }
        } catch (IOException | NumberFormatException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            s = null;
        } catch (ClassNotFoundException exc) {
            throw new ClassNotFoundException(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableException("не удалось считать Seriesable");
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

    public static Seriesable deserializeSeriesable(InputStream in) throws NullSeriesableException {
        Seriesable s;

        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
            s = (Seriesable) deserializer.readObject();
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            s = null;
        }

        if (s == null) {
            throw new NullSeriesableException("не удалось считать Seriesable");
        }

        return s;
    }
    // endregion
}
