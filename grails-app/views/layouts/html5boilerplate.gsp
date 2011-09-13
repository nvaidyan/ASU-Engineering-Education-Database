<!doctype html>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="description"
          content="${message(code:'application.description')}">
    <meta name="author" content="Nicholas Vaidyanathan, PhD Computer Science Lead Visionary of Visionary Software Solutions">

    <meta name="viewport" content="width=device-width,initial-scale=1">

    <r:require modules="core" />
    <r:layoutResources />
  <g:layoutHead/>
</head>
<body>

  <div id="container">
    <header role="banner">
      <script type="text/javascript">
      // <![CDATA[
      // Declare the ASUHeader namespace, if it doesn't already exist.
      if (!ASUHeader) {
              var ASUHeader = {};
      }
      // ]]>
      </script>
      <g:render template="/layouts/fultonHead" />
      <img id="logo" 
           src="${resource(dir:'images', file:'engineeringEducationPhD.png')}"
           alt="Engineering Education PhD" />
      <g:render template="/layouts/menus/top" />
    </header>
    <div id="main" role="main">
      <g:layoutBody/>
    </div>
    <footer role="contentinfo">
      <g:render template="/layouts/fultonFoot" />
    </footer>
  </div> <!--! end of #container -->
  
  <!--[if lt IE 7 ]>
          <script src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.2/CFInstall.min.js"></script>
          <script>window.attachEvent("onload",function(){CFInstall.check({mode:"overlay"})})</script>
  <![endif]-->
  <div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
  </div>
<r:script library="application"/>
</body>
</html>
