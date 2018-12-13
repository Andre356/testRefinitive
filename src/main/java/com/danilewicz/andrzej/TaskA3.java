package com.danilewicz.andrzej;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.lang.String;

public class TaskA3 {
    public static void main(String[] args){
        try {
            int actualWind = 0;
            int actualSolar = 0;
            int actualOthers = 0;
            int forecastWind = 0;
            int forecastSolar = 0;
            int forecastOthers = 0;

            // connect and download website; Document represents html DOM
            Document doc = Jsoup.connect("http://www.mercado.ren.pt/EN/Electr/MarketInfo/Gen/Pages/Forecast.aspx").get();

            // find all elements with class "tabHEADER"
            Elements tabHEADER = doc.getElementsByClass("tabHEADER");

            for (Element el: tabHEADER) {
                // parents of our elements are tables we are interested in
                Element table = (Element) el.parentNode();

                // find all rows in the table by <tr> tag
                Elements rows = table.getElementsByTag("tr");

                // remove last row
                rows.remove(rows.size()-1);

                // for each row
                for (Element row: rows) {
                    // find columns in the row
                    Elements columns = row.getElementsByTag("td");

                    // count number of columns
                    int numberOfColumns = columns.size();

                    // now we are interested in rows with 5 columns for 1st table
                    if (numberOfColumns == 5){
                        // get wind value and convert to int
                        int wind = Integer.parseInt(columns.get(1).text().trim());

                        // check if column has class "txtPREV"
                        // if yes then it is forecast
                        if (columns.get(1).hasClass("txtPREV")){
                            forecastWind += wind;
                        } else {
                            actualWind += wind;
                        }

                        // do the same for rest
                        int solar = Integer.parseInt(columns.get(2).text().trim());
                        if (columns.get(2).hasClass("txtPREV")){
                            forecastSolar += solar;
                        } else {
                            actualSolar += solar;
                        }

                        int others = Integer.parseInt(columns.get(3).text().trim());
                        if (columns.get(3).hasClass("txtPREV")){
                            forecastOthers += others;
                        } else {
                            actualOthers += others;
                        }
                    }

                    // we are interested in rows with 4 columns for 2nd table
                    if (numberOfColumns == 4){
                        // get wind value and convert to int
                        int wind = Integer.parseInt(columns.get(0).text().trim());

                        // check if column has class "txtPREV"
                        // if yes then it is forecast
                        if (columns.get(0).hasClass("txtPREV")){
                            forecastWind += wind;
                        } else {
                            actualWind += wind;
                        }

                        // do the same for rest
                        int solar = Integer.parseInt(columns.get(1).text().trim());
                        if (columns.get(1).hasClass("txtPREV")){
                            forecastSolar += solar;
                        } else {
                            actualSolar += solar;
                        }

                        int others = Integer.parseInt(columns.get(2).text().trim());
                        if (columns.get(2).hasClass("txtPREV")){
                            forecastOthers += others;
                        } else {
                            actualOthers += others;
                        }
                    }
                }
            }
            System.out.println("Actual: HH Wind: " + actualWind + " Solar: " + actualSolar + " Others: " + actualOthers );
            System.out.println("Forecast: HH Wind: " + forecastWind + " Solar: " + forecastSolar + " Others: " + forecastOthers);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}