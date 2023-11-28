package com.zenrows.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WorkanaJob {
    private String title="title";
    private String pubtime="pubtime";
    private String budget="budget";
    private String description="description";
    private String skillset="skillset";
    private String category="category";
    private String subcategory="subcategory";
    public String getTitle() {
        return this.title = title;
    }
    public String getPubtime() {
        return this.pubtime = pubtime;
    }
    public String getBudget() {
        return this.budget = budget;
    }
    public String getDescription(){
        return this.description;
    }
    public String getSkillset() {
        return this.skillset = skillset;
    }
    public String getCategory() {
        return this.category = category;
    }
    public String getSubcategory() {
        return this.subcategory = subcategory;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }
    public void setBudget(String budget) {
        this.budget = budget;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }
    public void setCategory(String category) {
        this.category=category;
    }
    public void setSubcategory(String subcategory) {
        this.subcategory=subcategory;
    }

//    @Override
//    public String toString () {
//        return "{ \"title\":\"" + title + "\", "
//                + "\"pubtime\":\"" + pubtime + "\", "
//                + "\"budget\":\"" + budget + "\", "
//                + "\"description\":\"" + description + "\", "+
//                "\"skillset\":\"" + skillset + "\"," +
//                "\"category\":\"" + category + "\"," +
//                "\"subcategory\":\"" + subcategory + "\"," +
//                " }";
//
//    }
    public static void printJob(WorkanaJob workanaJob){
        System.out.println("Title " +" : "+workanaJob.getTitle());
        System.out.println("Pubtime " +" : "+workanaJob.getPubtime());
        System.out.println("Description " +" : "+workanaJob.getDescription());
        System.out.println("Budget " +" : "+workanaJob.getBudget());
        System.out.println("Skills " +" : "+workanaJob.getSkillset());
        System.out.println("Category "+" : "+workanaJob.getCategory());
        System.out.println("Subcategory "+" : "+workanaJob.getSubcategory());
    }
    public static void main(String[] args){
        Document doc;
        try {
            doc = Jsoup
                    .connect("https://www.workana.com/job/administrative-coordinator-virtual-assistance?ref=projects_5")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                    .header("Accept-Language", "*")
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements jobs = doc.select("section.project-view-v3");
        WorkanaJob workanaJob = new WorkanaJob();

        int indexcategory = jobs.select("p.mt20").text().indexOf("Subcategory");
        int indexsubcategory = jobs.select("p.mt20").text().indexOf("Project size");
        workanaJob.setTitle(jobs.select("h1.title").text());
        workanaJob.setPubtime( jobs.select("p.h4").text().substring(16,34));
        workanaJob.setDescription(jobs.select("div.expander").text());
        workanaJob.setBudget(jobs.select("h4.budget").text());
        workanaJob.setSkillset(jobs.select("a.skill").text());
        workanaJob.setCategory(jobs.select("p.mt20").text().substring(9,indexcategory));
        workanaJob.setSubcategory(jobs.select("p.mt20").text().substring(indexcategory+12,indexsubcategory));
        printJob(workanaJob);
    }
}