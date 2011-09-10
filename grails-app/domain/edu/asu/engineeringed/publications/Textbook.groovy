package edu.asu.engineeringed.publications

class Textbook extends Publication {
    String isbn
    Byte edition
    
    static constraints = {
        isbn size:10..10
    }
}
