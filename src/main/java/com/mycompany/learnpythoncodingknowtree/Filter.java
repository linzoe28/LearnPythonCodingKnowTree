/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.learnpythoncodingknowtree;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author imsofa
 */
public class Filter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String str = FileUtils.readFileToString(new File("dlist_json.txt"), "big5");
        File dlist_csv = new File("dlist.csv");
        OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(dlist_csv), "utf-8");
        System.out.println(str);
        Gson gson = new Gson();
        ow.write("question_id,view_count,answer_count,score\r\n");
        List<Map> list = gson.fromJson(str, List.class);
        for (Map data : list) {
            ow.write(data.get("question_id").toString());
            ow.write(",");
            ow.write(data.get("view_count").toString());
            ow.write(",");
            ow.write(data.get("answer_count").toString());
            ow.write(",");
            ow.write(data.get("score").toString());
            ow.write("\r\n");
            System.out.println(data.get("question_id").toString());
        }
        ow.close();
    }

}
