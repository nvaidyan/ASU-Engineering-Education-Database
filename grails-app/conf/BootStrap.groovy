import ProfessorCreation
import PresentationCreation
import UserCreation
import BasicCreation
import CourseCreation

class BootStrap {
    def init = { servletContext ->
        BasicCreation.loadData()
        ProfessorCreation.loadData()
        //PresentationCreation.loadData()
        CourseCreation.loadData()
        UserCreation.loadData()
    }
    def destroy = {
    }
}
