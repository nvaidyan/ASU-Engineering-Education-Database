import edu.asu.engineeringed.*
import au.com.bytecode.opencsv.*
import au.com.bytecode.opencsv.bean.*
import edu.asu.engineeringed.publications.*
import edu.asu.engineeringed.courses.*

class CourseCreation {
    public static void loadData(){
        createTextbooks()
        createCourses()
        createASUEngEdCourses()
    }
    
    private static void createTextbooks() {
        CSVReader reader = new CSVReader(new FileReader("textbooks.csv"));
        String [] nextLine;
        nextLine = reader.readNext() //skip the header
        def compArch = DomainArea.findByName("Computer Architecture")
        while ((nextLine = reader.readNext()) != null) {
        // nextLine[] is an array of values from the line
            def authors = nextLine[2].split(",")
            authors = authors.collect {
                Author.findByName(it) ?: new Author(name:it).save(failOnError:true)
            }
            def textbook = Textbook.findByIsbn(nextLine[0]) ?:
            new Textbook(
                isbn:nextLine[0],
                title:nextLine[1],
                edition:nextLine[3],
                authors: authors
            ).save(failOnError:true)
            textbook.addToDomains(compArch)
        }
    }
    
    private static void createCourses() {
        CSVReader reader = new CSVReader(new FileReader("courses.csv"));
        String [] nextLine;
        nextLine = reader.readNext() //skip the header
        def compArch = DomainArea.findByName("Computer Architecture")
        while ((nextLine = reader.readNext()) != null) {
        // nextLine[] is an array of values from the line
            def institution = Institution.findByName(nextLine[1])
            def department = AcademicUnit.findByOwnerAndName(institution, nextLine[2])?:
                new AcademicUnit(name:nextLine[2], owner:institution).save(failOnError:true)
            def course = Course.get(nextLine[0]) ?:
            new Course(
                title:nextLine[3],
            ).save(failOnError:true)
            course.addToDomains(compArch)
            course.addToDepartments(department)
        }
    }
    
    private static void createASUEngEdCourses(){
        def asu = Institution.findByName("Arizona State University")
        def engEd = "Engineering Education"
        def unit = AcademicUnit.findByOwnerAndName(asu, engEd )
        def domain = DomainArea.findByName(engEd)
        def courses = [
            "ene701" : [
                         title:"Fundamentals of Engineering Education",
                         description:"Equity in Science Education, Fundamentals of Engineering ED, Assessment & Evaluation in Engineering Education, Research Methods in Engineering Education.  A small class emphasizing discussion, presentations by students, and written research papers",
                         url:"https://webapp4.asu.edu/catalog/app?component=%24DirectLink&page=Course&service=direct&session=T&sp=SWEST&sp=SENE&sp=S+691&sp=S2117&sp=SED216&sp=S83831"
                       ],
            "ene702" : [
                         title:"Assessment & Evaluation in Engineering Education",
                         description:"A small class emphasizing discussion, presentations by students, and written research papers",
                         url:"https://webapp4.asu.edu/catalog/app?component=%24DirectLink&page=Course&service=direct&session=T&sp=SWEST&sp=SENE&sp=S+691&sp=S2117&sp=SED250&sp=S83833"
                       ],
            "ene703" : [
                         title:"Research Methods in Engineering Education",
                         description:"A small class emphasizing discussion, presentations by students, and written research papers",
                         url:"https://webapp4.asu.edu/catalog/app?component=%24DirectLink&page=Course&service=direct&session=T&sp=SWEST&sp=SENE&sp=S+691&sp=S2117&sp=SED216&sp=S83834"
                       ],
        ]
       courses.each { k,v ->
           def course = Course.findByTitle(v.title) ?:
                        new Course(title:v.title, 
                                   description:v.description,
                                   url:v.url).save(failOnError:true)
           course.addToDomains(domain)
           unit.addToCourses(course)
       }
    }
    
}

