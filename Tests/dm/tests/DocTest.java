package dm.tests;

import com.company.*;

import java.util.ArrayList;
import java.util.List;

public class DocTest {

    Doc doc;
    Doc doc2;
    Doc doc3;
    Doc doc4;
    BaseData data;

    @org.junit.Before
    public void setUp() throws Exception {

        }
        /*doc = new Doc ("http://lenta.ru/news/2014/10/13/cheese/", themes);
        doc.setContent("Путин приехал в США на самолете Обамы.");
        doc2 = new Doc ("http://lenta.ru/news/2014/10/13/cheese/", themes);
        doc2.setContent("Путин получил черный пояс по каратэ в США.");
        doc3 = new Doc ("http://lenta.ru/news/2014/10/13/cheese/", themes);
        doc3.setContent("Бразильский спортсмен получил травму в Сомали.");
        doc4 = new Doc ("http://lenta.ru/news/2014/10/13/cheese/", themes);
        doc4.setContent("Российский спортсмен получил звание мастера спорта по керлингу");
        doc2.init();
        doc.init();
        doc3.init();
        doc4.init();*/

    @org.junit.Test
    public void testCountAssignments() throws Exception {
            BaseData data = new BaseData();
            for (Theme theme : data.getThemes()) {
                System.out.print(theme.getName() + ":  ");
                for (Word word : theme.getWords()) {
                    System.out.print(word.getValue() + "  ");
                }
                System.out.println();
            }


    }

    @org.junit.Test
    public void testChangeWordThemes() throws Exception {
        List<Theme> themes= new ArrayList<Theme>();
        for (int i = 0; i < 4; i++) {
            Theme theme = new Theme("t" + (i + 1));
            themes.add(theme);
        }
        doc = new Doc ("http://lenta.ru/news/2014/10/13/cheese/", themes);
        doc.setContent("Путин приехал в США на самолете Обамы.");
        doc.init();
        for (Theme theme: themes) {
            theme.countWordAmount();
            System.out.print(theme.getName() + ":  ");
            for (Word word: theme.getWords()) {
                System.out.print(word.getValue());
                System.out.println(theme.getWordAmount().get(word.getValue()));
            }
            System.out.println();
        }
        System.out.println( "  ||| ");
        doc.changeWordThemes();
        for (Theme theme: themes) {
            System.out.print(theme.getName() + ":  ");
            for (Word word: theme.getWords()) {
                System.out.print(word.getValue());
                System.out.println(theme.getWordAmount().get(word.getValue()));
            }
        }
        System.out.println();
        for (Word word: doc.getWords()) {
            System.out.print(word.getValue() + ":  ");
            System.out.println(Constants.getLyambda().get(word.getValue()));
        }
    }

}