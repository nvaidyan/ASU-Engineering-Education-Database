<html>
  <head>
    <meta name="layout" content="html5boilerplate" />
    <title>
      <g:message code="courses.title" default="Courses" />
    </title>
  </head>
  <body>
    <section>
      <h1>
        <g:message code="courses.current" 
                   default="Current courses" />
      </h1>
    <g:each in="${courseInstanceList}" var="unitCourse" status="i">
      <article>
        <h1>
          <g:fieldValue bean="${unitCourse.course}" field="title" />
        </h1>
        <details>
          <p class="description">
            <g:fieldValue bean="${unitCourse.course}" field="description" />
          </p>
          <g:link url="${unitCourse.course.url}"
                  target="_blank">
            <g:message code="course.url.title" default="Learn More..." />
          </g:link>
        </details>
      </article>
    </g:each>
  </section>
  </body>
</html>
