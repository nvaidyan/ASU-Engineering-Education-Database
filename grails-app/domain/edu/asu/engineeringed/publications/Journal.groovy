package edu.asu.engineeringed.publications
import edu.asu.engineeringed.AbstractEntity

class Journal extends AbstractEntity {
    static belongsTo = Organization
    Integer volume
    Integer issue
    String url
    
    static constraints = {
        name blank:false, unique:true
        volume min:1
        issue min:1
        url nullable:true, url:true
    }
}
