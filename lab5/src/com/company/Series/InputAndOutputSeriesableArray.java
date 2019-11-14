package com.company.Series;

import com.company.Exceptions.NullSeriesableException;

import java.io.*;

import static com.company.Series.InputAndOutputSeriesable.inputBytesAsSer;
import static com.company.Series.InputAndOutputSeriesable.readTextAsSer;

public class InputAndOutputSeriesableArray {
    // region запись массива
    public static void outputSarrAsBytes(Seriesable[] sarr, OutputStream out) {
        BufferedOutputStream bos = new BufferedOutputStream(out);
        outputLenOfSarrAsBytes(sarr, bos);
        for (Seriesable s : sarr) {
            s.outputAsBytes(out);
        }
    }

    private static void outputLenOfSarrAsBytes(Seriesable[] sarr, BufferedOutputStream bos) {
        DataOutputStream dataOutputter;
        try {
            dataOutputter = new DataOutputStream(bos);
            dataOutputter.writeInt(sarr.length);
            dataOutputter.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    public static void writeSarrAsText(Seriesable[] sarr, Writer out) {
        BufferedWriter bw = new BufferedWriter(out);
        writeLenOfSarrAsText(sarr, bw);
        for (Seriesable s : sarr) {
            s.writeAsText(out);
        }
    }

    private static void writeLenOfSarrAsText(Seriesable[] sarr, BufferedWriter bw) {
        PrintWriter printer = new PrintWriter(bw);
        printer.println(sarr.length);
        printer.flush();
    }

    public static void serializeSarr(Seriesable[] sarr, OutputStream out) {
        ObjectOutputStream serializer;
        try {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(sarr);
            serializer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }
    // endregion

    // region считывание массива
    public static Seriesable[] inputBytesAsSarr(InputStream in) throws NullSeriesableException, ClassNotFoundException {
        final int len = getLenOfSarrFromBytes(in);
        Seriesable[] sarr = new Seriesable[len];

        for (int index = 0; index < len; ++index) {
            sarr[index] = inputBytesAsSer(in);
        }

        return sarr;
    }

    private static int getLenOfSarrFromBytes(InputStream in) {
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
            exc.printStackTrace();
        }

        return len;
    }

    public static Seriesable[] readTextAsSarr(Reader in) throws NullSeriesableException, ClassNotFoundException {
        BufferedReader bf = new BufferedReader(in);
        final int len = getLenOfSarrFromText(bf);
        Seriesable[] sarr = new Seriesable[len];

        for (int index = 0; index < len; ++index) {
            sarr[index] = readTextAsSer(bf);
        }

        return sarr;
    }

    private static int getLenOfSarrFromText(BufferedReader bf) {
        int len = -1;

        try {
            len = Integer.parseInt(bf.readLine());
            if (len == -1) {
                throw new IOException("ошибка: не удалось считать длину массива из байтвого потока");
            }
        } catch (IOException | NumberFormatException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }

        return len;
    }

    public static Seriesable[] deserializeSarr(InputStream in) throws NullSeriesableException {
        Seriesable[] sarr;

        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
            sarr = (Seriesable[]) deserializer.readObject();
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            sarr = null;
        }

        if (sarr == null) {
            throw new NullSeriesableException("не удалось считать массив Seriesable[]");
        }

        return sarr;
    }
    // endregion
}
