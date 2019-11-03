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
        try {
            ObjectOutputStream serializer = new ObjectOutputStream(out);

            serializer.writeObject(s);
            serializer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
    // endregion

    // region запись массива
    public static void outputSerArrAsBytes(Seriesable[] sArr, OutputStream out) {
        outputLenOfSerArrAsBytes(sArr, out);
        for (Seriesable s : sArr) {
            s.outputAsBytes(out);
        }
    }

    private static void outputLenOfSerArrAsBytes(Seriesable[] sArr, OutputStream out) {
        try {
            BufferedOutputStream buffer = new BufferedOutputStream(out);
            DataOutputStream dataOutputter = new DataOutputStream(buffer);

            dataOutputter.writeInt(sArr.length);

            dataOutputter.flush();
            buffer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void writeSerArrAsText(Seriesable[] sArr, Writer out) {
        writeLenOfSerArrAsText(sArr, out);
        for (Seriesable s : sArr) {
            s.writeAsText(out);
        }
    }

    private static void writeLenOfSerArrAsText(Seriesable[] sArr, Writer out) {
        try {
            BufferedWriter buffer = new BufferedWriter(out);
            PrintWriter printer = new PrintWriter(buffer);

            printer.println(sArr.length);
            printer.println();

            printer.flush();
            buffer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void serializeSerArr(Seriesable[] sArr, OutputStream out) {
        try {
            ObjectOutputStream serializer = new ObjectOutputStream(out);

            serializer.writeObject(sArr);

            serializer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
    // endregion

    // region считывание объекта
    public static Seriesable inputBytesAsSer(InputStream in) throws NullSeriesableObjectException, ClassNotFoundException {
        Seriesable s;

        try {
            DataInputStream dataInputter = new DataInputStream(in);

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

    public static Seriesable readTextAsSer(Reader in) throws NullSeriesableObjectException, ClassNotFoundException {
        Seriesable s;

        try {
            BufferedReader reader = new BufferedReader(in);

            String className = reader.readLine();
            reader.readLine();

            String title = reader.readLine();
            reader.readLine();

            int numOfStartPages = Integer.parseInt(reader.readLine());
            reader.readLine();

            int numOfEls = Integer.parseInt(reader.readLine());
            reader.readLine();

            s = getNewSerByClassName(className, title, numOfStartPages, numOfEls);

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
    // endregion

    // region считывание массива
    public static Seriesable[] inputBytesAsSerArr(InputStream in) throws NullSeriesableObjectException, ClassNotFoundException {
        final int len = getLenOfSerArrFromBytes(in);
        Seriesable[] sArr = new Seriesable[len];

        for (int index = 0; index < len; ++index) {
            sArr[index] = inputBytesAsSer(in);
        }

        return sArr;
    }

    private static int getLenOfSerArrFromBytes(InputStream in) {
        int len = -1;

        try {
            DataInputStream dataInputter = new DataInputStream(in);

            len = dataInputter.readInt();

            if (len == -1) {
                throw new IOException("ошибка: не удалось считать длину массива из байтвого потока");
            }
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }

        return len;
    }

    public static Seriesable[] readTextAsSerArr(Reader in) throws NullSeriesableObjectException, ClassNotFoundException {
        final int len = getLenOfSerArrFromText(in);
        Seriesable[] sArr = new Seriesable[len];

        for (int index = 0; index < len; ++index) {
            sArr[index] = readTextAsSer(in);
        }

        return sArr;
    }

    private static int getLenOfSerArrFromText(Reader in) {
        int len = -1;

        try {
            BufferedReader reader = new BufferedReader(in);

            len = Integer.parseInt(reader.readLine());
            reader.readLine();

            if (len == -1) {
                throw new IOException("ошибка: не удалось считать длину массива из байтвого потока");
            }
        } catch (IOException | NumberFormatException exc) {
            System.out.println(exc.getMessage());
        }

        return len;
    }

    public static Seriesable[] deserializeSerArr(InputStream in) throws NullSeriesableObjectException {
        Seriesable[] sArr;

        try {
            ObjectInputStream deserializer = new ObjectInputStream(in);

            sArr = (Seriesable[]) deserializer.readObject();
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
            sArr = null;
        }

        if (sArr == null) {
            throw new NullSeriesableObjectException("не удалось считать массив Seriesable[]");
        }

        return sArr;
    }
    // endregion
}
