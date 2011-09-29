<html>
  <head>
    <meta name="layout" content="html5boilerplate" />
    <title>
      <g:message code="courseAnalysis.dashboard" 
                 default="Welcome to the Course Analysis system" />
    </title>
  </head>
  <body>
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
            <g:render template="courses" collection="${courses}" />
          </tbody>
          <tfoot>
            <g:link action="create" controller="course">Add a new Course</g:link>
          </tfoot>
        </table>
      </g:if>
      <g:else>
        <span class="noneFound">There are no courses currently in the system</span>
      </g:else>
    </section>
    <section id="professors">
      <g:if test="${professors}">
        <ul id="professorList">
          <g:render template="professors" collection="${professors}" />
        </ul>
      </g:if>
      <g:else>
        <span class="noneFound">There are no professors currently in the system</span>
      </g:else>
    </section>
    <section id="textbooks">
      <g:if test="${textbooks}">
        <ul id="textbookList">
          <g:render template="textbooks" collection="${textbooks}" />
        </ul>
      </g:if>
      <g:else>
        <span class="noneFound">There are no textbooks currently in the system</span>
      </g:else>
    </section>
  </body>
</html>
