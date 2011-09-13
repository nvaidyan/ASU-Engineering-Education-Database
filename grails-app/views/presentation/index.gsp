<html>
  <head>
    <meta name="layout" content="html5boilerplate" />
    <title>
      <g:message code="presentations.title" default="Presentations" />
    </title>
  </head>
  <body>
    <section>
      <h1>
        <g:message code="presentations.seminars" 
                   args="['2010-2011']" 
                   default="{0} Seminar Series" />
      </h1>
    <g:each in="${presentationInstanceList}" var="presentation" status="i">
      <article>
        <h1>
          <g:fieldValue bean="${presentation}" field="title" />
        </h1>
        <details>
          <h2>
            <time 
              datetime="${g.formatDate(date:presentation.start, formatName:"seminar.dateformat" )}">
              <g:formatDate date="${presentation.start}" formatName="seminar.dateformat" />
            </time>
            <span>-</span>
            <time 
              datetime="${g.formatDate(date:presentation.end, formatName:"seminar.dateformat")}">
              <g:formatDate date="${presentation.end}" type="time" />
            </time>
            <span class="location">
              <g:fieldValue bean="${presentation}" field="location" />
            </span>

          </h2>
          <h3>
            <g:fieldValue bean="${presentation}" field="speaker" />
          </h3>
          <p class="description">
            <g:fieldValue bean="${presentation}" field="description" />
          </p>
        </details>
      </article>
    </g:each>
  </section>
  </body>
</html>
