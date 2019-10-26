package com.company.Series;

import com.company.SeriesExceptions.DatabaseNotSetException;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Series {
    public static ArticlesSet[] getArticlesSeriesArrFromSeriesableArr(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            LinkedList<Integer> indexesOfArticles = getIndexesOfArticles(s);
            ArticlesSet[] as = new ArticlesSet[indexesOfArticles.size()];

            for (int i = 0; i < as.length; i++) {
                as[i] = (ArticlesSet) s[indexesOfArticles.get(i)];
            }

            return as;
        }
    }

    private static LinkedList<Integer> getIndexesOfArticles(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник статей не задан");
        } else {
            LinkedList<Integer> indexesOfArticles = new LinkedList<>();

            for (int i = 0; i < s.length; i++) {
                if (s[i] instanceof ArticlesSet) {
                    indexesOfArticles.add(i);
                }
            }

            return indexesOfArticles;
        }
    }

    public static BooksSet[] getBooksSeriesArrFromSeriesableArr(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = getIndexesOfBooks(s);
            BooksSet[] bs = new BooksSet[indexesOfBooks.size()];

            for (int i = 0; i < bs.length; i++) {
                bs[i] = (BooksSet) s[indexesOfBooks.get(i)];
            }

            return bs;
        }
    }

    private static LinkedList<Integer> getIndexesOfBooks(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = new LinkedList<>();

            for (int i = 0; i < s.length; i++) {
                if (s[i] instanceof BooksSet) {
                    indexesOfBooks.add(i);
                }
            }

            return indexesOfBooks;
        }
    }

    public static Seriesable[] getArrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            int[] sumsOfPagesWithoutStart = getSumsOfPagesWithoutStart(s);

            int currIndexOfSum;
            int indexToCompareWith;
            int len = sumsOfPagesWithoutStart.length;

            for (currIndexOfSum = 0; currIndexOfSum < len; currIndexOfSum++) {
                for (indexToCompareWith = currIndexOfSum + 1; indexToCompareWith < len; indexToCompareWith++) {
                    if (sumsOfPagesWithoutStart[currIndexOfSum] == sumsOfPagesWithoutStart[indexToCompareWith]) {
                        Seriesable[] twoSeriesable = new Seriesable[2];
                        twoSeriesable[0] = s[currIndexOfSum];
                        twoSeriesable[1] = s[indexToCompareWith];

                        return twoSeriesable;
                    }
                }
            }

            throw new NoSuchElementException("нет таких элементов");
        }
    }

    private static int[] getSumsOfPagesWithoutStart(Seriesable[] s) throws DatabaseNotSetException {
        if (s == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            int[] sumsOfPagesWithoutStart = new int[s.length];

            for (int i = 0; i < sumsOfPagesWithoutStart.length; i++) {
                sumsOfPagesWithoutStart[i] = s[i].getSumOfPagesWithoutStart();
            }

            return sumsOfPagesWithoutStart;
        }
    }
}
