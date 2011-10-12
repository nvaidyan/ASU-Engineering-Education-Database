import edu.asu.engineeringed.*
import au.com.bytecode.opencsv.*
import au.com.bytecode.opencsv.bean.*

class BasicCreation implements CreationAlgorithm {
    public static void loadData(){
        createInstitutions()
        createAcademicUnits()
        createDomainAreas()
        createASUEngEdStudents()
    }
    
    private static void createInstitutions() {
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
    
    private static void createAcademicUnits() {
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
    
    private static void createDomainAreas() {
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
    
    private static void createASUEngEdStudents(){
        def engineeringEd = AcademicUnit.findByName("Engineering Education")
        def students = [
            "katie" : [name:"Katherine Muto-Nelson", 
                       email:"kmuto@asu.edu",
                       yearInSchool:"PhD",
                      ],
            "chrissy" : [name:"Christina Hobson-Foster", 
                       email:"Christina.Hobson@asu.edu",
                       yearInSchool:"PhD",
                      ],
            "wunmi" : [name:"Omowunmi.Isaacs-Sodeye", 
                       email:"Omowunmi.Isaacs-Sodeye@asu.edu",
                       yearInSchool:"PhD",
                      ],
            "carl" : [name:"Carl Whitesel", 
                       email:"cwhitese@asu.edu",
                       yearInSchool:"PhD",
                      ],
            "patrick" : [name:"Patrick Schwab", 
                       email:"pschwab@asu.edu",
                       yearInSchool:"PhD",
                      ],
        ]
        students.each { k,v ->
            def student = Student.findByName(v.name) ?:
                          new GraduateStudent(name:v.name,
                                      email:v.email,
                                      yearInSchool:v.yearInSchool
                                     ).save(failOnError:true)
            engineeringEd.addToStudents(student)
        }
    }
}

