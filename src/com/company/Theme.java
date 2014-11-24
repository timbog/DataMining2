package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ludmila on 22.10.14.
 */
public class Theme {
    private String name;
    private List<Word> words = new ArrayList<Word>();

    public HashMap<String, Integer> getWordAmount() {
        return wordAmount;
    }

    public void setWordAmount(HashMap<String, Integer> wordAmount) {
        this.wordAmount = wordAmount;
    }

    private HashMap<String, Integer> wordAmount;

    public List<Word> getWords() {
        return words;
    }

    public void setwords(List<Word> words) {
        this.words = words;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Theme(String name) {
        this.name = name;
    }

    public void uniteEqualWords() {
        for (int i = 0; i < words.size() - 1; i++) {
            for (int j = i + 1; j < words.size(); j++) {
                //Word word = new Word(words.get(i).getValue(), words.get(i).getTheme(), words.get(i).getProbability());
                if (words.get(j).getValue().equals(words.get(i).getValue())) {
                    words.get(i).setProbability(words.get(i).getProbability() + words.get(j).getProbability());
                    words.remove(words.get(j));
                }
            }
        }
    }

    public void countWordAmount() {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        for (Word word1: words) {
            int amountOfThisWord = 0;
            for (Word word2: words) {
                if (word1.getValue().equals(word2.getValue())) {
                    amountOfThisWord++;
                }
            }
            result.put(word1.getValue(), amountOfThisWord);
        }
        this.wordAmount = result;
    }

}