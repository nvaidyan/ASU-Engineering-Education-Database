import edu.asu.engineeringed.*
import au.com.bytecode.opencsv.*
import au.com.bytecode.opencsv.bean.*

class ProfessorCreation implements CreationAlgorithm {
    public static void loadData(){
        loadProfessorsFromCSV()
        createASUFaculty()
    }
    
    private static void loadProfessorsFromCSV() {
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
    
    private static void createASUFaculty(){
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
        UnitFaculty.create(ganesh,maryLou,true)
        UnitFaculty.create(ganesh,ira,true)
        UnitFaculty.create(ganesh,semte,true)
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
        UnitFaculty.create(dale,maryLou,true)
        UnitFaculty.create(dale,engineeringEd,true)
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
        UnitFaculty.create(collofello,ira,true)
        UnitFaculty.create(collofello,cidse,true)
        UnitFaculty.create(collofello,engineeringEd,true)
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
        UnitFaculty.create(odesma,cte,true)
        UnitFaculty.create(odesma,engineeringEd,true)
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
        UnitFaculty.create(krause,ira,true)
        UnitFaculty.create(krause,semte,true)
        UnitFaculty.create(krause,engineeringEd,true)
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
        UnitFaculty.create(mcKenna,cte,true)
        UnitFaculty.create(mcKenna,engineeringEd,true)
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
        UnitFaculty.create(middleton,ira,true)
        UnitFaculty.create(middleton,engineeringEd,true)
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
        UnitFaculty.create(ramakrishna,ira,true)
        UnitFaculty.create(ramakrishna,semte,true)
        UnitFaculty.create(ramakrishna,engineeringEd,true)
        
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
        UnitFaculty.create(chell,cte,true)
        UnitFaculty.create(chell,engineeringEd,true)
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
        UnitFaculty.create(squires,ira,true)
        UnitFaculty.create(squires,semte,true)
        UnitFaculty.create(squires,engineeringEd,true)
        institution = Institution.findByNameLike("Stanford%")
        degree = new Degree(name:"Doctorate of Philosophy",
                            type:"PhD",
                            grantor:institution,
                            yearConferred:1990,
                            person:squires).save(failOnError:true)
        squires.addToDegrees(degree)     
    }
}

