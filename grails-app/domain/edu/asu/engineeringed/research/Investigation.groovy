package edu.asu.engineeringed.research

import org.apache.commons.lang.builder.HashCodeBuilder

class Investigation implements Serializable, Comparable {
    Researcher researcher
    Project project
    Boolean isLead
    
    boolean equals(other) {
        if (!(other instanceof Investigation)) {
            return false
        }

        other.researcher?.id == researcher?.id &&
        other.project?.id == project?.id &&
        other.isLead == isLead        
    }

    int compareTo(obj) {
        (isLead) ? 1 : 
        researcher.lastName.compareToIgnoreCase(obj.researcher.lastName)
    }
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (researcher) builder.append(researcher.id)
        if (project) builder.append(project.id)
        if(isLead) builder.append(isLead)
        builder.toHashCode()
    }

    static Investigation get(long researcherId, long projectId) {
        find 'from Investigation where researcher.id=:researcherId and project.id=:projectId',
        [researcherId: researcherId, projectId: projectId]
    }

    static Investigation create(Researcher researcher, Project project, boolean flush = true) {
        new Investigation(researcher: researcher, project: project).save(flush: flush, insert: true)
    }
    
    static Investigation create(Researcher researcher, Project project, Boolean isLead, boolean flush = true) {
        new Investigation(researcher: researcher, project: project, isLead:isLead).save(flush: flush, insert: true)
    }

    static boolean remove(Researcher researcher, Project project, boolean flush = false) {
        Investigation instance = Investigation.findByResearcherAndProject(researcher, project)
        if (!instance) {
            return false
        }

        instance.delete(flush: flush)
        true
    }

    static void removeAll(Researcher researcher) {
        executeUpdate 'DELETE FROM Investigation WHERE researcher=:researcher', [researcher: researcher]
    }

    static void removeAll(Project project) {
        executeUpdate 'DELETE FROM Investigation WHERE project=:project', [project: project]
    }

    static mapping = {
        id composite: ['researcher', 'project']
        version false
    }
    
    @Override
    public String toString(){ (isLead) ? "Lead Investigator: ${researcher} ${project}" : "${researcher} ${project}"}
}
