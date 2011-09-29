<html>
  <head>
    <meta name="layout" content="html5boilerplate" />
    <title>
      <g:message code="courseAnalysis.dashboard" 
                 default="Welcome to the Course Analysis system" />
    </title>
  </head>
  <body>
    <g:render template="courses" bean="${courses}" />
    <g:render template="professors" bean="${professors}" />      
    <g:render template="textbooks" bean="${textbooks}" />
  </body>
</html>
