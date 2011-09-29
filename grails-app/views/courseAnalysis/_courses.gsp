<g:set var="course" value="${it}" />
<tr>
  <td>
    <ul>
      <g:each in="${course.departments}" var="dept" status="i">
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