package edu.asu.engineeringed.publications

class Textbook extends Publication {
    String isbn
    Byte edition
    
    static mapping = {
        id name:"isbn", generator:"assigned"
    }
    
    static constraints = {
        isbn size:10..10
    }
}
