package edu.asu.engineeringed;

/**
 *
 * @author nvaidyan
 */
public abstract class AbstractPerson extends AbstractEntity implements InternetPerson {
    private String email;
    
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail() { return email; }
    
    public String getLastName() { 
        String[] splitNames = getName().split(" ");
        return splitNames[splitNames.length-1];
    }
    
    public String getFirstName() { 
        String[] splitNames = getName().split(" ");
        return splitNames[0];
    }
}
