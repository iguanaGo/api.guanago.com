
<%@ page import="iguana.Segmento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'segmento.label', default: 'Segmento')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-segmento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-segmento" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list segmento">
			
				<g:if test="${segmentoInstance?.aerolinea}">
				<li class="fieldcontain">
					<span id="aerolinea-label" class="property-label"><g:message code="segmento.aerolinea.label" default="Aerolinea" /></span>
					
						<span class="property-value" aria-labelledby="aerolinea-label"><g:fieldValue bean="${segmentoInstance}" field="aerolinea"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${segmentoInstance?.duracion}">
				<li class="fieldcontain">
					<span id="duracion-label" class="property-label"><g:message code="segmento.duracion.label" default="Duracion" /></span>
					
						<span class="property-value" aria-labelledby="duracion-label"><g:fieldValue bean="${segmentoInstance}" field="duracion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${segmentoInstance?.escalas}">
				<li class="fieldcontain">
					<span id="escalas-label" class="property-label"><g:message code="segmento.escalas.label" default="Escalas" /></span>
					
						<g:each in="${segmentoInstance.escalas}" var="e">
						<span class="property-value" aria-labelledby="escalas-label"><g:link controller="escala" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${segmentoInstance?.numeroDeVuelo}">
				<li class="fieldcontain">
					<span id="numeroDeVuelo-label" class="property-label"><g:message code="segmento.numeroDeVuelo.label" default="Numero De Vuelo" /></span>
					
						<span class="property-value" aria-labelledby="numeroDeVuelo-label"><g:fieldValue bean="${segmentoInstance}" field="numeroDeVuelo"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:segmentoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${segmentoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
