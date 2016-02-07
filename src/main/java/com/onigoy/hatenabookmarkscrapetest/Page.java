/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onigoy.hatenabookmarkscrapetest;

import java.util.List;

/**
 *
 * @author onigoy
 */
public class Page {

    public int count;
    public String url;
    public String eid;
    public String title;
    public String screenshot;
    public String entry_url;

    private List<Bookmark> bookmarks;

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public int getCount() {
        return count;
    }

    public String getUrl() {
        return url;
    }

    public String getEid() {
        return eid;
    }

    public String getTitle() {
        return title;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public String getEntry_url() {
        return entry_url;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public void setEntry_url(String entry_url) {
        this.entry_url = entry_url;
    }
}
