package com.company.Series;

public interface Seriesable {
    int MIN_LEN_OF_ARR = 1;
    int MAX_LEN_OF_ARR = 5;

    int MIN_NUM_OF_ELS_OF_SERIES = 1;
    int MAX_NUM_OF_ELS_OF_SERIES = 5;

    int MIN_NUM_OF_START_PAGES = 1;
    int MAX_NUM_OF_START_PAGES = 100;

    int MIN_NUM_OF_PAGES_OF_EL = 1;
    int MAX_NUM_OF_PAGES_OF_EL = 1000;

    String getTitle();

    int getNumOfEls();

    int getNumOfStartPages();

    String getEl(int index);

    int getNumOfPages(int index);

    void setTitle(String title);

    void setNumOfStartPages(int num);

    void setEl(int index, String title);

    void setNumOfPagesOfEl(int index, int num);

    int getSumOfPagesWithoutStart();
}
