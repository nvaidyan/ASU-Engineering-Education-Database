import edu.asu.engineeringed.research.*
import edu.asu.engineeringed.*
import java.text.SimpleDateFormat

class ResearchCreation implements CreationAlgorithm {
    private static final ProfessorService professorService = new ProfessorService()
    private static final StudentService studentService = new StudentService()
    
    public static void loadData(){
        createResearchers()
        createProjects()
        createInvestigations()
    }
    
    private static void createResearchers(){
        createNickStuff()
        addASUEngineeringAffiliated()
    }
    private static void createNickStuff(){
        def nick = Researcher.findByName("Nicholas Vaidyanathan") ?:
            new Researcher(name:"Nicholas Vaidyanathan",
                           email:"nicholas.vaidyanathan@asu.edu").save()
        def projects = [
            "Course Analysis": [start:new SimpleDateFormat("MM/dd/yyyy").parse("08/30/2011"), 
                                end:new SimpleDateFormat("MM/dd/yyyy").parse("10/27/2011"),
                                description:"""We're tablulating a public available database 
                                       of engineering courses courses across 
                                       the major engineering schools as defined 
                                       by the US News and World Report. 
                                       This will enable STEM researchers to 
                                       analysize real historical data on 
                                       courses taught across the major institutions 
                                       and query for commonalities and differences, 
                                       providing new insights into what trends are emerging,
                                       where innovation is coming from within individual courses, 
                                       and how curricula are adapting to meet the needs of the Engineer of 2020.
                                       This will also empower educators to compare their planned 
                                       and executed courses with those of their peers and enable new 
                                       faculty to quickly learn the lay of the land.""",
                               status:"Execution"
                               ],
            "Technical Literacy": [start:new SimpleDateFormat("MM/dd/yyyy").parse("02/01/2011"), 
                                end:new SimpleDateFormat("MM/dd/yyyy").parse("05/31/2012"),
                                description:"""we argue that educational
                                        approaches have been slow to adopt the paradigm shifts
                                        inherent since the rise of the Internet. Not tools or
                                        technologies-- those discussions are well known and
                                        difficult -- conversing about them cliché. Rather, we seek
                                        to increase educator awareness about conveying the
                                        fundamental shifts in mindset. One primary and
                                        repetitive example is the separation of presentation of
                                        content from the content itself. We argue that teaching
                                        such concepts helps learners become more analytical,
                                        encourages them to think about their content in terms
                                        understandable to a computer, and develops higher
                                        order cognitive faculties. We present an example
                                        experiment that crosses domain boundaries by urging
                                        the infusion of Hypertext Markup Language content
                                        into standard language courses.""",
                               status:"Execution"
                               ],
        ]
        
        projects.each { key,value -> 
            def project = Project.findByName(key) ?:
            new Project(name:key,
                        start:value.start,
                        end:value.end,
                        description:value.description,
                       status:value.status  
                       ).save()
         Investigation.create(nick, project, true).save()
        }
    }
    
    private static void addASUEngineeringAffiliated(){
        def students = studentService.getASUEngineeringEducationStudents()
        students.each{
            def researcher = Researcher.findByName(it.name) ?:
                new Researcher(name:it.name, email:it.email).save()
        }
        def faculty = professorService.getASUEngineeeringEducationFaculty()
        println "faculty is ${faculty}"
        faculty.each{
            def researcher = Researcher.findByName(it.name) ?:
                new Researcher(name:it.name, email:it.email).save()
        }
    }
    private static void createProjects(){
        def projects = [
            "Constructs": [start:new SimpleDateFormat("MM/dd/yyyy").parse("08/18/2011"), 
                                end:new SimpleDateFormat("MM/dd/yyyy").parse("12/10/2011"),
                                description:"""We're developing a database/glossary of common constructs found
                                       when conducting an in-depth lierature review of the available engineering education literature.
                                       This will also empower new researchers to enter the field 
                                       and view a "10,000 ft map" of what the most important concepts are,  
                                       who are the key players, etc""",
                               status:"Execution"
                               ],
            "Measurement": [start:new SimpleDateFormat("MM/dd/yyyy").parse("02/01/2011"), 
                                end:new SimpleDateFormat("MM/dd/yyyy").parse("05/31/2012"),
                                description:"""In this project, we analyze the question banks available to 
                                        instructors in many engineering textbooks and assess them on principles
                                        of validity, reliability, and basic measurement theory""",
                               status:"Execution"
                               ],
        ]
        
        projects.each { key,value -> 
            def project = Project.findByName(key) ?:
            new Project(name:key,
                        start:value.start,
                        end:value.end,
                        description:value.description,
                       status:value.status  
                       ).save()
         //Investigation.create(nick, project, true).save()
        }
    }
    
    private static void createInvestigations(){
        
    }
}

