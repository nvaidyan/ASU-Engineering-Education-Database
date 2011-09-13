package edu.asu.engineeringed

class Degree extends AbstractEntity {
    String type
    DomainArea primary
    Institution grantor
    Integer yearConferred
    
    static belongsTo = [ person : Professor ]
    
    static constraints = {
        person()
        name blank:false
        type inList:["Bachelor's", "Master's", "PhD"]
        primary nullable:true
        grantor()
        yearConferred min:1930, nullable:true
    }
    
    @Override
    public String toString(){ "${type}, ${grantor}, ${yearConferred}"}
}
