<html>
  <head>
    <meta name="layout" content="html5boilerplate" />
    <title>
      <g:message code="domainArea.title" default="Select a Domain Area" />
    </title>
  </head>
  <body>
    <g:form name="domains" method="POST" action="selectedDomain">
      <legend>
        <g:message code="domainAreas.current" 
                   default="Current Domain Areas" />
      </legend>
      <g:select name="domain" from="${domainAreaInstanceList}" />
      <g:submitButton name="select" value="View this domain" />
    </g:form>
  </body>
</html>
