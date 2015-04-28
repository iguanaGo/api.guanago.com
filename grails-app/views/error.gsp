<!DOCTYPE html>
<html>
    <head>
        <title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
        <meta name="layout" content="main">
        <g:if env="development"><link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css"></g:if>
    </head>
    <body>
    <h2>Time: <g:formatDate format="yyyy-MM-dd hh:mm:ss" date="${new Date()}"/> </h2>
        <g:renderException exception="${exception}" />

    </body>
</html>