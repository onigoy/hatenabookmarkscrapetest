package com.onigoy.hatenabookmarkscrapetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import org.jsoup.Jsoup;

public class FXMLController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TableColumn pageTitleColumn;
    @FXML
    private TableColumn bookmarkUserColumn;
    @FXML
    private TableColumn bookmarkCommentColumn;
    @FXML
    private TableView<TableItem> table;
    @FXML
    private TextField args0;
    @FXML
    private TextField args1;

    @FXML
    private void handleButtonAction(ActionEvent event) {
//        label.setText("...");

        // TODO 多分、データバインドする方法があるはず

        table.getItems().clear();

        scrapeHatenaBookmark();

//        label.setText("取得しました！");
    }

    @FXML
    private void handleMenuAboutAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("HatenaBookmark Scrapte Test");
        alert.setHeaderText(null);
        alert.setContentText("onigoyと申します。仕事募集中です！");

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pageTitleColumn.setCellValueFactory(new PropertyValueFactory<>("pageTitle"));
        bookmarkUserColumn.setCellValueFactory(new PropertyValueFactory<>("bookmarkUser"));
        bookmarkCommentColumn.setCellValueFactory(new PropertyValueFactory<>("bookmarkComment"));
    }

    private void scrapeHatenaBookmark() {
        String url;
        org.jsoup.nodes.Document document;
        HttpURLConnection connect;
        InputStream is;
        InputStream in;
        String str;

        for (int i = 0; i <= Integer.parseInt(args1.getText()); i++) {

            try {
                url = "http://b.hatena.ne.jp/entrylist?sort=hot&layout=headline&url=" + URLEncoder.encode(args0.getText(), "utf-8") + "&of=" + i * 20;
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
                        page.getBookmarks().stream().forEach((bookmark) -> {
                            table.getItems().add(new TableItem(page.getTitle(), bookmark.getUser(), bookmark.getComment()));

//                            System.out.println(page.getTitle() + "," + bookmark.getUser() + "," + bookmark.getComment());
                        });
                    } catch (IOException | JSONException e) {
                        System.out.println(e);
                    }

                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    String convertString(InputStream is) throws IOException {
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
