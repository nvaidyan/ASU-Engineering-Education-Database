package edu.asu.engineeringed

import org.apache.commons.lang.builder.HashCodeBuilder

class UnitFaculty implements Serializable, Comparable {
    Professor professor
    AcademicUnit unit
    Boolean isChair = false
    
    boolean equals(other) {
        if (!(other instanceof UnitFaculty)) {
                return false
        }

        other.professor?.id == professor?.id &&
        other.unit?.id == unit?.id &&
        other.isChair == isChair        
    }

    int compareTo(obj) {
       (isChair) ? 1 : professor.lastName.compareToIgnoreCase(obj.professor.lastName)
    }
    int hashCode() {
            def builder = new HashCodeBuilder()
            if (professor) builder.append(professor.id)
            if (unit) builder.append(unit.id)
            if(isChair) builder.append(isChair)
            builder.toHashCode()
    }

    static UnitFaculty get(long professorId, long unitId) {
            find 'from UnitFaculty where professor.id=:professorId and unit.id=:unitId',
                    [professorId: professorId, unitId: unitId]
    }

    static UnitFaculty create(Professor professor, AcademicUnit unit, boolean flush = false) {
            new UnitFaculty(professor: professor, unit: unit).save(flush: flush, insert: true)
    }
    
    static UnitFaculty create(Professor professor, AcademicUnit unit, Boolean isChair, boolean flush = false) {
            new UnitFaculty(professor: professor, unit: unit, isChair:isChair).save(flush: flush, insert: true)
    }

    static boolean remove(Professor professor, AcademicUnit unit, boolean flush = false) {
            UnitFaculty instance = UnitFaculty.findByProfessorAndUnit(professor, unit)
            if (!instance) {
                    return false
            }

            instance.delete(flush: flush)
            true
    }

    static void removeAll(Professor professor) {
            executeUpdate 'DELETE FROM UnitFaculty WHERE professor=:professor', [professor: professor]
    }

    static void removeAll(AcademicUnit unit) {
            executeUpdate 'DELETE FROM UnitFaculty WHERE unit=:unit', [unit: unit]
    }

    static mapping = {
            id composite: ['professor', 'unit']
            version false
    }
    
    @Override
    public String toString(){ (isChair) ? "Chair: ${professor} ${unit}" : "${professor} ${unit}"}
}
