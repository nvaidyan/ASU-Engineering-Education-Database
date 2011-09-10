import edu.asu.engineeringed.users.*
import edu.asu.engineeringed.publications.*
import edu.asu.engineeringed.*
import au.com.bytecode.opencsv.*
import au.com.bytecode.opencsv.bean.*

class BootStrap {
    def init = { servletContext ->
        createInstitutions()
        createAcademicUnits()
        createDomainAreas()
        createProfessors()
        createTextbooks()
        createRoles()
        createUsers()
    }
    def destroy = {
    }
    
    def createInstitutions() {
        /*def asu = Institution.findByName("Arizona State University") ?:
            new Institution(name:"Arizona State University", schedule:'semester').save(failOnError:true)
        def purdue = Institution.findByName("Purdue University") ?:
            new Institution(name:"Purdue University", schedule:'semester').save(failOnError:true)*/
        CSVReader reader = new CSVReader(new FileReader("universities.csv"));
        String [] nextLine;
        HeaderColumnNameTranslateMappingStrategy strat = new HeaderColumnNameTranslateMappingStrategy();
        strat.setType(Institution.class);
        strat.setColumnMapping(["universityName":"name", "carnegieClassification":"carnegieClassification", "schedule":"schedule"]);

        CsvToBean csv = new CsvToBean();
        List list = csv.parse(strat, reader);
        list.each {
            def already = Institution.findByName(it.name) ?: it.save(failOnError:true)
        }
    }
    
    def createAcademicUnits() {
        def asu = Institution.findByName("Arizona State University")
        def maryLou = AcademicUnit.findByName("Mary Lou Fulton Teacher's College") ?:
            new AcademicUnit(name:"Mary Lou Fulton Teacher's College", institution:asu).save(failOnError:true)
        def ira = AcademicUnit.findByName("Ira A. Fulton School of Engineering") ?:
            new AcademicUnit(name:
    "Ira A. Fulton School of Engineering", institution:asu).save(failOnError:true)
        def cte = AcademicUnit.findByName("College of Technology and Innovcation") ?:
            new AcademicUnit(name:"College of Technology and Innovcation", institution:asu).save(failOnError:true)
        def cidse = AcademicUnit.findByName("School of Computing, Informatics, and Decision Systems Engineering") ?:
            new AcademicUnit(name:"School of Computing, Informatics, and Decision Systems Engineering", institution:asu).save(failOnError:true)
        ira.addToSubUnits(cidse)
        cidse.addToParentUnits(ira)
        def semte = AcademicUnit.findByName("School for Engineering of Matter, Transport, and Energy") ?:
            new AcademicUnit(name:"School for Engineering of Matter, Transport, and Energy", institution:asu).save(failOnError:true)
        ira.addToSubUnits(semte)
        semte.addToParentUnits(ira)
    }
    
    def createDomainAreas() {
        def engEd = DomainArea.findByName("Engineering Education") ?:
            new DomainArea(name:"Engineering Education").save(failOnError:true)
        def cs = DomainArea.findByName("Computer Science") ?:
            new DomainArea(name:"Computer Science").save(failOnError:true)
        def ee = DomainArea.findByName("Electrical Engineering") ?:
            new DomainArea(name:"Electrical Engineering").save(failOnError:true)
        def mech = DomainArea.findByName("Mechanical Engineering") ?:
            new DomainArea(name:"Mechanical Engineering").save(failOnError:true)
        def civil = DomainArea.findByName("Civil Engineering") ?:
            new DomainArea(name:"Civil Engineering").save(failOnError:true)
        def industrial = DomainArea.findByName("Industrial Engineering") ?:
            new DomainArea(name:"Industrial Engineering").save(failOnError:true)
        def chemical = DomainArea.findByName("Chemical Engineering") ?:
            new DomainArea(name:"Chemical Engineering").save(failOnError:true)
        def bio = DomainArea.findByName("Biomedical Engineering") ?:
            new DomainArea(name:"Biomedical Engineering").save(failOnError:true)
        def compArch = DomainArea.findByName("Computer Architecture") ?:
            new DomainArea(name:"Computer Architecture").save(failOnError:true)
        cs.addToSubAreas(compArch)
        ee.addToSubAreas(compArch)
        def swEng = DomainArea.findByName("Software Engineering") ?:
            new DomainArea(name:"Software Engineering").save(failOnError:true)
        cs.addToSubAreas(swEng)
    }
    
