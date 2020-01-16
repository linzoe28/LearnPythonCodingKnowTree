/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.learnpythoncodingknowtree;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author imsofa
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        int page = 1;
        Gson gson = new Gson();
        Set<String> tagSet=new TreeSet<>();
        for (page = 1; page <= 10; page++) {
            String url = "https://api.stackexchange.com/2.2/questions?key=HBRCqQk3OxNjO0dh89T5SA((&order=desc&sort=votes&min=10&site=stackoverflow&tagged=python&pagesize=100&page=" + page;
            Map ret = gson.fromJson(HttpRequest.get(url).acceptCharset("utf-8").acceptGzipEncoding().uncompress(true).body(), Map.class);
            List<Map> items = (List) ret.get("items");
            
            for(Map item : items){
                List<String> tags=(List<String>) item.get("tags");
                String title=(String) item.get("title");
                System.out.println(title);
                for(String tag : tags){
                    tagSet.add(tag);
                }
            }
            
            boolean hasMore = (boolean) ret.get("has_more");
            if (!hasMore) {
                break;
            }
        }
        System.out.println(Arrays.deepToString(tagSet.toArray()));
    }

}
