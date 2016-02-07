/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onigoy.hatenabookmarkscrapetest;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author onigoy
 */
public class TableItem {

    private StringProperty pageTitle;
    private StringProperty bookmarkUser;
    private StringProperty bookmarkComment;

    public TableItem(String pageTitle, String bookmarkUser, String bookmarkComment) {
        this.pageTitle = new SimpleStringProperty(pageTitle);
        this.bookmarkUser = new SimpleStringProperty(bookmarkUser);
        this.bookmarkComment = new SimpleStringProperty(bookmarkComment);
    }

    public StringProperty pageTitleProperty() {
        return this.pageTitle;
    }

    public StringProperty bookmarkUserProperty() {
        return this.bookmarkUser;
    }

    public StringProperty bookmarkCommentProperty() {
        return this.bookmarkComment;
    }
}
