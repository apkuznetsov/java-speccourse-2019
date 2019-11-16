package com.company.Series;

import com.company.Exceptions.NullSarrException;
import com.company.Factories.ArticlesSeriesFactory;
import com.company.Factories.SeriesableFactory;
import com.company.Seriesable.Seriesable;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Series {
    private SeriesableFactory factory = new ArticlesSeriesFactory();

    public static ArticlesSeries[] getArticlesArrFromSarr(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: массив не задан");
        } else {
            LinkedList<Integer> indexesOfArticles = getIndexesOfArticles(sarr);
            ArticlesSeries[] as = new ArticlesSeries[indexesOfArticles.size()];

            for (int i = 0; i < as.length; i++) {
                as[i] = (ArticlesSeries) sarr[indexesOfArticles.get(i)];
            }

            return as;
        }
    }

    private static LinkedList<Integer> getIndexesOfArticles(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: сборник статей не задан");
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

    public static BooksSeries[] getBooksArrFromSarr(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = getIndexesOfBooks(sarr);
            BooksSeries[] bs = new BooksSeries[indexesOfBooks.size()];

            for (int i = 0; i < bs.length; i++) {
                bs[i] = (BooksSeries) sarr[indexesOfBooks.get(i)];
            }

            return bs;
        }
    }

    private static LinkedList<Integer> getIndexesOfBooks(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: сборник книг не задан");
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

    public static Seriesable[] getSarrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: массив не задан");
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

    private static int[] getSumsOfPagesWithoutStart(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: массив не задан");
        } else {
            int[] sumsOfPagesWithoutStart = new int[sarr.length];

            for (int i = 0; i < sumsOfPagesWithoutStart.length; i++) {
                sumsOfPagesWithoutStart[i] = sarr[i].getSumOfPagesWithoutStart();
            }

            return sumsOfPagesWithoutStart;
        }
    }

    public void setSeriesableFactory(SeriesableFactory sf) {
        this.factory = sf;
    }
}
