package com.zenrows.data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
public class UpworkJob {
    private String title="React Native/Expo pro needed";
    public String getTitle() {
        return this.title = title;
    }
    public void setTitle(String title){
        this.title = title;
    }
//    @Override
//    public String toString () {
//        return "{ \"title\":\"" + title + "\", "
//                + "\"pubtime\":\"" + pubtime + "\", "
//                + "\"budget\":\"" + budget + "\", "
//               + "\"description\":\"" + description + "\", "+
//                "\"skillset\":\"" + skillset + "\"," +
//                "\"category\":\"" + category + "\"," +
//                "\"subcategory\":\"" + subcategory + "\"," +
//                " }";
//    }
    public static void printJob(UpworkJob upworkJob){
        System.out.println("Title" + " : " + upworkJob.getTitle());
    }
    public static void main(String[] args){
        String url = "https://www.upwork.com/freelance-jobs/apply/Convert-Scan-CAD-Model-Solidworks-with-Source-file_~01afa7c794e7770eb0/";
        try{
            Connection.Response response = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                    .referrer("https://www.google.com") //Set a valid referrer
                    .followRedirects(true)
                    .ignoreHttpErrors(true)
                    .execute();
            if(response.statusCode() == 200){
                Document doc =response.parse();
                //Process the document as needed
                UpworkJob upworkJob = new UpworkJob();
                System.out.println(doc.select("div.container"));
                Elements jobs= doc.select("div.container");
                upworkJob.setTitle(jobs.select("span.text-base").text());
                printJob(upworkJob);
            } else {
                System.out.println("Error: " + response.statusCode());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
//        Elements jobs= doc.select("div.container");
        UpworkJob upworkJob = new UpworkJob();
//        upworkJob.setTitle(jobs.select("span.text-base").text());
//        printJob(upworkJob);
    }
}
