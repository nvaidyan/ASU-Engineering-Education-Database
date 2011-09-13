package edu.asu.engineeringed

class UndergraduateStudent extends Student {

    static constraints = {
        yearInSchool inList:["Freshman", "Sophomore", "Junior", "Senior"]
    }
}
