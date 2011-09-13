<html>
  <head>
    <meta name="layout" content="html5boilerplate" />
    <title>
      <g:message code="students.title" default="Students" />
    </title>
  </head>
  <body>
    <section>
      <h1>
        <g:message code="students.current" 
                   default="Current Students" />
      </h1>
    <g:each in="${studentInstanceList}" var="student" status="i">
      <article>
        <h1>
          <g:fieldValue bean="${student}" field="name" />
        </h1>
        <details>
          <h2>
            <g:fieldValue bean="${student}" field="yearInSchool" />
          </h2>
          <h3>
            <g:fieldValue bean="${student}" field="gpa" />
          </h3>
          <p class="bio">
            <g:fieldValue bean="${student}" field="bio" />
          </p>
        </details>
      </article>
    </g:each>
  </section>
  </body>
</html>
