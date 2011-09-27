package edu.asu.engineeringed

import edu.asu.engineeringed.courses.Course
import org.apache.commons.lang.builder.HashCodeBuilder

class UnitCourse implements Serializable, Comparable {
    Course course
    AcademicUnit unit
    Boolean isRequired = true
    Boolean isUpperDivision = true
    Boolean isHonors = false
    Integer courseNumber = 100
    
    boolean equals(other) {
        if (!(other instanceof UnitCourse)) {
                return false
        }

        other.course?.id == course?.id &&
        other.unit?.id == unit?.id &&
        other.isRequired == isRequired &&        
        other.isUpperDivision == isUpperDivision &&
        other.isHonors == isHonors &&
        other.courseNumber == courseNumber
    }

    int compareTo(obj) {
       courseNumber >= obj.courseNumber
    }
    int hashCode() {
            def builder = new HashCodeBuilder()
            if (course) builder.append(course.id)
            if (unit) builder.append(unit.id)
            if(isRequired) builder.append(isRequired)
            if(isUpperDivision) builder.append(isUpperDivision)
            if(isHonors) builder.append(isHonors)
            builder.toHashCode()
    }

    static UnitCourse get(long courseId, long unitId) {
            find 'from UnitCourse where course.id=:courseId and unit.id=:unitId',
                    [courseId: courseId, unitId: unitId]
    }

    static UnitCourse create(Course course, AcademicUnit unit, boolean flush = true) {
            def creation = new UnitCourse(course: course, unit: unit).save(flush: flush, insert: true)
            if(creation){ createRelationships(creation) }
            creation
    }
    private static final void createRelationships(UnitCourse creation){
        creation.course.addToDepartments(creation)
        creation.unit.addToCourses(creation)
    }
    static boolean remove(Course course, AcademicUnit unit, boolean flush = false) {
            UnitCourse instance = UnitCourse.findByCourseAndUnit(course, unit)
            if (!instance) {
                    return false
            }

            instance.delete(flush: flush)
            true
    }

    static void removeAll(Course course) {
            executeUpdate 'DELETE FROM UnitCourse WHERE course=:course', [course: course]
    }

    static void removeAll(AcademicUnit unit) {
            executeUpdate 'DELETE FROM UnitCourse WHERE unit=:unit', [unit: unit]
    }

    static mapping = {
            id composite: ['course', 'unit']
            version false
    }
    
    @Override
    public String toString(){ "${unit.owner} ${unit} ${course}"}
}
