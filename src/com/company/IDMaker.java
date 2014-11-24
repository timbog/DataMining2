package com.company;

/**
 * Created by bogdan on 31.10.14.
 */
public class IDMaker {
    private static IDMaker instance;
    private static int index;

    private IDMaker() {
    }

    public static IDMaker getInstance() {
        if (instance == null) {
            instance = new IDMaker();
            index = 0;
        }
        return instance;
    }

    public int getIndex() {
        index = index + 1;
        return index;
    }

}
