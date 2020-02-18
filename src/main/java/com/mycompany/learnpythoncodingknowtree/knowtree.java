/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.learnpythoncodingknowtree;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author imsofa
 */
public class knowtree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        CSVReader csvReader = new CSVReader(new FileReader("tag_2w.csv"));
        String[] col = null;
        int[][] occurs = new int[100][217];
        boolean firstLine = true;
        String[] names = null;
        while ((col = csvReader.readNext()) != null) {
            if (firstLine) {
                firstLine = false;
                names = col;
                continue;
            }
            int cluster = Integer.valueOf(col[219]);
            for (int i = 1; i <= 217; i++) {
                occurs[cluster - 1][i - 1] += Integer.valueOf(col[i]);
            }
            //System.out.println(col[0]);
        }
        System.out.println(Arrays.deepToString(occurs));
        for (int i = 0; i < occurs.length; i++) {
            int[] row = occurs[i];
            int max = row[0];
            int maxIndex = 0;
            for (int j = 1; j < row.length; j++) {
                if (row[j] > max) {
                    max = row[j];
                    maxIndex = j;
                }
            }
            System.out.println("cluster:" + (i + 1) + ", max=" + maxIndex + ", tag=" + names[maxIndex + 1]);
        }

    }

}
