package com.example.demo;

import java.util.List;
/**
 * {
    "name": "a",
    "scale": [ "1", "2" ],
    "Y/N": [ "Yes", "Yes", "No", "No" ],
    "Hours": "1-3",
    "structure": "f",
    "studying": "a"
  }
*/

public class Professor {
    private String name;
    private Integer experience;
    private Integer diff;
    private String take;
    private String tutors;
    private String attendance;
    private String textbooks;
    private String hours;
    private String structure;
    private String studying;

    public String getName(){
        return this.name;
    }

    public Integer getExperience(){
        return this.experience;
    }

    public Integer getDiff(){
        return (this.diff);
    }

    public String getTake(){
        return this.take;
    }

    public String getTutors(){
        return this.tutors;
    }

    public String getTextbooks(){
        return this.textbooks;
    }

    public String getAttendance(){
        return this.attendance;
    }

    public String getHours(){
        return this.hours;
    }
    public String getStructure(){
        return this.structure;
    }

    public String getStudying(){
        return this.studying;
    }

    // public String toString(){
    //     String s = "";
    //     s += this.getName().toString() +  this.getDiffScale().toString() +  this.getExScale().toString() +  this.getTakeAgain().toString() + this.getTA().toString() +  this.getTextbook().toString() +  this.getAttendance().toString() +  this.getHours().toString() +  this.getStructure().toString() + this.getStudying().toString();

    //     return s;
    // }    
}
