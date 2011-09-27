package edu.asu.engineeringed.research

class CourseAnalysisController {
    def courseAnalysisService
    
    def index() { 
        def domains = courseAnalysisService.getDomains()
        [domainAreaInstanceList:domains]
    }
    
    def selectedDomain() {
        def info = courseAnalysisService.getDomainInformation(params.domain)
        println "info is ${info}"
        [
            courses:info.courses,
            professors:info.professors,
            textbooks:info.textbooks
        ]
    }
}
