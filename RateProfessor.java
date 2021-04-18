package com.example.demo;

import java.util.LinkedList;
import java.util.Vector;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import com.example.demo.javafoundations.ArrayIterator;
import com.example.demo.javafoundations.LinkedQueue;
import java.util.Hashtable;

/**
 * client class of AdjListDiGraph.java
 * 
 * @author ac119,dh4,ac111, hw102
 * @version 04-17-2021
 */
public class RateProfessor{
    /**
     * instance variables
     */
    private String name;

    private Integer difficultyRating;
    private Hashtable<String, LinkedList> difficultyHash = new Hashtable<String, LinkedList>();

    private Integer expRating;
    private Hashtable<String, LinkedList> experienceHash = new Hashtable<String, LinkedList>();

    private String takeAgain;
    private Hashtable<String, Double> takeAgainHash = new Hashtable<String, Double>();
    Double yesCount = 0.0;

    private String taAvail;
    private Hashtable<String, Double> taHash = new Hashtable<String, Double>();

    private String textbook;
    private Hashtable<String, Double> textbookHash = new Hashtable<String, Double>();

    private String attendance;
    private Hashtable<String, Double> attendanceHash = new Hashtable<String, Double>();

    private String timeSpent;
    private Hashtable<String, LinkedList> timeHash = new Hashtable<String, LinkedList>();

    private String comments;
    private AdjListsDiGraph<String> rateMyProf;

    private String structure;
    private String studying;

    private int submissionsCounter; 

    /**
     * constructor
     */
    public RateProfessor(String inputName, Integer inputExpRating, Integer diffInputRating,String inputAgain, String inputTA, String inputTxtbook, String inputAttendance, String inputTime, String inputStructure, String inputStudying){
        String again = "0";
        submissionsCounter = 0;
        rateMyProf = new AdjListsDiGraph<String>();
        name = inputName;
        expRating = inputExpRating;
        difficultyRating = diffInputRating;
        takeAgain = inputAgain;
        taAvail = inputTA;
        textbook = inputTxtbook;
        attendance = inputAttendance;
        structure = inputStructure;
        studying = inputStudying;
        timeSpent = inputTime;
        addProfessor(name);
        findDiffAverageRating(inputName);
        findExpAverageRating(inputName);
        submissionsCounter++;

    
    }

    /**
     * gets the name of the prof
     */
    public String getName(){
        return this.name;
    }


    /**
     * gets the rating that was inputed
     */

    public Integer getDiffRating(){
        return this.difficultyRating;
    }

    /**
     * gets the rating that was inputed
     */

    public Integer getExpRating(){
        return this.expRating;
    }

    /**
     * would take again?
     */
    public String getTakeAgain(){
        return this.takeAgain;
    }

    /**
     * would take again?
     */
    public String getTAAvail(){
        return this.taAvail;
    }

    /**
     * would take again?
     */
    public String gettxtbook(){
        return this.textbook;
    }

    /**
     * would take again?
     */
    public String getAttendance(){
        return this.attendance;
    }

    /**
     * would take again?
     */
    public String gettimeSpent(){
        return this.timeSpent;
    }

    /**
     * gets Studying comments
     */
    public String getStudying(){
        return this.studying;
    }

    /**
     * gets Schedule comments
     */
    public String getStructure(){
        return this.structure;
    }

    public void addProfessor(String name){
        rateMyProf.addVertex(name);
        LinkedList<Integer> rating;
        if (!difficultyHash.containsKey(name)){
            Integer dRating = Integer.valueOf(getDiffRating());
            rating = new LinkedList();
            rating.add(dRating);
            difficultyHash.put(name, rating);
        }
        else{
            LinkedList newRating = difficultyHash.get(name);
            newRating.add(getDiffRating());
            difficultyHash.put(name,newRating);
        }

        if (!experienceHash.containsKey(name)){
            rating = new LinkedList();
            rating.add(getExpRating());
            experienceHash.put(name, rating);
        }
        else{
            LinkedList newRating = experienceHash.get(name);
            newRating.add(getExpRating());
            experienceHash.put(name,newRating);
        }

        // if (!timeHash.containsKey(name)){
        //     rating = new LinkedList();
        //     rating.add(gettimeSpent());
        //     timeHash.put(name, rating);
        // }
        // else{
        //     LinkedList newRating = timeHash.get(name);
        //     newRating.add(gettimeSpent());
        //     timeHash.put(name,newRating);
        // }

        if (!takeAgainHash.containsKey(name)){
            if (this.getTakeAgain().equals("Yes")){
                yesCount++;
                takeAgainHash.put(name, yesCount);
            }

            else{
                takeAgainHash.put(name,yesCount);
            }

        }
        else{
            if (this.getTakeAgain().equals("Yes")){
                yesCount++;
                takeAgainHash.put(name, yesCount);
            }
        }

        if (!taHash.containsKey(name)){
            Double yesCount = 0.0;
            if (getTAAvail().equals("Yes")){
                yesCount++;
                taHash.put(name, yesCount);
            }
            else{
                taHash.put(name,yesCount);
            }

        }
        else{
            if (getTAAvail().equals("Yes")){
                yesCount++;
                taHash.put(name, yesCount);
            }
        }

        if (!textbookHash.containsKey(name)){
            yesCount = 0.0;
            if (gettxtbook().equals("Yes")){
                yesCount++;
                textbookHash.put(name, yesCount);
            }
            else{
                textbookHash.put(name,yesCount);
            }

        }
        else{
            if (gettxtbook().equals("Yes")){
                yesCount ++;
                textbookHash.put(name, yesCount);
            }
        }

        if (!attendanceHash.containsKey(name)){
            yesCount = 0.0;
            if (getAttendance().equals("Yes")){
                yesCount++;
                attendanceHash.put(name, yesCount);
            }
            else{
                attendanceHash.put(name,yesCount);
            }

        }
        else{
            if (getAttendance().equals("Yes")){
                yesCount ++;
                attendanceHash.put(name, yesCount);
            }
        }

    }

