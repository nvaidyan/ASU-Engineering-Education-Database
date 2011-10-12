package edu.asu.engineeringed.users
import edu.asu.engineeringed.AbstractEntity
import edu.asu.engineeringed.Media

class Accomplishment extends AbstractEntity {
    String description
    Media mediaFile
    
    static constraints = {
        name blank:false, unique:true
        description blank:false
    }
}
