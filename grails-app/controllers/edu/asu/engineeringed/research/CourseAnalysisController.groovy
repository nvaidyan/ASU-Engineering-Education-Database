package edu.asu.engineeringed.research

class CourseAnalysisController {

    def index() { 
        def domains = DomainArea.list()
        [domainAreaInstanceList:domains]
    }
}
