import edu.asu.engineeringed.users.*
import edu.asu.engineeringed.publications.*
import edu.asu.engineeringed.courses.*
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
        createCourses()
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
            new AcademicUnit(name:"Mary Lou Fulton Teacher's College", owner:asu).save(failOnError:true)
        def ira = AcademicUnit.findByName("Ira A. Fulton School of Engineering") ?:
            new AcademicUnit(name:
    "Ira A. Fulton School of Engineering", owner:asu).save(failOnError:true)
        def cte = AcademicUnit.findByName("College of Technology and Innovcation") ?:
            new AcademicUnit(name:"College of Technology and Innovcation", owner:asu).save(failOnError:true)
        def cidse = AcademicUnit.findByName("School of Computing, Informatics, and Decision Systems Engineering") ?:
            new AcademicUnit(name:"School of Computing, Informatics, and Decision Systems Engineering", owner:asu).save(failOnError:true)
        ira.addToSubUnits(cidse)
        cidse.addToParentUnits(ira)
        def semte = AcademicUnit.findByName("School for Engineering of Matter, Transport, and Energy") ?:
            new AcademicUnit(name:"School for Engineering of Matter, Transport, and Energy", owner:asu).save(failOnError:true)
        ira.addToSubUnits(semte)
        semte.addToParentUnits(ira)
        def engineeringEd = AcademicUnit.findByName("Engineering Education") ?:
            new AcademicUnit(name:"Engineering Education", owner:asu).save(failOnError:true)
        maryLou.addToSubUnits(engineeringEd)
        ira.addToSubUnits(engineeringEd)
        engineeringEd.addToParentUnits(ira)
        engineeringEd.addToParentUnits(maryLou)
    }
    
    def createDomainAreas() {
        def engEd = DomainArea.findByName("Engineering Education") ?:
            new DomainArea(name:"Engineering Education").save(failOnError:true)
        def math = DomainArea.findByName("Mathematics") ?:
            new DomainArea(name:"Mathematics").save(failOnError:true)
        def edu = DomainArea.findByName("Education") ?:
            new DomainArea(name:"Education").save(failOnError:true)
        edu.addToSubAreas(engEd)
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
        loadProfessorsFromCSV()
        createASUFaculty()
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
            ).save(failOnError:true)
            prof.addToInterests(compArch)
            if(thesisDomainArea && thesisInstitution){
                def degree = new Degree(name:"Doctorate of Philosophy", 
                type:"PhD",
                primary:thesisDomainArea,
                grantor:thesisInstitution,
                person:prof
                ).save(failOnError:true)
                prof.addToDegrees(degree)
            }
            
        }
    }
    
    def createASUFaculty(){
        def maryLou = AcademicUnit.findByName("Mary Lou Fulton Teacher's College")
        def ira = AcademicUnit.findByName("Ira A. Fulton School of Engineering")
        def cte = AcademicUnit.findByName("College of Technology and Innovcation")
        def cidse = AcademicUnit.findByName("School of Computing, Informatics, and Decision Systems Engineering")
        def semte = AcademicUnit.findByName("School for Engineering of Matter, Transport, and Energy")
        def engineeringEd = AcademicUnit.findByName("Engineering Education") 
        
        def ganesh = Professor.findByName("Tirupalavanam Ganesh") ?:
            new Professor(name:"Tirupalavanam Ganesh", 
                          position:"Assistant Professor, Engineering Education Engineering Education Program Coordinator",
                          homepageUrl:"https://webapp4.asu.edu/directory/person/37224",
                          headshotUrl:"https://webapp4.asu.edu/directory/photo?user=37224",
                          researchInterests:"engineering education, qualitative research methods, curriculum studies").save(failOnError:true)
        UnitFaculty.create(ganesh,maryLou)
        UnitFaculty.create(ganesh,ira)
        UnitFaculty.create(ganesh,semte)
        UnitFaculty.create(ganesh,engineeringEd,true,true)
        def institution = Institution.findByName("Arizona State University")
        def degree = new Degree(name:"Doctorate of Philosophy", 
                                type:"PhD",
                                grantor:institution,
                                yearConferred:2003,
                                person:ganesh).save(failOnError:true)
        ganesh.addToDegrees(degree)
        
        
        def dale = Professor.findByName("Dale Baker") ?:
            new Professor(name:"Dale Baker",
                          position:"Professor, Science Education",
                          homepageUrl:"https://webapp4.asu.edu/directory/person/14845",
                          headshotUrl:"http://engineeringed.asu.edu/images/dale_baker.jpg",
                          researchInterests:"Science, gender, equity and assessment").save(failOnError:true)
        UnitFaculty.create(dale,maryLou)
        UnitFaculty.create(dale,engineeringEd)
        institution = Institution.findByNameLike("Rutgers")?: 
        new Institution(name:"Rutgers, The State University of New Jersey", schedule:'semester').save(failOnError:true)
        degree = new Degree(name:"Doctorate of Philosophy", 
                            type:"PhD",
                            grantor:institution,
                            yearConferred:1981,
                            person:dale).save(failOnError:true)
        dale.addToDegrees(degree)
        
        
        def collofello = Professor.findByName("James Collofello") ?:
            new Professor(name:"James Collofello",
                          position:"Associate Dean and Professor, Engineering",
                          homepageUrl:"https://webapp4.asu.edu/directory/person/25234",
                          headshotUrl:"https://webapp4.asu.edu/directory/photo?user=25234",
                          researchInterests:"software engineering, undergraduate engineering education").save(failOnError:true)
        UnitFaculty.create(collofello,ira)
        UnitFaculty.create(collofello,cidse)
        UnitFaculty.create(collofello,engineeringEd)
        institution = Institution.findByNameLike("Northwestern%")
        degree = new Degree(name:"Doctorate of Philosophy",
                            type:"PhD",grantor:institution,
                            yearConferred:1979,
                            person:collofello).save(failOnError:true)
        collofello.addToDegrees(degree)
        
        
        def odesma = Professor.findByName("Odesma Dalrymple") ?:
            new Professor(name:"Odesma Dalrymple", 
                          position:"Assistant Professor, Department of Engineering",
                          homepageUrl:"https://webapp4.asu.edu/directory/person/1468223",
                          headshotUrl:"https://webapp4.asu.edu/directory/photo?user=1468223",
                          researchInterests:"engineering education, reverse engineering, electrical and computer engineering, influence of informal experience on engineering learning").save(failOnError:true)
        UnitFaculty.create(odesma,cte)
        UnitFaculty.create(odesma,engineeringEd)
        institution = Institution.findByNameLike("Purdue%")
        degree = new Degree(name:"Doctorate of Philosophy",
                            type:"PhD",
                            grantor:institution,
                            yearConferred:2009,
                            person:odesma).save(failOnError:true)
        odesma.addToDegrees(degree)
        
        
        def krause = Professor.findByName("Stephen Krause") ?:
            new Professor(name:"Stephen Krause",
                          position:"Professor, Materials Science and Engineering",
                          homepageUrl:"https://webapp4.asu.edu/directory/person/57601",
                          headshotUrl:"https://webapp4.asu.edu/directory/photo?user=57601",
                          researchInterests:"materials science and engineering, k-12 engineering education, concept inventories").save(failOnError:true)
        UnitFaculty.create(krause,ira)
        UnitFaculty.create(krause,semte)
        UnitFaculty.create(krause,engineeringEd)
        institution = Institution.findByNameLike("University of Michigan%")
        degree = new Degree(name:"Doctorate of Philosophy", 
                            type:"PhD",grantor:institution, 
                            yearConferred:1981,
                            person:krause).save(failOnError:true)
        krause.addToDegrees(degree)
        
        
        def mcKenna = Professor.findByName("Ann McKenna") ?:
            new Professor(name:"Ann McKenna", 
                          position:"Associate Professor",
                          homepageUrl:"https://webapp4.asu.edu/directory/person/1625925",
                          headshotUrl:"https://webapp4.asu.edu/directory/photo?user=1625915",
                          researchInterests:"cognitive and social processes of design, design teaching and learning, adaptive expertise in design and innovation, teaching approaches of engineering faculty, diffusion and impact of curricular innovations").save(failOnError:true)
        UnitFaculty.create(mcKenna,cte)
        UnitFaculty.create(mcKenna,engineeringEd)
        institution = Institution.findByNameLike("University of California-Berkeley%")
        degree = new Degree(name:"Doctorate of Philosophy", 
                            type:"PhD",grantor:institution, 
                            yearConferred:2001,
                            person:mcKenna).save(failOnError:true)
        mcKenna.addToDegrees(degree)
        
        
        def middleton = Professor.findByName("James Middleton") ?:
            new Professor(name:"James Middleton", 
                          position:"Director, CRESMET and Professor, Mathematics Education",
                          homepageUrl:"https://webapp4.asu.edu/directory/person/49406",
                          headshotUrl:"https://webapp4.asu.edu/directory/photo?user=49406",
                          researchInterests:"mathematics, learning psychology, curriculum development, middle school mathematics").save(failOnError:true)
        UnitFaculty.create(middleton,ira)
        UnitFaculty.create(middleton,engineeringEd)
        institution = Institution.findByNameLike("University of Wisconsin%")
        degree = new Degree(name:"Doctorate of Philosophy", 
                            type:"PhD",
                            grantor:institution, 
                            yearConferred:1992,
                            person:middleton).save(failOnError:true)
        middleton.addToDegrees(degree)
        
        
        def ramakrishna = Professor.findByName("B Ramakrishna") ?:
            new Professor(name:"B Ramakrishna",
                          position:"Associate Professor, School for Engineering of Matter, Transport, and Energy",
                          homepageUrl:"https://webapp4.asu.edu/directory/person/83829",
                          headshotUrl:"https://webapp4.asu.edu/directory/photo?user=83829",
                          researchInterests:"biomineralization, nano biotechnology, k-12 science and engineering education").save(failOnError:true)
        UnitFaculty.create(ramakrishna,ira)
        UnitFaculty.create(ramakrishna,semte)
        UnitFaculty.create(ramakrishna,engineeringEd)
        
        institution = Institution.findByNameLike("Indian Institute of Technology%")?:
        new Institution(name:"Indian Institute of Technology", schedule:'semester').save(failOnError:true)
        degree = new Degree(name:"Doctorate of Philosophy", 
                            type:"PhD",
                            grantor:institution, 
                            yearConferred:1992,
                            person:ramakrishna).save(failOnError:true)
        ramakrishna.addToDegrees(degree)
        
        
        def chell = Professor.findByName("Chell Roberts") ?:
            new Professor(name:"Chell Roberts", 
                          position:"Chair and Professor, Department of Engineering",
                          homepageUrl:"https://webapp4.asu.edu/directory/person/35735",
                          headshotUrl:"https://webapp4.asu.edu/directory/photo?user=35735",
                          researchInterests:"engineering design and curriculum, engineering education, industrial engineering").save(failOnError:true)
        UnitFaculty.create(chell,cte)
        UnitFaculty.create(chell,engineeringEd)
        institution = Institution.findByNameLike("Virginia %tech%")
        degree = new Degree(name:"Doctorate of Philosophy", 
                            type:"PhD",grantor:institution, 
                            yearConferred:1991,
                            person:chell).save(failOnError:true)
        chell.addToDegrees(degree)
        
        
        def squires = Professor.findByName("Kyle Squires") ?:
            new Professor(name:"Kyle Squires", 
                          position:"School Director and Professor, School for Engineering of Transport, Matter, and Energy",
                          homepageUrl:"https://webapp4.asu.edu/directory/person/94222",
                          headshotUrl:"https://webapp4.asu.edu/directory/photo?user=94222",
                          researchInterests:"computational science and engineering, fluid mechanics").save(failOnError:true)
        UnitFaculty.create(squires,ira)
        UnitFaculty.create(squires,semte)
        UnitFaculty.create(squires,engineeringEd)
        institution = Institution.findByNameLike("Stanford%")
        degree = new Degree(name:"Doctorate of Philosophy",
                            type:"PhD",
                            grantor:institution,
                            yearConferred:1990,
                            person:squires).save(failOnError:true)
        squires.addToDegrees(degree)     
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
    
    def createCourses() {
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
