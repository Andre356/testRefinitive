package com.danilewicz.andrzej;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TaskA1 {
    public static void main(String[] args) {
        BufferedReader reader;
        try{
            // reader reads text from the specified file
            reader = new BufferedReader(new FileReader("task_A1.html"));

            // read file line by line
            String line;
            while ((line = reader.readLine()) != null) {
                // check if line starts with "<tr"
                if (line.startsWith("<tr")){
                    // split line by ">"
                    String[] values = line.split(">");

                    for (int i=0; i < values.length; i++){
                        // if value starts with "<t" or "</t"
                        if (values[i].startsWith("<t") || values[i].trim().startsWith("</t")){
                            continue; // skip
                        }

                        // change value to upper case, split by "<" and take first element
                        values[i] = values[i].toUpperCase().split("<")[0];

                        if (i==2){          // change date
                            values[i] = FormatDate(values[i]);
                        } else if (i==6){   // change GENERATION
                            values[i] = FormatDecimal(values[i]);
                        } else if (i==10){  // change TURBINA DE GAS
                            values[i] = values[i].replaceAll("%", " ");
                        } else if (i==12){   // change FUEL Y GAS
                            values[i] = values[i].split("\\(")[0].replaceAll("\\+", "Y");
                        } else if (i==14){  // change CICLO COMBINADO
                            values[i] = values[i].split("\\(")[0].trim();
                        } else if (i==17){  // change GENERACIÓN.AUXILIAR
                            values[i] = values[i].split("\\(")[0].trim().replaceAll(" ", "\\.");
                        }
                        System.out.print(values[i] + "\t\t");
                    }
                    System.out.println();
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String FormatDate(String text){
        text = text.split("T")[0];   // split text by "T" and take first element

        if (text.contains("-")){        // if text contains "-"
            return text;                // correct format, return it
        } else if (text.contains("/")){ // if text contains "/"
            String[] values = text.split("/");  // split text by "/"
            return values[2] + "-" + values[1] + "-" + values[0];
        } else {
            return text.substring(0,2) + "-" + text.substring(2,4) + "-" + text.substring(4,8);
        }
    }

    private static String FormatDecimal(String text){
        // split text by "(", take second element, replace ")" with "" and replace "," with "."
        String number = text.split("\\(")[1].replaceAll("\\)", "").replaceAll(",", ".");

        // split text by "."
        String[] val = number.split("\\.");
        String num = "";
        // if there is not a dot
        if (val.length == 1){
            num = val[0] + ",00";
        } else {
            for (int i=0; i<val.length-1; i++){
                num = num + val[i]; // concatenate all beside last one
            }
            num = num + "," + val[val.length-1];    // add comma and last value
        }
        return text.split("\\(")[0] + "(" + num + ")";
    }
}
