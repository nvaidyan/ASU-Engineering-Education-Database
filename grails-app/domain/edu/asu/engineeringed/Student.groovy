package edu.asu.engineeringed

abstract class Student extends AbstractPerson {

    String headshotUrl
    String bio
    String yearInSchool
    Double gpa
    
    static constraints = {
        name blank:false, unique:true
        email nullable:true, email:true
        headshotUrl nullable:true, url:true
        bio nullable:true
        yearInSchool nullable:true
        gpa nullable:true, min:0.0d, max:4.0d
    }
}