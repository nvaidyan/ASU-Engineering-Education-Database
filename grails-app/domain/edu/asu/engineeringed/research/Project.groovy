package edu.asu.engineeringed.research
import edu.asu.engineeringed.AbstractEntity

class Project extends AbstractEntity {
    String description
    String status
    Date start
    Date end
    
    static hasMany = [collaborators:Investigation]
    
    static constraints = {
        name blank:false, unique:true
        status inList:["Exploration", "Execution", "Completed"]
        description blank:false
        start validator: {
            val, obj -> val <= obj.end
        }
        end validator: {
            val, obj -> val >= obj.start
        }
    }
    
    static mapping = {
        description type:'text'
    }
}
