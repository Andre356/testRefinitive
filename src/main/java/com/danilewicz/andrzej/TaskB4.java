package com.danilewicz.andrzej;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.apache.commons.math3.primes.Primes;

public class TaskB4 {
    public static void main(String[] args) {
        BufferedReader reader;
        try {
            TreeMap<String, Integer> map = new TreeMap<String, Integer>();

            // reader reads text from the specified file
            reader = new BufferedReader(new FileReader("task_B4.txt"));

            // skip first line with headers
            String line = reader.readLine();

            // read file line by line
            while ((line = reader.readLine()) != null) {
                // split line by tab
                String[] values = line.split("\\t");

                // remove all commas from GDP
                String GDPtext = values[1].replaceAll(",", "");

                // convert string to int
                int GDP = Integer.parseInt(GDPtext);

                // if GDP is prime number
                if (Primes.isPrime(GDP)){

                    // clean country name
                    String Country = values[0].split("\\[")[0];

                    // add it to the TreeMap
                    map.put(Country, GDP);
                }
            }
            reader.close();

            // print results
            for (Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();

                System.out.println(key + ":\t" + value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}