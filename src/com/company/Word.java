package com.company;

/**
 * Created by bogdan on 31.10.14.
 */
public class Word {
    private String value;
    private Theme theme;
    private double probability;
    private int id;

    public Word(String value, Theme theme, double probability) {
        this.value = value;
        this.theme = theme;
        this.probability = probability;
        this.id = IDMaker.getInstance().getIndex();
        theme.getWords().add(this);
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
        theme.getWords().add(this);
    }
    public String getValue() {
        return value;
    }
    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public Theme getTheme() {
        return theme;
    }

    public int getId() {
        return id;
    }
}
