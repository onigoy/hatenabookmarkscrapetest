package com.onigoy.hatenabookmarkscrapetest;

import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author onigoy
 */
public class Bookmark {

    private Date timestamp;
    private String comment;
    private String user;
    private List<String> tags;

    public Date getTimestamp() {
        return timestamp;
    }

    public String getComment() {
        return comment;
    }

    public String getUser() {
        return user;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}
