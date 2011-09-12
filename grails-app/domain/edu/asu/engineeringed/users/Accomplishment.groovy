package edu.asu.engineeringed.users
import edu.asu.engineeringed.AbstractEntity

class Accomplishment {
    String description
    
    static constraints = {
        name blank:false, unique:true
        description blank:false
    }
}
