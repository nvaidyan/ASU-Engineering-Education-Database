package edu.asu.engineeringed.presentations

class PresentationController {
    static scaffold = true
    def presentationService
    
    def index() {
        def presentations = presentationService.getAllPresentations()
        [presentationInstanceList:presentations]
    }
}
