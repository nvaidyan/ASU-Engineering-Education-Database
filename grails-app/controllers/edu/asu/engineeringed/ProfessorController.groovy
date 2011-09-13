package edu.asu.engineeringed

class ProfessorController {
    def professorService
    static scaffold = true
    
    def program(){
        def faculty = professorService.getASUEngineeeringEducationFaculty()
        [facultyInstanceList:faculty]
    }
}
