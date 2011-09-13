<html>
  <head>
    <meta name="layout" content="html5boilerplate" />
    <title>
      <g:message code="program.faculty" default="Program Faculty" />
    </title>
    <r:require modules="facultyList" />
  </head>
  <body>
    <section id="facultySlides">
      <g:each in="${facultyInstanceList}" var="facultyInstance" status="i">
        <article class="faculty">
          <a href="${facultyInstance.homepageUrl}"
             title="${facultyInstance}'s homepage"
             target="_blank">
            <img src="${facultyInstance.headshotUrl}"
                 alt="${facultyInstance}'s headshot"
                 width="100"
                 height="100" />
            <h3>${facultyInstance}</h3>
          </a>
          <p class="position">${facultyInstance.position}</p>
          <h4>
            <g:message code="faculty.affiliations" default="Affiliations" />
          </h4>
          <ul class="affiliations">
          <g:each in="${facultyInstance.affiliations}" var="affiliation" status="j">
            <li>${affiliation.unit}</li>
          </g:each>
          </ul>
          <h4>
            <g:message code="faculty.degrees" default="Degrees" />
          </h4>
          <ul class="degrees">
          <g:each in="${facultyInstance.degrees}" var="degree" status="j">
            <li>${degree}</li>
          </g:each>
          </ul>
          <h4>
            <g:message code="faculty.interests" default="Research Interests" />
          </h4>
          <p class="interests">${facultyInstance.researchInterests}</p>
        </article>
      </g:each>
    </section>
  </body>
</html>
