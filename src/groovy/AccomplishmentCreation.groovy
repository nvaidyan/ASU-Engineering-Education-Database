import edu.asu.engineeringed.users.Accomplishment
import edu.asu.engineeringed.Media

class AccomplishmentCreation implements CreationAlgorithm {
    public static void loadData(){
        createAccomplishments()
    }
    
    private static final void createAccomplishments() {
        def accomplishments = 
        [
            "Awesome" : [ 
                          name: "Awesome!!!",
                          description: "Thank you for logging on, please help us conduct better research",
                        ]
        ]
        
        accomplishments.each { k,v ->
            def media = Media.findByName(k)
            def accomplishment = new Accomplishment(name: v.name, 
                                                    description:v.description, 
                                                    mediaFile:media).save()
            
        }
    }
}

