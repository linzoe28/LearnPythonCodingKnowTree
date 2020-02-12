/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.learnpythoncodingknowtree;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author imsofa
 */
public class tag_cluster {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            // TODO code application logic here
            InputStreamReader inputreader = new InputStreamReader(new FileInputStream("dlist_dict.csv"));
            BufferedReader br = new BufferedReader(inputreader);
            String line = null;
            List<String> id_list = new ArrayList();
            Set tagset = new TreeSet();
            while ((line = br.readLine()) != null) {
                String dict_id[] = line.split(",");
                id_list.add(dict_id[0].toString());
                System.out.println(dict_id[0].toString());
            }
            
            String json = FileUtils.readFileToString(new File("dlist_json.txt"), "big5");
            Gson gs = new Gson();
            List<Map> list = gs.fromJson(json, List.class);
            List<Map> diList = new ArrayList<Map>();
            for (Map data : list) {
                String id_in_map = "" + ((int) Double.parseDouble("" + data.get("question_id")));
                if (id_list.contains(id_in_map)) {
                    diList.add(data);
                }
            }
            
            for (Map d : diList) {
                List<String> tags = (ArrayList<String>) d.get("tags");
                for (String tag : tags) {
                    tagset.add(tag);
                }
            }
            ArrayList<String> tag_dict = new ArrayList<>(tagset);
            ArrayList<String> tag_dict_copy = new ArrayList<>();
            for(String t: tag_dict){
                if(t.length()<5 || Character.isDigit(t.charAt(0)) || t.contains("-") || t.contains(".")){
                    continue;
                }else{
                    tag_dict_copy.add(t);
                }
            }
            System.out.println("tags: "+tag_dict_copy.size());
            tag_dict=tag_dict_copy;
            List<List> articles = new ArrayList<>();
            for (Map d : diList) {
                List<String> l = (List<String>) d.get("tags");
                List articleVector = new ArrayList();
                articleVector.add(""+d.get("question_id"));
                for (String t : tag_dict) {
                    if (l.contains(t)) {
                        articleVector.add("1");
                    } else {
                        articleVector.add("0");
                    }
                }
                articles.add(articleVector);
            }
            File article_csv = new File("article.csv");
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(article_csv), "utf-8");
            String header = "id," + String.join(",", tag_dict.toArray(new String[0]));
            ow.write(header + "\r\n");
            for (List article : articles) {
                String _line = String.join(",", (String[]) article.toArray(new String[0]));
                ow.write(_line + "\r\n");
            }
            ow.close();
            
        } catch (Throwable ex) {
            Logger.getLogger(tag_cluster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