    /**
     * add arc
     */


    /**
     * puts the professors in a hashtable with their ratings
     * 
     * @returns the average rating
     */
    public void findDiffAverageRating(String name)
    {
        LinkedList<Integer> ratingsList = difficultyHash.get(name);
        double count = 0.0;
        double sum = 0.0;
        for (int i = 0; i < ratingsList.size(); i++){
            double ratingValue = (double)ratingsList.get(i);
            sum += ratingValue;
            count ++;
        }
        double average = (double)(sum*100) / (count*100);
        this.difficultyRating = (int)average;
    }

    /**
     * puts the professors in a hashtable with their ratings
     * 
     * @returns the average rating
     */
    public void findExpAverageRating(String name)
    {
        LinkedList<Integer> ratingsList = experienceHash.get(name);
        double count = 0;
        double sum = 0;
        for (int i = 0; i < ratingsList.size(); i++){
            double ratingValue = (double)ratingsList.get(i);
            sum += ratingValue;
            count ++;
        }
        double average = (double)(sum*100) / (count*100);
        this.expRating = (int)average;
    }

    /**
     * puts the professors in a hashtable with their ratings
     * 
     * @returns the average rating
     */
    // public Double getTimeAverage(String name)
    // {
    //     LinkedList<Double> ratingsList = timeHash.get(name);
    //     double count = 0;
    //     double sum = 0;
    //     for (int i = 0; i < ratingsList.size(); i++){
    //         sum = ratingsList.get(i) + sum;
    //         count ++;
    //     }
    //     Double average = (sum*100) / (count*100);
    //     return average;
    // }

    /**
     * finds the approval rate 
     */
    public String numTakeAgain(String name){
        double yesNum = takeAgainHash.get(name);
        double noNum = submissionsCounter - yesNum;
        String s = "";

        double percentYes = ((100* yesNum)/(100*submissionsCounter));
        double percentNo = ((100*noNum)/(100*submissionsCounter));

        s = ("Yes: " + (percentYes*100) + "%, No: " + (percentNo*100) + "% " + "\n");
        return s;
    }

    /**
     * finds the approval rate 
     */
    public String numTAAvail(String name){
        double yesNum = taHash.get(name);
        double noNum = submissionsCounter - yesNum;
        String s = "";

        double percentYes = ((100* yesNum)/(100*submissionsCounter));
        double percentNo = ((100*noNum)/(100*submissionsCounter));

        s = ("Yes: " + (percentYes*100) + "%, No: " + (percentNo*100) + "% " + "\n");
        return s;
    }

    /**
     * finds the approval rate 
     */
    public String numTextbook(String name){
        double yesNum = textbookHash.get(name);
        double noNum = submissionsCounter - yesNum;
        String s = "";

        double percentYes = ((100* yesNum)/(100*submissionsCounter));
        double percentNo = ((100*noNum)/(100*submissionsCounter));

        s = ("Yes: " + (percentYes*100) + "%, No: " + (percentNo*100) + "% " + "\n");
        return s;
    }

    /**
     * finds the approval rate 
     */
    public String numAttendance(String name){
        double yesNum = attendanceHash.get(name);
        double noNum = submissionsCounter - yesNum;
        String s = "";

        double percentYes = ((100* yesNum)/(100*submissionsCounter));
        double percentNo = ((100*noNum)/(100*submissionsCounter));

        s = ("Yes: " + (percentYes*100) + "%, No: " + (percentNo*100) + "% " + "\n");
        return s;
    }

    /**
     * toString method
     */
    public String toString(){
        String s = "";
        if (rateMyProf.getNumVertices() > 0){
            for (int i = 0; i < rateMyProf.getNumVertices(); i++){
                s += "Name: " + getName() + "\n";
                s += "Difficulty Rating: " + this.difficultyRating + "\n";
                s += "Teacher Experience Rating: " + this.expRating + "\n";
                s += "Would they take it again? " + numTakeAgain(getName()) + "\n";
                s += "Is there a TA/ tutors available for the course?" + numTAAvail(getName()) + "\n";
                s += "Is there a required textbook for the course? " + numTextbook(getName()) + "\n";
                s += "Is there a required textbook for the course? " + numAttendance(getName()) + "\n";
                s += "Time spent per week on average (in hours): " + gettimeSpent() + "\n";
                s += "Tips for studying: " + getStudying() + "\n";
                s += "Structure: " + getStructure();
            }
        }
        return s;
    }

    /**
     * for testing purposes
     */
    public static void main (String[] args)
    {
    //     RateProfessor test = new RateProfessor();
    //     System.out.println(test);
    }
}

