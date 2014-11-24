package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bogdan on 31.10.14.
 */
public class BaseData {
    private List<Theme> themes = new ArrayList<Theme>();
    private List<Doc> documents = new ArrayList<Doc>();
    private List<String> links = new ArrayList<String>();
    public List<Theme> getThemes() {
        return themes;
    }

    public List<Doc> getDocuments() {
        return documents;
    }

    public BaseData() {
        makeLinks();
        makeThemes();
        makeDocuments();
        for (int i = 0; i < 5; i ++) {
            changeThemes();
        }
    }

    private void makeThemes() {
        for (int i = 0; i < 4; i++) {
            Theme theme = new Theme("t" + (i + 1));
            themes.add(theme);
        }
    }

    private void uniteEqualWords() {
        for (Theme theme: themes) {
            theme.uniteEqualWords();

        }
    }

    private void makeDocuments() {
        for (String link : links) {
            Doc doc = new Doc(link, themes);
            doc.init();
            //doc.countAssignments();
            //doc.changeWordThemes();
            documents.add(doc);
            //uniteEqualWords();
        }
        for (Theme theme: themes) {
            theme.countWordAmount();
        }
    }

    private void makeLinks() {
        links.add("http://lenta.ru/news/2014/10/13/cheese/");
        links.add("http://lenta.ru/news/2014/07/31/juice/");
        links.add("http://lenta.ru/news/2014/11/03/last/");
        links.add("http://lenta.ru/news/2014/11/05/win/");
        links.add("http://lenta.ru/news/2014/11/04/facebookmurder/");
        links.add("http://lenta.ru/news/2014/11/05/submarine/");
        links.add("http://lenta.ru/news/2014/11/05/euro/");
        /*links.add("http://lenta.ru/news/2014/11/05/albania/");
        links.add("http://lenta.ru/news/2014/11/05/string/");
        links.add("http://lenta.ru/news/2014/11/05/nalchik/");
        links.add("http://lenta.ru/news/2014/11/04/brandao/");
        links.add("http://lenta.ru/news/2014/11/04/zenit/");
        links.add("http://lenta.ru/news/2014/11/01/babyt/");
        links.add("http://lenta.ru/news/2014/11/05/school/");
        links.add("http://lenta.ru/news/2014/11/03/stopwindows/");
        links.add("http://lenta.ru/news/2014/10/31/iss/");
        links.add("http://lenta.ru/news/2014/10/31/itch//");
        links.add("http://lenta.ru/news/2014/11/05/putin1/");
        links.add("http://lenta.ru/news/2014/11/03/captainsdaughter/");
        links.add("http://lenta.ru/news/2014/11/04/jobs/");*/
    }


    private void changeThemes() {
        for (Doc document: documents) {
            document.changeWordThemes();
        }
    }

}
