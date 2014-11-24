package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Constants {

    public static void setAlfa(HashMap<String, Double> alfa) {
        Constants.alfa = alfa;
    }

    private static HashMap<String,Double> alfa;

    public static void setLyambda(HashMap<String, Double> lyambda) {
        Constants.lyambda = lyambda;
    }

    private static HashMap<String, Double> lyambda;

    public static HashMap<String, Double> getAlfa() {
        return alfa;
    }

    public static HashMap<String, Double> getLyambda() {
        return lyambda;
    }

    private static double generateRandom() {
        double min=0.1;
        double max=100;
        Random r = new Random();
        double randomValue = min + (max - min) * r.nextDouble();
        while (Double.valueOf(randomValue).isInfinite()) {
            randomValue = min + (max - min) * r.nextDouble();
        }
        return randomValue;
    }

    public static void addThemeName(String themeName) {

        alfa.put(themeName, generateRandom());
    }

    public static void addWordValue(String wordValue) {
        lyambda.put(wordValue, generateRandom());
    }

}