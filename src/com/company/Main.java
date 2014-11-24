package com.company;
import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Desktop;
//import javax.swing.text.Doc;

public class Main {

    public static void main(String[] args) throws Exception {
        ThemeSolver solver = new ThemeSolver(new BaseData(), "http://lenta.ru/news/2014/10/18/gonzalo/");
        System.out.println("exit");
    }

}
