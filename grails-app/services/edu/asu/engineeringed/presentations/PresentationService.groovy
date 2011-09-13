package edu.asu.engineeringed.presentations

class PresentationService {

    def getAllPresentations() {
        def presentations = Presentation.list(sort:"start", order:'desc')
    }
}
