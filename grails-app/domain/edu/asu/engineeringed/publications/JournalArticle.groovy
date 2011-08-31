package edu.asu.engineeringed.publications

class JournalArticle extends Publication {
    static belongsTo = [Journal,Author]
    Date published
    Integer startPage
    Integer endPage
    String url
    static constraints = {
        published()
        startPage min:1
        endPage(validator: {
                val, obj -> obj.startPage <= val
        })
        url nullable:true, url:true
    }
}
