<nav id="topMenu">
  <ul>
    <li>
      <g:link controller="home" action="index">
        <g:message code="topnav.home" default="Home" />
      </g:link>
    </li>
    <li>
      <g:link controller="presentation" action="index">
        <g:message code="topnav.presentations" default="Presentations" />
      </g:link>
    </li>
    <li>
      <g:link controller="professor" action="program">
        <g:message code="topnav.faculty" default="Program Faculty" />
      </g:link>
    </li>
    <li>
      <g:link controller="student" action="program">
        <g:message code="topnav.students" default="Students" />
      </g:link>
    </li>
    <li>
      <g:link controller="course" action="program">
        <g:message code="topnav.courses" default="Courses" />
      </g:link>
    </li>
    <li>
      <g:link controller="project" action="list">
        <g:message code="topnav.projects" default="Research Projects" />
      </g:link>
    </li>
  </ul>
</nav>