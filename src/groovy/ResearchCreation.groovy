import edu.asu.engineeringed.research.*
import edu.asu.engineeringed.*
class ResearchCreation {
    private static final ProfessorService professorService = new ProfessorService()
    private static final StudentService studentService = new StudentService()
    
    public static void loadData(){
        createResearchers()
        createProjects()
        createInvestigations()
    }
    
    private static void createResearchers(){
        def nick = Researcher.findByName("Nicholas Vaidyanathan") ?:
            new Researcher(name:"Nicholas Vaidyanathan",
                           email:"nicholas.vaidyanathan@asu.edu").save(failOnError:true)
        addASUEngineeringAffiliated()
    }
    
    private static void addASUEngineeringAffiliated(){
        def students = studentService.getASUEngineeringEducationStudents()
        students.each{
            def researcher = Researcher.findByName(it.name) ?:
                new Researcher(name:it.name, email:it.email).save(failOnError:true)
        }
        def faculty = professorService.getASUEngineeeringEducationFaculty()
        println "faculty is ${faculty}"
        faculty.each{
            def researcher = Researcher.findByName(it.name) ?:
                new Researcher(name:it.name, email:it.email).save(failOnError:true)
        }
    }
    private static void createProjects(){
        
    }
    
    private static void createInvestigations(){
        
    }
}

