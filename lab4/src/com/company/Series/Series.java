package com.company.Series;

import com.company.Exceptions.DatabaseNotSetException;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Series {
    public static ArticlesSeries[] getArticlesSerArrFromSerArr(Seriesable[] sArr) throws DatabaseNotSetException {
        if (sArr == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            LinkedList<Integer> indexesOfArticles = getIndexesOfArticles(sArr);
            ArticlesSeries[] as = new ArticlesSeries[indexesOfArticles.size()];

            for (int i = 0; i < as.length; i++) {
                as[i] = (ArticlesSeries) sArr[indexesOfArticles.get(i)];
            }

            return as;
        }
    }

    private static LinkedList<Integer> getIndexesOfArticles(Seriesable[] sArr) throws DatabaseNotSetException {
        if (sArr == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник статей не задан");
        } else {
            LinkedList<Integer> indexesOfArticles = new LinkedList<>();

            for (int i = 0; i < sArr.length; i++) {
                if (sArr[i] instanceof ArticlesSeries) {
                    indexesOfArticles.add(i);
                }
            }

            return indexesOfArticles;
        }
    }

    public static BooksSeries[] getBooksSerArrFromSerArr(Seriesable[] sArr) throws DatabaseNotSetException {
        if (sArr == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = getIndexesOfBooks(sArr);
            BooksSeries[] bs = new BooksSeries[indexesOfBooks.size()];

            for (int i = 0; i < bs.length; i++) {
                bs[i] = (BooksSeries) sArr[indexesOfBooks.get(i)];
            }

            return bs;
        }
    }

    private static LinkedList<Integer> getIndexesOfBooks(Seriesable[] sArr) throws DatabaseNotSetException {
        if (sArr == null) {
            throw new DatabaseNotSetException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = new LinkedList<>();

            for (int i = 0; i < sArr.length; i++) {
                if (sArr[i] instanceof BooksSeries) {
                    indexesOfBooks.add(i);
                }
            }

            return indexesOfBooks;
        }
    }

    public static Seriesable[] getSerArrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] sArr) throws DatabaseNotSetException {
        if (sArr == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            int[] sumsOfPagesWithoutStart = getSumsOfPagesWithoutStart(sArr);

            int currIndexOfSum;
            int indexToCompareWith;
            int len = sumsOfPagesWithoutStart.length;

            for (currIndexOfSum = 0; currIndexOfSum < len; currIndexOfSum++) {
                for (indexToCompareWith = currIndexOfSum + 1; indexToCompareWith < len; indexToCompareWith++) {
                    if (sumsOfPagesWithoutStart[currIndexOfSum] == sumsOfPagesWithoutStart[indexToCompareWith]) {
                        Seriesable[] twoSer = new Seriesable[2];
                        twoSer[0] = sArr[currIndexOfSum];
                        twoSer[1] = sArr[indexToCompareWith];

                        return twoSer;
                    }
                }
            }

            throw new NoSuchElementException("нет таких элементов");
        }
    }

    private static int[] getSumsOfPagesWithoutStart(Seriesable[] sArr) throws DatabaseNotSetException {
        if (sArr == null) {
            throw new DatabaseNotSetException("операция невозможна: база данных не задана");
        } else {
            int[] sumsOfPagesWithoutStart = new int[sArr.length];

            for (int i = 0; i < sumsOfPagesWithoutStart.length; i++) {
                sumsOfPagesWithoutStart[i] = sArr[i].getSumOfPagesWithoutStart();
            }

            return sumsOfPagesWithoutStart;
        }
    }
}
