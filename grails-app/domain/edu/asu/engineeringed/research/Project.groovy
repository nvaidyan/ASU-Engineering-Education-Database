package edu.asu.engineeringed.research

class Project {
    String name
    String description
    String status
    Date start
    Date end
    
    static hasOne = [lead:Investigator]
    static hasMany = [collaborators:Investigator]
    
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
    
    @Override
    public String toString() { name }
}
