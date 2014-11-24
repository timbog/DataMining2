package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Masha on 21.11.2014.
 */
public class MakeDictionary {
    private List<List<String>> dictionary_list = new ArrayList<List<String>>();

    public List<List<String>> getDictionary_list() {
        return dictionary_list;
    }

    public void setDictionary_list(List<List<String>> dictionary_list) {
        this.dictionary_list = dictionary_list;
    }

    public MakeDictionary() throws Exception{
        makeList("t1.txt", 0);
        makeList("t2.txt", 1);
        makeList("t3.txt", 2);
        makeList("t4.txt", 3);
    }

    public void makeList (String file_name, int i) throws Exception{
        File file1 = new File(System.getProperty("java.io.tmpdir") + file_name);
        FileInputStream inFile = new FileInputStream(file1);
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String text = new String(str);
        List<String> list = new ArrayList<String>();
        String[] str_split = text.split(" ");
        for (int j = 0; j < str_split.length; j++) {
            list.add(j, str_split[j]);
        }
        dictionary_list.add(i, list);
    }
}
