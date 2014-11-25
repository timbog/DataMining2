package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by bogdan on 28.10.14.
 */
public class Doc {

    private String content;
    private List<Theme> themes;
    private List<Word> words = new ArrayList<Word>();

    public List<ArrayList<Integer>> getAssignments() {
        return assignments;
    }

    private List<ArrayList<Integer>> assignments= new ArrayList<ArrayList<Integer>>();

    public HashMap<String, Integer> getThemeAppearance() {
        return themeAppearance;
    }

    public void setThemeAppearance(HashMap<String, Integer> themeAppearance) {
        this.themeAppearance = themeAppearance;
    }

    private HashMap<String, Integer> themeAppearance;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    private List<Integer> getWordsId() {
        List<Integer> idList = new ArrayList<Integer>();
        for (Word word:words) {
            idList.add(word.getId());
        }
        return idList;
    }

    private boolean wordsContain(String value) {
        for (Word word: words) {
            if (word.getValue().equals(value))
                return true;
        }
        return false;
    }

    private Theme getRandomTheme() {
        Random random = new Random();
        return themes.get(random.nextInt(themes.size()));
    }

    public String getContentByPath(String path) {
        try {
            Document doc = Jsoup.connect(path).get();
            Element content = doc.getElementsByClass("b-topic__body").first();
            System.out.println();
            String str = content.toString();
            String temp = "";
            boolean flag = true;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '<') {
                    flag = false;
                }
                if (str.charAt(i) == '>') {
                    flag = true;
                } else if ((str.charAt(i) != '<') && flag) {
                    temp = temp + str.charAt(i);
                }
            }
            return temp;
        }
        catch (java.io.IOException ex) {

        }
        return "ERROR";
    }

    public Doc(String path,  List<Theme> themes) {
        this.themes = themes;
        this.content = getContentByPath(path);
    }

    public void init() {
        themeAppearance = new HashMap<String, Integer>();
        if (Constants.getAlfa() == null) {
            Constants.setAlfa(new HashMap<String, Double>());
            Constants.setLyambda(new HashMap<String, Double>());
            for (Theme theme: themes) {
                Constants.addThemeName(theme.getName());
            }
        }

        String tmp = "";
        for (int i = 0; i < content.length(); i++) {
            if (((content.charAt(i) == ' ') && (tmp.length() != 0)) || (i == content.length() - 1)) {
                if ((!tmp.equals(" \n")) && (!tmp.equals("\n"))) {
                    if ((!tmp.equals("в")) && (!tmp.equals("на")) && (!tmp.equals("к")) && (!tmp.equals("за"))
                            && (!tmp.equals("из")) && (!tmp.equals("с")) && (!tmp.equals("под")) && (!tmp.equals("над"))
                            && (!tmp.equals("и")) && (!tmp.equals("что")) && (!tmp.equals("был")) && (!tmp.equals("это"))
                            && (!tmp.equals("а")) && (!tmp.equals("об")) && (!tmp.equals("о")) && (!tmp.equals("не"))
                            && (!tmp.equals("Об")) && (!tmp.equals("В"))) {
                            words.add(new Word(tmp, getRandomTheme(), 0));
                            if (!Constants.getLyambda().containsKey(tmp)) {
                                Constants.addWordValue(tmp);
                            }
                    }
                }
                tmp = "";
            }
            else {
                if ((content.charAt(i) != '.') && (content.charAt(i) != ',') && (content.charAt(i) != '!')
                        && (content.charAt(i) != ':') && (content.charAt(i) != '«') && (content.charAt(i) != '»') &&
                        (content.charAt(i) != '(') && (content.charAt(i) != ')') && (content.charAt(i) != '-')
                        && (content.charAt(i) != '_') && (content.charAt(i) != '—'))
                    tmp = tmp + content.charAt(i);
            }
        }
        countAssignments();
    }

    public void countThemeAppearance() {
        themeAppearance.clear();
        int[] array = new int[themes.size()];
        for (int i = 0; i < themes.size(); i++) {
            array[i] = 0;
        }
        for (Word word: words) {
            ++array[themes.indexOf(word.getTheme())];
        }
        for (int i = 0; i < themes.size(); i++) {
            themeAppearance.put(themes.get(i).getName(), array[i]);
        }
    }

    private void changeThemeOfWord(Word word) {
        //countAssignments();
        countThemeAppearance();
        word.getTheme().getWords().remove(word);
        int amountOfThisWord = word.getTheme().getWordAmount().get(word.getValue());
        word.getTheme().getWordAmount().remove(word.getValue());
        if (amountOfThisWord - 1 != 0) {
            word.getTheme().getWordAmount().put(word.getValue(), amountOfThisWord - 1);
        }
        int oldThemeAppearance = themeAppearance.get(word.getTheme().getName());
        themeAppearance.remove(word.getTheme().getName());
        themeAppearance.put(word.getTheme().getName(), oldThemeAppearance - 1);
        assignments.get(words.indexOf(word)).set(themes.indexOf(word.getTheme()), assignments.get(words.indexOf(word)).get(themes.indexOf(word.getTheme())) - 1);
        HashMap<String, Double> probabilities = new HashMap<String, Double>();
        Random random = new Random();
        double sum = 0;
        double firstDenominator = 0;
        double secondDenominator = 0;
        for (Theme theme: themes) {
            firstDenominator = firstDenominator + ((double) themeAppearance.get(theme.getName())) + Constants.getAlfa().get(theme.getName());
        }
        for (Theme theme : themes) {
            double first = (themeAppearance.get(theme.getName()) + Constants.getAlfa().get(theme.getName())) / firstDenominator;
            for (Word word1: theme.getWords()) {
                secondDenominator = secondDenominator + theme.getWordAmount().get(word1.getValue()) + Constants.getLyambda().get(word1.getValue());
            }
            double second = (assignments.get(words.indexOf(word)).get(themes.indexOf(theme)) + Constants.getLyambda().get(word.getValue())) / secondDenominator;
            probabilities.put(theme.getName(), first * second);
            sum = sum + (first * second);
        }
        Random r = new Random();
        double randomValue = 0 + (sum) * r.nextDouble();
        int indexOfNewTheme = 0;
        double border = 0;
        for (int i = 0; i < themes.size(); i++) {
            border = border + probabilities.get(themes.get(i).getName());
            if (randomValue < border) {
                indexOfNewTheme = i;
                break;
            }
        }/*
        double res = 0;
        for (int i = 0; i < themes.size(); i++) {
            if (probabilities.get(themes.get(i).getName()) > res) {
                res = probabilities.get(themes.get(i).getName());
                indexOfNewTheme = i;
            }
        }*/
        int temp2 = 0;
        if (themes.get(indexOfNewTheme).getWordAmount().containsKey(word.getValue())) {
            temp2 = themes.get(indexOfNewTheme).getWordAmount().get(word.getValue());
            themes.get(indexOfNewTheme).getWordAmount().remove(word.getValue());
        }
        themes.get(indexOfNewTheme).getWordAmount().put(word.getValue(), temp2 + 1);

        word.setTheme(themes.get(indexOfNewTheme));
        word.setProbability(probabilities.get(themes.get(indexOfNewTheme).getName()));
        assignments.get(words.indexOf(word)).set(themes.indexOf(word.getTheme()), assignments.get(words.indexOf(word)).get(themes.indexOf(word.getTheme())) + 1);
    }

    public void countAssignments() {
        assignments.clear();
        for (Word word:words) {
            ArrayList<Integer> assignmentsToThemes = new ArrayList<Integer>();
            for (Theme theme:themes) {
                int assignmentsToThisTheme = 0;
                for (int i = 0; i < theme.getWords().size(); i++) {
                    if (theme.getWords().get(i).getValue().equals(word.getValue())) {
                       ++assignmentsToThisTheme;
                    }
                }
                assignmentsToThemes.add(assignmentsToThisTheme);
            }
            assignments.add(assignmentsToThemes);
        }
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void changeWordThemes() {
        for (Word word:words) {
            changeThemeOfWord(word);
        }
    }

}
