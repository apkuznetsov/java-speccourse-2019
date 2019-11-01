package com.company.Series;

import com.company.Exceptions.IllegalIndexException;

import java.io.*;

public class ArticlesSet implements Seriesable, Serializable {
    private String title;
    private int numOfAbstractPages;
    private String[] articles;
    private int[] numsOfPages;

    public ArticlesSet(String title, int numOfAbstractPages, int numOfArticles) {
        this.title = title;
        this.numOfAbstractPages = numOfAbstractPages;
        articles = new String[numOfArticles];
        numsOfPages = new int[articles.length];
    }

    // region get
    public String getTitle() {
        return title;
    }

    public int getNumOfStartPages() {
        return numOfAbstractPages;
    }

    public int getNumOfEls() {
        return articles.length;
    }

    public String getEl(int index) {
        if (index < 0 || index >= articles.length) {
            throw new IllegalIndexException("неверный индекс");
        }

        return articles[index];
    }

    public int getNumOfPages(int index) {
        if (index < 0 || index >= numsOfPages.length) {
            throw new IllegalIndexException("неверный индекс");
        }

        return numsOfPages[index];
    }
    // endregion

    // region set
    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumOfStartPages(int num) {
        if (num < Seriesable.MIN_NUM_OF_START_PAGES) {
            throw new IllegalArgumentException("неверное число страниц");
        }
        if (num > Seriesable.MAX_NUM_OF_START_PAGES) {
            throw new IllegalArgumentException("слишком большое число страниц");
        }

        numOfAbstractPages = num;
    }

    public void setEl(int index, String el) {
        if (index < 0 || index >= articles.length) {
            throw new IllegalIndexException("неверный индекс");
        }

        articles[index] = el;
    }

    public void setNumOfPagesOfEl(int index, int num) {
        if (index < 0 || index >= articles.length) {
            throw new IllegalIndexException("неверный индекс");
        }
        if (num < Seriesable.MIN_NUM_OF_PAGES_OF_EL) {
            throw new IllegalArgumentException("неверное число страниц");
        }
        if (num > Seriesable.MAX_NUM_OF_PAGES_OF_EL) {
            throw new IllegalArgumentException("слишком большое число страниц");
        }

        numsOfPages[index] = num;
    }
    // endregion

    // функциональный метод
    public int getSumOfPagesWithoutStart() {
        int sum = 0;
        for (int num : numsOfPages) {
            sum += num;
        }

        return sum;
    }

    // region переопределения
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("название сборника статей .......................... ").append(title).append('\n');
        sb.append("кол-во страниц в аннотации ........................ ").append(numOfAbstractPages).append('\n');
        sb.append("общей кол-во страниц в сборнике без аннотаций ..... ").append(getSumOfPagesWithoutStart()).append('\n');
        sb.append("кол-во элементов .................................. ").append(articles.length).append('\n');
        sb.append("тип объекта........................................ ").append(getClass()).append('\n');
        sb.append("---------------------------------------------------\n");

        appendArticlesInfo(sb);

        return sb.toString();
    }

    private void appendArticlesInfo(StringBuilder sb) {
        int lastIndex = articles.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            sb.append(i).append(") ").
                    append(articles[i]).
                    append(" (кол-во стр. -- ").append(numsOfPages[i]).append(")").append("\n");
        }
        sb.append(lastIndex).append(") ").
                append(articles[lastIndex]).
                append(" (кол-во стр. -- ").append(numsOfPages[lastIndex]).append(")");
    }

    @Override
    public boolean equals(Object obj) {
        boolean isSeriesable = obj instanceof Seriesable;

        if (isSeriesable) {
            Seriesable anotherSeries = (Seriesable) obj;

            if (this.title.equals(anotherSeries.getTitle()))
                return areElsEqual(anotherSeries);
        }

        return false;
    }

    private boolean areElsEqual(Seriesable anotherSeries) {
        if (!areNumOfStartPagesAndElsEqual(anotherSeries)) {
            return false;
        }

        for (int i = 0; i < articles.length; i++) {
            if (!isElEqual(i, anotherSeries))
                return false;
        }

        return true;
    }

    private boolean areNumOfStartPagesAndElsEqual(Seriesable anotherSeries) {
        return this.getNumOfStartPages() == anotherSeries.getNumOfStartPages() &&
                this.articles.length == anotherSeries.getNumOfEls();
    }

    private boolean isElEqual(int index, Seriesable anotherSeries) {
        return articles[index].equals(anotherSeries.getEl(index)) &&
                numsOfPages[index] == anotherSeries.getNumOfPages(index);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    // endregion

    public void outputAsBytes(OutputStream out) {
        BufferedOutputStream buffer = new BufferedOutputStream(out);
        DataOutputStream dataOutputter = new DataOutputStream(buffer);

        try {
            dataOutputter.writeUTF(title);
            dataOutputter.writeInt(numOfAbstractPages);
            dataOutputter.writeInt(articles.length);

            for (int index = 0; index < articles.length; index++) {
                dataOutputter.writeUTF(articles[index]);
                dataOutputter.writeInt(numsOfPages[index]);
            }

            dataOutputter.flush();
            dataOutputter.close();

            buffer.flush();
            buffer.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }

    public void writeAsText(Writer out) {
        BufferedWriter buffer = new BufferedWriter(out);
        PrintWriter printer = new PrintWriter(buffer);

        try {
            printer.println(title);
            printer.println();

            printer.println(numOfAbstractPages);
            printer.println();

            printer.println(articles.length);
            printer.println();

            for (int index = 0; index < articles.length; index++) {
                printer.println(articles[index]);
                printer.println(numsOfPages[index]);
                printer.println();
            }

            printer.flush();
            printer.close();

            buffer.flush();
            buffer.close();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
