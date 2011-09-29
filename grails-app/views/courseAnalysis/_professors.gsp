<g:set var="professor" value="${it}" />
  <li>
    <g:link action="edit" controller="professor" id="${professor.id}">
      ${professor.name}
    </g:link>
  </li>
