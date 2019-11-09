package com.company.Series;

import com.company.Exceptions.NullSeriesableObjectException;

import java.io.*;

import static com.company.Series.InputAndOutputSeriesable.inputBytesAsSer;
import static com.company.Series.InputAndOutputSeriesable.readTextAsSer;

public class InputAndOutputSeriesableArray {
    // region запись массива
    public static void outputSerArrAsBytes(Seriesable[] sArr, OutputStream out) {
        outputLenOfSerArrAsBytes(sArr, out);
        for (Seriesable s : sArr) {
            s.outputAsBytes(out);
        }
    }

    private static void outputLenOfSerArrAsBytes(Seriesable[] sArr, OutputStream out) {
        BufferedOutputStream buffer;
        DataOutputStream dataOutputter;
        try {
            buffer = new BufferedOutputStream(out);
            dataOutputter = new DataOutputStream(buffer);

            dataOutputter.writeInt(sArr.length);

            dataOutputter.flush();
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
        BufferedWriter buffer;
        PrintWriter printer;
        try {

            buffer = new BufferedWriter(out);
            printer = new PrintWriter(buffer);

            printer.println(sArr.length);

            printer.flush();
            buffer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void serializeSerArr(Seriesable[] sArr, OutputStream out) {
        ObjectOutputStream serializer;
        try {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(sArr);
            serializer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
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

        DataInputStream dataInputter;
        try {
            dataInputter = new DataInputStream(in);
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

        BufferedReader reader;
        try {
            reader = new BufferedReader(in);

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

        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
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
