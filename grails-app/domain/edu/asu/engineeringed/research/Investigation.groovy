package edu.asu.engineeringed.research

class Investigation {
    Researcher researcher
    Project project
    String role
    
    static constraints = {
        role inList:['lead', 'collaborator']
    }
}
