package edu.asu.engineeringed.research
import edu.asu.engineeringed.DomainArea
import edu.asu.engineeringed.Professor
import edu.asu.engineeringed.publications.Textbook
import edu.asu.engineeringed.courses.Course
class CourseAnalysisService {

    def getDomains() {
        DomainArea.list()
    }
    def getDomainInformation(String domainName) {
        def domain = DomainArea.findByName(domainName)
        if(domain){
            println "in service domain is ${domain}"
            def courses = Course.list().findAll{ 
                it.domains.contains(domain)
            }
            /* def professors = Professor.findAllByInterestsInList([domain])
            def textbooks = Textbook.findAllByDomainsInList([domain])*/
            def professors = Professor.list().findAll {
                it.interests.contains(domain)
            }
            def textbooks = Textbook.list().findAll {
                it.domains.contains(domain)
            }
            [
                courses:courses,
                professors:professors,
                textbooks:textbooks
            ]
        }
    }
}
