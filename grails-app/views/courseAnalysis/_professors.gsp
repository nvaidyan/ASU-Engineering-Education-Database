<section id="professors">
  <g:if test="${professors}">
    <table id="professorTable">
      <thead>
      <th>Name</th>
      <th>Affiliations</th>
      <th>Year Started Teaching</th>
      <th>Tenured</th>
      <th>Research Interests</th>
      </thead>
      <tbody>
      <g:each in="${professors}" var="professor" status="i">
        <tr>
          <td>
        <g:link action="edit" controller="professor" id="${professor.id}">${professor.name}</g:link>
        </td>
        <td>
          <ul>
            <g:each in="${professor.affiliations}" var="affiliation" status="j">
              <li>${affiliation}</li>
            </g:each>
          </ul>
        </td>
        <td>
${professor.yearStartedTeaching}
        </td>
        <td>
${professor.tenured}
        </td>
        <td>
          <ul>
            <g:each in="${professor.interests}" var="interest" status="j">
              <li>${interest}</li>
            </g:each>
          </ul>
        </td>
        </tr>
      </g:each> 
      </tbody>
      <tfoot>
        <tr>
          <td><g:link action="create" controller="professor">Add a new Professor</g:link></td>
      </tr>
      </tfoot>
    </table>
  </g:if>
  <g:else>
    <span class="noneFound">There are no professors currently in the system</span>
  </g:else>
</section>