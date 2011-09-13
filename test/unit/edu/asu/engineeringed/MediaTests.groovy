package edu.asu.engineeringed



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainUnitTestMixin} for usage instructions
 */
@TestFor(Media)
class MediaTests {

    void testName() {
        String fileName = "ASU_Eg_Ed_Seminar_10.22.10.pdf"
        byte[] dummy = [146, 238, 122]
        def media = Media(name:fileName,description:"fake",fileData:mediaData)
        assertEquals "pdf", media.extension
        assertEquals "ASU_Eg_Ed_Seminar_10.22.10", media.name
    }
}
