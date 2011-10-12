import ProfessorCreation
import PresentationCreation
import UserCreation
import BasicCreation
import CourseCreation
import ResearchCreation

class BootStrap {
    def init = { servletContext ->
        BasicCreation.loadData()
        ProfessorCreation.loadData()
        PresentationCreation.loadData()
        CourseCreation.loadData()
        UserCreation.loadData()
        ResearchCreation.loadData()
        MediaCreation.loadData()
        AccomplishmentCreation.loadData()
    }
    def destroy = {
    }
}
