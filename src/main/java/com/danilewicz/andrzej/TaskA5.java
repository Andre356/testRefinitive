package com.danilewicz.andrzej;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TaskA5 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader;
        URL url = new URL("https://www.nhc.noaa.gov/data/hurdat/hurdat2-nepac-1949-2016-041317.txt");

        try {
            // reader reads text from the specified file
            reader = new BufferedReader(new InputStreamReader(url.openStream()));

            // read file line by line
            String line;
            String hurr = "";
            int max = 0;
            boolean validHurr = false;

            while ((line = reader.readLine()) != null) {
                // check if line starts with "EP" or "CP"- name search;
                if (line.startsWith("EP") || line.startsWith("CP")) {
                    if (validHurr){
                        System.out.println(max);
                    }
                    max =0;
                    String[] values = line.split(",");
                    int year = Integer.parseInt(values[0].substring(4,8));
                    if (year > 2015 && values[1].toUpperCase().endsWith("A")){
                        validHurr = true;
                        hurr = values[1].trim();
                        System.out.print(hurr + "\t\t");
                    } else {
                        validHurr = false;
                    }
                } else {
                    if (validHurr){
                        int tempMax = Integer.parseInt(line.split(",")[6].trim());
                        if (tempMax > max){
                            max = tempMax;
                        }
                    }
                }
            }
            reader.close();
            if (validHurr) {
                System.out.println(max);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
