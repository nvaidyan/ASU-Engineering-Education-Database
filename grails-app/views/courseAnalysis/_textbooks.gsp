<g:set var="textbook" value="${it}" />
<li>
<g:link action="edit" controller="textbook" id="${textbook.id}">
  ${textbook.title}
</g:link>
</li>