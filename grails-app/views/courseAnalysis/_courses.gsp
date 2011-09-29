<section id="courses">
  <g:if test="${courses}">
    <table id="courseTable">
      <thead>
        <tr>
          <th>Institution</th>
          <th>Course Title</th>
        </tr>
      </thead>
      <tbody>
        <g:each in="${courses}" var="course" status="i">
        <tr>
          <td>
            <ul>
              <g:each in="${course.departments}" var="dept" status="j">
                <li>${dept.owningInstitution}</li>
              </g:each>
            </ul>
          </td>
          <td>
      <g:link action="edit" controller="course" id="${course.id}">
${course.title}
      </g:link>
      </td>
      </tr>
      </g:each>
      </tbody>
      <tfoot>
        <tr>
          <td><g:link action="create" controller="course">Add a new Course</g:link></td>
      </tr>
      </tfoot>
    </table>
  </g:if>
  <g:else>
    <span class="noneFound">There are no courses currently in the system</span>
  </g:else>
</section>