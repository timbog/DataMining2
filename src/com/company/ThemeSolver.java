package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Masha on 10.11.2014.
 */
public class ThemeSolver {
    private BaseData data;
    private String link;
    private Doc article;

    public Doc getArticle() {
        return article;
    }

    public void setArticle(Doc article) {
        this.article = article;
    }
    public BaseData getData() {
        return data;
    }

    public void setData(BaseData data) {
        this.data = data;
    }

    public ThemeSolver(BaseData data, String link) {
        this.data = data;
        this.link = link;
        this.article = new Doc(link, data.getThemes());
        article.init();
        try {
            this.run();
        }
        catch (Exception ex) {
        }
    }

    public void run() throws Exception {
        //создание словаря
        fileWriter("t1.txt", 0);
        fileWriter("t2.txt", 1);
        fileWriter("t3.txt", 2);
        fileWriter("t4.txt", 3);

        double probability = 0;
        int g = 0;


        MakeDictionary dictionary = new MakeDictionary();

        String[] theme_name;
        theme_name = new String[4];

        //массив, в котором будут храниться суммарные вероятности тем
        double[] arr_chance;
        arr_chance = new double[4];
        for (int i=0; i<4; i++){
            arr_chance[i] = 0;
        }

        for (int i=0; i<this.getArticle().getWords().size(); i++){
            for (int j=0; j<4; j++){
                for (int k=0; k < dictionary.getDictionary_list().get(j).size(); k = k+2){
                    if (this.getArticle().getWords().get(i).getValue().equals(dictionary.getDictionary_list().get(j).get(k))){
                        probability = Double.parseDouble(dictionary.getDictionary_list().get(j).get(k+1));
                        arr_chance[j] = arr_chance[j] + probability;
                        g++;//эта переменная показывает сколько слов совпадает со словами в словаре
                    }
                }
            }
        }

        //нахожу максимально вероятную тему
        double max = 0;
        int k = 0;
        for (int i=0; i < arr_chance.length; i++){
            if (max < arr_chance[i]) {
                max = arr_chance[i];
                k = i;
            }
        }

        //System.out.println(k);
        //System.out.println(g);
    }

    public static void fileWriter(String str, int i) throws IOException {
        BaseData data = new BaseData();
        int k = 0;
        File file = new File(System.getProperty("java.io.tmpdir") + str);
        try {
            if (file.exists())
                file.delete();
            file.createNewFile();
        }
        catch (IOException ex) {
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        WordSorter sorter = new WordSorter();

        data.getThemes().get(i).setwords(sorter.sort(data.getThemes().get(i).getWords()));
        for (Word word : data.getThemes().get(i).getWords()) {
            k++;
            fw.write(word.getValue() + " " + word.getProbability() + " ");
        }
        fw.close();
        System.out.println(k);
    }
}
