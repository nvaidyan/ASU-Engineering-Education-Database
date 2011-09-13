package edu.asu.engineeringed.presentations
import edu.asu.engineeringed.Media

class Presentation {
    Date start
    Date end
    String location
    String title
    String description
    
    static hasMany = [attachments:Media]
    static belongsTo = [speaker:Speaker]
    static constraints = {
        start validator: {
            val,obj -> val <= obj.end
        }
        end validator: {
            val,obj -> val >= obj.start
        }
        location blank:false
        title blank:false
        description blank:false
    }
    
    static mapping = {
        description type:'text'
    }
}
