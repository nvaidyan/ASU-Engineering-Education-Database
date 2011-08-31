package edu.asu.engineeringed.publications

class ConferencePaper extends Publication {
    static belongsTo = [Conference,Author]
    String track
    Boolean best
    static constraints = {
        track()
        best()
    }
}
