/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.learnpythoncodingknowtree;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.util.ArrayList;
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
        Set<String> tagSet = new TreeSet<>();
        List<Map> dList = new ArrayList<>();//儲存所有的資料
        for (page = 1; page <= 500; page++) {
            String url = "https://api.stackexchange.com/2.2/questions?key=HBRCqQk3OxNjO0dh89T5SA((&order=desc&sort=votes&min=10&site=stackoverflow&tagged=python&pagesize=100&page=" + page;
            Map ret = null;
            String body=null;
            try {
                body=HttpRequest.get(url).acceptCharset("utf-8").acceptGzipEncoding().uncompress(true).body();
                ret = gson.fromJson(body, Map.class);
            } catch (Throwable e) {
                System.out.println(body);
                throw e;
            }
            List<Map> items = (List) ret.get("items");

            for (Map item : items) {
                List<String> tags = (List<String>) item.get("tags");
                String title = (String) item.get("title");
                System.out.println(title);
                for (String tag : tags) {
                    tagSet.add(tag);
                }
                dList.add(item);
            }

            boolean hasMore = (boolean) ret.get("has_more");
            if (!hasMore) {
                break;
            }
            if(page%10==0){
                Thread.sleep(20000);
            }
            
        }
        System.out.println(Arrays.deepToString(tagSet.toArray()));
        //把 dList 用 gson 轉成字串然後存到檔案
        //把 tagSet 存到檔案
        String dlist_json = gson.toJson(dList);
        FileWriter fw = new FileWriter("dlist_json.txt");
        fw.write(dlist_json);
        fw.flush();
        fw.close();

        List<String> tags = new ArrayList<>(tagSet);
        String tags_json = gson.toJson(tags);
        fw = new FileWriter("tags_json.txt");
        fw.write(tags_json);
        fw.flush();
        fw.close();
    }

}
