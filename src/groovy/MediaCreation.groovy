import edu.asu.engineeringed.Media
import org.codehaus.groovy.grails.commons.ApplicationHolder

class MediaCreation implements CreationAlgorithm {
    public static void loadData(){
        createMedia()
    }
    
    private static final void createMedia() {
        def media = 
        [
            "awesome" : [ 
                          name:"Awesome",
                          description: "This picture should make you smile",
                          fileName:"images/awesome.png" 
                        ]
        ]
        
        media.each { k, v ->
            def grailsApplication = ApplicationHolder.application
            def resource = grailsApplication.mainContext.getResourceByPath(v.fileName)
            def theFile = resource.file
            def newMedia = new Media(fileData:theFile.readBytes(),
                                  name:v.name, 
                                  description:v.description)
            newMedia.save()
        }
    }
}

