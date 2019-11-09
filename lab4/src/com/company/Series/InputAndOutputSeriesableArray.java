package com.company.Series;

import com.company.Exceptions.NullSeriesableObjectException;

import java.io.*;

import static com.company.Series.InputAndOutputSeriesable.inputBytesAsSer;
import static com.company.Series.InputAndOutputSeriesable.readTextAsSer;

public class InputAndOutputSeriesableArray {
    // region запись массива
    public static void outputSerArrAsBytes(Seriesable[] sArr, OutputStream out) {
        BufferedOutputStream bos = new BufferedOutputStream(out);
        outputLenOfSerArrAsBytes(sArr, bos);
        for (Seriesable s : sArr) {
            s.outputAsBytes(out);
        }
    }

    private static void outputLenOfSerArrAsBytes(Seriesable[] sArr, BufferedOutputStream bos) {
        DataOutputStream dataOutputter;
        try {
            dataOutputter = new DataOutputStream(bos);
            dataOutputter.writeInt(sArr.length);
            dataOutputter.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static void writeSerArrAsText(Seriesable[] sArr, Writer out) {
        BufferedWriter bw = new BufferedWriter(out);
        writeLenOfSerArrAsText(sArr, bw);
        for (Seriesable s : sArr) {
            s.writeAsText(out);
        }
    }

    private static void writeLenOfSerArrAsText(Seriesable[] sArr, BufferedWriter bw) {
        PrintWriter printer = new PrintWriter(bw);
        printer.println(sArr.length);
        printer.flush();
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
        BufferedReader bf = new BufferedReader(in);
        final int len = getLenOfSerArrFromText(bf);
        Seriesable[] sArr = new Seriesable[len];

        for (int index = 0; index < len; ++index) {
            sArr[index] = readTextAsSer(bf);
        }

        return sArr;
    }

    private static int getLenOfSerArrFromText(BufferedReader bf) {
        int len = -1;

        try {
            len = Integer.parseInt(bf.readLine());
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
