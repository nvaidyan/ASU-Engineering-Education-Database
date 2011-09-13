package edu.asu.engineeringed

class Media {
    private static final int GIGABYTES = 1024 * 1024 * 1024
    private static final int MAX_SIZE = 1 * GIGABYTES //max size 1GB
    
    byte[] fileData
    String name
    String description
    long size
    String extension
    Date dateCreated
    Date lastUpdated
    
    static constraints = {
		fileData(nullable:false, minSize:1, maxSize:MAX_SIZE)
		name(blank:false, unique:true)
		description(maxSize:1000)
		size(editable:false)
                extension(nullable:true, editable:false)
    }
    
    @Override
    public String toString() { return "${name}" }

    public void setFileData(byte[] data)
    {
        this.fileData = data
        size = data.length
    }
    public void setName(String name){
        this.name = name
        extension = (name.lastIndexOf(".") == -1) ? '' : 
        name.substring(name.lastIndexOf(".")+1)
    }
}