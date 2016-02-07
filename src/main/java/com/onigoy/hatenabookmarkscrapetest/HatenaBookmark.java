/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onigoy.hatenabookmarkscrapetest;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import org.jsoup.Jsoup;

/**
 *
 * @author onigoy
 */
public class HatenaBookmark {

    public static void scrape() {
        String url;
        org.jsoup.nodes.Document document;
        int i = 0;
        String[] args = new String[1];
        args[0] = "http://www.yutorism.jp";
        HttpURLConnection connect;
        InputStream is;
        InputStream in;
        String str;

        try {
            url = "http://b.hatena.ne.jp/entrylist?sort=hot&layout=headline&url=" + URLEncoder.encode(args[0], "utf-8") + "&of=" + i * 20;
            document = Jsoup.connect(url).timeout(100000).get();

            for (org.jsoup.nodes.Element element : document.select("li.entrylist-unit")) {
                try {
                    url = element.getElementsByTag("a").attr("href");
                    url = "http://b.hatena.ne.jp/entry/jsonlite/?url=" + URLEncoder.encode(url, "utf-8");

                    connect = (HttpURLConnection) new URL(url).openConnection();
                    connect.setRequestMethod("GET");
                    connect.setConnectTimeout(100000);
                    is = connect.getInputStream();
                    str = convertString(is);

                    Page page = JSON.decode(str, Page.class);
                    for (Bookmark bookmark : page.getBookmarks()) {
//                        table.getItems().add(new TableItem(page.getTitle(), bookmark.getUser(), bookmark.getComment()));
                        System.out.println(page.getTitle()+","+bookmark.getUser() + "," + bookmark.getComment());
                    }
                } catch (IOException | JSONException e) {
                    System.out.println(e);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String convertString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        char[] b = new char[1024];
        int line;
        while (0 <= (line = reader.read(b))) {
            sb.append(b, 0, line);
        }
        return sb.toString();
    }

}
