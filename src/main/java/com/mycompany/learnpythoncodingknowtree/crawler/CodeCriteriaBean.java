/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.learnpythoncodingknowtree.crawler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class CodeCriteriaBean {
    private String title;
    private String url;
    private List<String> tag=new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
    
}
