<section id="textbooks">
  <g:if test="${textbooks}">
    <table id="textbookList">
      <thead>
        <tr>
          <th>ISBN</th>
          <th>Name</th>
          <th>Edition</th>
        </tr>
      </thead>
      <tbody>
        <g:each in="${textbooks}" var="textbook" status="i">
        <tr>
          <td>
            ${textbook.isbn}
          </td>
          <td>
            <g:link action="edit" controller="textbook" id="${textbook.id}">
${textbook.title}
        </g:link>
          </td>
          <td>
            ${textbook.edition}
          </td>
        </tr>
      </g:each>
      </tbody>
      <tfoot>
        <tr>
          <td><g:link action="create" controller="textbook">Add a new Textbook</g:link></td>
        </tr>
      </tfoot>
    </table>
  </g:if>
  <g:else>
    <span class="noneFound">There are no textbooks currently in the system</span>
  </g:else>
</section>