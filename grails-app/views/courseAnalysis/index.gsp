<html>
  <head>
    <meta name="layout" content="html5boilerplate" />
    <title>
      <g:message code="domainArea.title" default="Select a Domain Area" />
    </title>
  </head>
  <body>
    <section>
      <h1>
        <g:message code="domainAreas.current" 
                   default="Current Domain Areas" />
      </h1>
      <ul>
        <g:each in="${domainAreaInstanceList}" var="domainArea" status="i">
          <li class="button">
            <g:fieldValue bean="${domainArea}" field="name" />
          </li>
        </g:each>
      </ul>
    </section>
  </body>
</html>
