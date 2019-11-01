package com.company;

import com.company.Exceptions.*;
import com.company.Series.*;

import java.io.*;

public class InputAndOutput {
    // region output
    public static void outputSeriesableAsBytes(Seriesable s, OutputStream out) {
        s.outputAsBytes(out);
    }

    public static void writeSeriesableAsText(Seriesable s, Writer out) {
        s.writeAsText(out);
    }

    public static void serializeSeriesable(Seriesable s, OutputStream out) {
        try {
            ObjectOutputStream serializer = new ObjectOutputStream(out);
            serializer.writeObject(s);
            serializer.flush();
            serializer.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
    // endregion

    public static Seriesable inputBytesAsSeriesable(InputStream in) throws NullSeriesableObjectException {
        String title;
        int numOfStartPages;
        int numOfEls;
        Seriesable s;

        DataInputStream dataInputter = new DataInputStream(in);
        try {
            title = dataInputter.readUTF();
            numOfStartPages = dataInputter.readInt();
            numOfEls = dataInputter.readInt();

            s = new BooksSet(title, numOfStartPages, numOfEls);
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
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }

    public static Seriesable readTextAsSeriesable(Reader in) throws NullSeriesableObjectException {
        String title;
        int numOfStartPages;
        int numOfEls;
        Seriesable s;

        BufferedReader reader = new BufferedReader(in);
        try {
            title = reader.readLine();
            reader.readLine();

            numOfStartPages = Integer.parseInt(reader.readLine());
            reader.readLine();

            numOfEls = Integer.parseInt(reader.readLine());
            reader.readLine();

            s = new BooksSet(title, numOfStartPages, numOfEls);
            final int len = s.getNumOfEls();

            String el;
            int numOfPages;
            for (int index = 0; index < len; index++) {
                el = reader.readLine();
                numOfPages = Integer.parseInt(reader.readLine());
                reader.readLine();

                s.setEl(index, el);
                s.setNumOfPagesOfEl(index, numOfPages);
            }
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            s = null;
        }

        if (s == null) {
            throw new NullSeriesableObjectException("не удалось считать Seriesable");
        }

        return s;
    }

    public static Seriesable deserializeSeriesable(InputStream in) throws NullSeriesableObjectException {
        Seriesable s;

        try {
            ObjectInputStream deserializer = new ObjectInputStream(in);
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
}
