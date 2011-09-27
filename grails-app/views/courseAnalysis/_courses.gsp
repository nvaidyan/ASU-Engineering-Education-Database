<tr>
  <td><g:fieldValue bean="${it.departments}" field="unit" /></td>
  <td>
    <g:link action="edit" controller="course">
      <g:fieldValue bean="${it}" field="title" />
    </g:link>
  </td>
  <td><g:fieldValue bean="${it}" field="description" /> ${it.description}</td>
</tr>