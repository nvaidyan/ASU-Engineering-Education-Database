package edu.asu.engineeringed

class ProfessorService {

    def getASUEngineeeringEducationFaculty() {
        def asu = Institution.findByName("Arizona State University")
        def engEd = AcademicUnit.findByOwnerAndName(asu, 
                                                    "Engineering Education")
        engEd.faculty.sort{ it.isChair }
        engEd.faculty.collect{ it.professor }
    }
}
