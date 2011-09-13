package edu.asu.engineeringed



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ProfessorService)
class ProfessorServiceTests {

    void testGetASUEngineeringEducationFaculty() {
        def asu = new Institution(name:"Arizona State University", schedule:'semester')
        def engineeringEd = new AcademicUnit(name:"Engineering Education", owner:asu)
        def ganesh = new Professor(name:"Tirupalavanam Ganesh")
        engineeringEd.addToFaculty(ganesh)
        def dale = new Professor(name:"Dale Baker")
        engineeringEd.addToFaculty(dale)
        def collofello = new Professor(name:"James Collofello")
        engineeringEd.addToFaculty(collofello)
        def odesma = new Professor(name:"Odesma Dalrymple")
        engineeringEd.addToFaculty(odesma)
        def krause = new Professor(name:"Stephen Krause")
        engineeringEd.addToFaculty(krause)
        def mcKenna = new Professor(name:"Ann McKenna")
        engineeringEd.addToFaculty(mcKenna)
        def middleton = new Professor(name:"James Middleton")
        engineeringEd.addToFaculty(middleton)
        def ramakrishna = new Professor(name:"B Ramakrishna")
        engineeringEd.addToFaculty(ramakrishna)
        def chell = new Professor(name:"Chell Roberts")
        engineeringEd.addToFaculty(chell)
        def squires = new Professor(name:"Kyle Squires")
        engineeringEd.addToFaculty(squires)
        def expectedFaculty = [ganesh,dale,collofello,odesma,krause,mcKenna,middleton,ramakrishna,chell,squires]
        def faculty = professorService.getASUEngineeringEducationFaculty()
        assertEquals expectedFaculty,faculty
    }
}
