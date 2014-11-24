package com.company;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
/**
 * Created by bogdan on 05.11.14.
 */
public class WordSorter implements Comparator {

    Pattern p = null;
    Collator collator = null;

    /** Создает новые экземпляры FileSorter */
    public WordSorter() {
    }

    /* Этот метод выполняет сравнение имен двух файлов.
     * Возвращает:
     *     1 если первый параметр (о1) больше второго (о2),
     *    -1 если первый параметр (о1) меньше второго (о2),
     *     0 если они равны.
     * Имя первого файла считается больше второго имени, если
     * первый файл находится ближе к корню дерева папок.
     * Если файлы находятся в одной папке, то больше то имя,
     * которое идет первым по алфавиту.
     */
    public int compare(Object o1, Object o2) {
        Word word1 = (Word) o1;
        Word word2 = (Word) o2;
        if (word1.getProbability() > word2.getProbability())
            return -1;
        if (word1.getProbability() == word2.getProbability())
            return 0;
        else
            return 1;
    }

    public List sort(List fileList) {
        ArrayList res = new ArrayList(fileList.size());
        res.addAll(fileList);
        Collections.sort(res, this);
        return res;
    }
}
