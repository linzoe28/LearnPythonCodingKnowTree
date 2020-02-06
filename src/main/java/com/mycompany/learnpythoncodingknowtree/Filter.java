/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.learnpythoncodingknowtree;

import com.google.gson.Gson;
import java.io.File;
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
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        String str=FileUtils.readFileToString(new File("dlist_json.txt"), "big5");
        System.out.println(str);
        Gson gson=new Gson();
        List<Map> list=gson.fromJson(str, List.class);
        for(Map data : list){
            System.out.println(data.get("answer_count"));
        }
    }
    
}
