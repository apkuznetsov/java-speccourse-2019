package com.company.Series;

import com.company.Exceptions.DatabaseNotSetException;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Series {
    public static ArticlesSeries[] getArticlesArrFromSarr(Seriesable[] sarr) throws DatabaseNotSetException {
        if (sarr == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            LinkedList<Integer> indexesOfArticles = getIndexesOfArticles(sarr);
            ArticlesSeries[] as = new ArticlesSeries[indexesOfArticles.size()];

            for (int i = 0; i < as.length; i++) {
                as[i] = (ArticlesSeries) sarr[indexesOfArticles.get(i)];
            }

            return as;
        }
    }

    private static LinkedList<Integer> getIndexesOfArticles(Seriesable[] sarr) throws DatabaseNotSetException {
        if (sarr == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник статей не задан");
        } else {
            LinkedList<Integer> indexesOfArticles = new LinkedList<>();

            for (int i = 0; i < sarr.length; i++) {
                if (sarr[i] instanceof ArticlesSeries) {
                    indexesOfArticles.add(i);
                }
            }

            return indexesOfArticles;
        }
    }

    public static BooksSeries[] getBooksArrFromSarr(Seriesable[] sarr) throws DatabaseNotSetException {
        if (sarr == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = getIndexesOfBooks(sarr);
            BooksSeries[] bs = new BooksSeries[indexesOfBooks.size()];

            for (int i = 0; i < bs.length; i++) {
                bs[i] = (BooksSeries) sarr[indexesOfBooks.get(i)];
            }

            return bs;
        }
    }

    private static LinkedList<Integer> getIndexesOfBooks(Seriesable[] sarr) throws DatabaseNotSetException {
        if (sarr == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = new LinkedList<>();

            for (int i = 0; i < sarr.length; i++) {
                if (sarr[i] instanceof BooksSeries) {
                    indexesOfBooks.add(i);
                }
            }

            return indexesOfBooks;
        }
    }

    public static Seriesable[] getSarrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] sarr) throws DatabaseNotSetException {
        if (sarr == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            int[] sumsOfPagesWithoutStart = getSumsOfPagesWithoutStart(sarr);

            int currIndexOfSum;
            int indexToCompareWith;
            int len = sumsOfPagesWithoutStart.length;

            for (currIndexOfSum = 0; currIndexOfSum < len; currIndexOfSum++) {
                for (indexToCompareWith = currIndexOfSum + 1; indexToCompareWith < len; indexToCompareWith++) {
                    if (sumsOfPagesWithoutStart[currIndexOfSum] == sumsOfPagesWithoutStart[indexToCompareWith]) {
                        Seriesable[] twoSer = new Seriesable[2];
                        twoSer[0] = sarr[currIndexOfSum];
                        twoSer[1] = sarr[indexToCompareWith];

                        return twoSer;
                    }
                }
            }

            throw new NoSuchElementException("нет таких элементов");
        }
    }

    private static int[] getSumsOfPagesWithoutStart(Seriesable[] sarr) throws DatabaseNotSetException {
        if (sarr == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            int[] sumsOfPagesWithoutStart = new int[sarr.length];

            for (int i = 0; i < sumsOfPagesWithoutStart.length; i++) {
                sumsOfPagesWithoutStart[i] = sarr[i].getSumOfPagesWithoutStart();
            }

            return sumsOfPagesWithoutStart;
        }
    }
}
