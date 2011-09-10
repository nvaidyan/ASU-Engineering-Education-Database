import edu.asu.engineeringed.users.*
import edu.asu.engineeringed.*
import au.com.bytecode.opencsv.*
import au.com.bytecode.opencsv.bean.*

class BootStrap {
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
            new AcademicUnit(name:"Ira A. Fulton School of Engineering", institution:asu).save(failOnError:true)
        def cidse = AcademicUnit.findByName("School of Computing, Informatics, and Decision Systems Engineering") ?:
            new AcademicUnit(name:"School of Computing, Informatics, and Decision Systems Engineering", institution:asu).save(failOnError:true)
        ira.addToSubUnits(cidse)
        cidse.addToParentUnits(ira)
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
        def ganesh = Professor.findByName("Tirupalavanam Ganesh") ?:
            new Professor(name:"Tirupalavanam Ganesh", affiliations:[maryLou, ira]).save(failOnError:true)
        def dale = Professor.findByName("Dale Baker") ?:
            new Professor(name:"Dale Baker", affiliations:[maryLou]).save(failOnError:true)
        def collofello = Professor.findByName("James Collofello") ?:
            new Professor(name:"James Collofello", affiliations:[ira]).save(failOnError:true)
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
    def init = { servletContext ->
        createInstitutions()
        createAcademicUnits()
        createDomainAreas()
        createProfessors()
        createRoles()
        createUsers()
    }
    def destroy = {
    }
}