    def createProfessors() {
        def maryLou = AcademicUnit.findByName("Mary Lou Fulton Teacher's College")
        def ira = AcademicUnit.findByName("Ira A. Fulton School of Engineering")
        def cte = AcademicUnit.findByName("College of Technology and Innovcation")
        def cidse = AcademicUnit.findByName("School of Computing, Informatics, and Decision Systems Engineering")
        def semte = AcademicUnit.findByName("School for Engineering of Matter, Transport, and Energy")
        loadProfessorsFromCSV()
        def ganesh = Professor.findByName("Tirupalavanam Ganesh") ?:
            new Professor(name:"Tirupalavanam Ganesh", affiliations:[maryLou, ira,semte]).save(failOnError:true)
        def dale = Professor.findByName("Dale Baker") ?:
            new Professor(name:"Dale Baker", affiliations:[maryLou]).save(failOnError:true)
        def collofello = Professor.findByName("James Collofello") ?:
            new Professor(name:"James Collofello", affiliations:[ira,cidse]).save(failOnError:true)
        def odesma = Professor.findByName("Odesma Dalrymple") ?:
            new Professor(name:"Odesma Dalrymple", affiliations:[cte]).save(failOnError:true)
        def krause = Professor.findByName("Stephen Krause") ?:
            new Professor(name:"Stephen Krause", affiliations:[ira,semte]).save(failOnError:true)
        def mcKenna = Professor.findByName("Ann McKenna") ?:
            new Professor(name:"Ann McKenna", affiliations:[cte]).save(failOnError:true)
        def middleton = Professor.findByName("James Middleton") ?:
            new Professor(name:"James Middleton", affiliations:[ira]).save(failOnError:true)
        def ramakrishna = Professor.findByName("B Ramakrishna") ?:
            new Professor(name:"B Ramakrishna", affiliations:[ira,semte]).save(failOnError:true)
        def chell = Professor.findByName("Chell Roberts") ?:
            new Professor(name:"Chell Roberts", affiliations:[cte]).save(failOnError:true)
        def squires = Professor.findByName("Kyle Squires") ?:
            new Professor(name:"Kyle Squires", affiliations:[ira,semte]).save(failOnError:true)
    }
    
    def loadProfessorsFromCSV() {
        CSVReader reader = new CSVReader(new FileReader("professors.csv"));
        String [] nextLine;
        nextLine = reader.readNext() //skip the header
        def compArch = DomainArea.findByName("Computer Architecture")
        while ((nextLine = reader.readNext()) != null) {
        // nextLine[] is an array of values from the line
            def thesisDomainArea = DomainArea.findByName(nextLine[8])
            def thesisInstitution = Institution.findByName(nextLine[7])
            def prof = Professor.findByName(nextLine[1]) ?:
            new Professor(
                name:nextLine[1],
                email:nextLine[2],
                homepageUrl:(nextLine[3] !="NULL") ? nextLine[3] : null,
                yearStartedTeaching:(nextLine[4] != "NULL") ? nextLine[4] : null,
                position:nextLine[5],
                tenured:(nextLine[6] == "1") ? true : false,
                comments:nextLine[9],
                doctoralThesisDomain:thesisDomainArea,
                doctoralAlmaMater:thesisInstitution
            ).save(failOnError:true)
            prof.addToInterests(compArch)
        }
    }
    
    def createTextbooks() {
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
    def createRoles() {
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?:
                       new Role(authority:'ROLE_ADMIN').save(failOnError:true)
        def switchUserRole = Role.findByAuthority('ROLE_SWITCH_USER') ?:
                       new Role(authority:'ROLE_SWITCH_USER').save(failOnError:true)
        def userRole = Role.findByAuthority('ROLE_USER') ?:
                       new Role(authority:'ROLE_USER').save(failOnError:true)
    }
    
    def createUsers(){
        def password = "password"
        def ganesh = User.findByUsername("ganesh") ?:
        new User(username:"ganesh", 
                 email:"tganesh@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def nick = User.findByUsername("nick") ?:
        new User(username:"nick",
                 email:"the_N_Channel@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def katie = User.findByUsername("katie") ?:
        new User(username:"katie",
                 email:"katherine.muto@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def chrissy = User.findByUsername("chrissy") ?:
        new User(username:"chrissy",
                 email:"Christina.Foster@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def wunmi = User.findByUsername("wunmi") ?:
        new User(username:"wunmi",
                 email:"Omowunmi.Isaacs-Sodeye@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def carl = User.findByUsername("carl") ?:
        new User(username:"carl",
                 email:"Carl.Whitesel@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        def patrick = User.findByUsername("patrick") ?:
        new User(username:"patrick",
                 email:"Patrick.Schwab@asu.edu",
                 enabled:true,
                 password:password).save(failonError:true)
        UserRole.create(nick,Role.findByAuthority('ROLE_ADMIN'))
        UserRole.create(nick, Role.findByAuthority('ROLE_SWITCH_USER'))
        UserRole.create(ganesh, Role.findByAuthority('ROLE_USER'))
        UserRole.create(katie, Role.findByAuthority('ROLE_USER'))
        UserRole.create(chrissy, Role.findByAuthority('ROLE_USER'))
        UserRole.create(wunmi, Role.findByAuthority('ROLE_USER'))
        UserRole.create(carl, Role.findByAuthority('ROLE_USER'))
        UserRole.create(patrick, Role.findByAuthority('ROLE_USER'))
    }
}
