<html>
  <head>
    <meta name="layout" content="html5boilerplate" />
    <title>
      <g:message code="projects.title" default="Projects" />
    </title>
    <r:require modules="projectList" />
  </head>
  <body>
    <section>
      <h1>
        <g:message code="projects.current"
                   default="Current Projects" />
      </h1>
    <g:each in="${projectInstanceList}" var="project" status="i">
      <article>
        <h1>
          <g:if test="${project.name.equals('Course Analysis')}">
            <g:link controller="courseAnalysis">
              <g:fieldValue bean="${project}" field="name" />
            </g:link>
          </g:if>
          <g:else>
            <g:fieldValue bean="${project}" field="name" />
          </g:else>
        </h1>
        <details>
          <label for="collaborators">
            <g:message code="project.collaborators" default="Collaborators" />
          </label>
          <ul id="collaborators">
          <g:each in="${project.collaborators}" var="collaborator" status="j">
            <li>
            <g:if test="${collaborator.isLead}">
              <span class="lead"><g:message code="investigation.isLead" default="Lead"/></span>
            </g:if>
              <g:fieldValue bean="${collaborator}" field="researcher"/>
            </li>
          </g:each>
          </ul>
          <label for="status">
            <g:message code="project.status" default="Status" />
          </label>
          <p id="status">
            <g:fieldValue bean="${project}" field="status" />
          </p>
          <label for="description">
            <g:message code="project.description" default="Description" />
          </label>
          <p id="description">
            <g:fieldValue bean="${project}" field="description" />
          </p>
          <label for="start">
            <g:message code="project.start" default="Start Date" />
          </label>
          <p id="start">
            <g:formatDate date="${project.start}" type="date" />
          </p>
          <label for="end">
            <g:message code="project.end" default="End Date" />
          </label>
          <p id="end">
            <g:formatDate date="${project.end}" type="date" />
          </p>
        </details>
      </article>
    </g:each>
  </section>
  </body>
</html>
