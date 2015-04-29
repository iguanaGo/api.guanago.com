
<%@ page import="iguana.Escala" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'escala.label', default: 'Escala')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-escala" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-escala" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list escala">
			
				<g:if test="${escalaInstance?.avion}">
				<li class="fieldcontain">
					<span id="avion-label" class="property-label"><g:message code="escala.avion.label" default="Avion" /></span>
					
						<span class="property-value" aria-labelledby="avion-label"><g:fieldValue bean="${escalaInstance}" field="avion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${escalaInstance?.destino}">
				<li class="fieldcontain">
					<span id="destino-label" class="property-label"><g:message code="escala.destino.label" default="Destino" /></span>
					
						<span class="property-value" aria-labelledby="destino-label"><g:fieldValue bean="${escalaInstance}" field="destino"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${escalaInstance?.duracion}">
				<li class="fieldcontain">
					<span id="duracion-label" class="property-label"><g:message code="escala.duracion.label" default="Duracion" /></span>
					
						<span class="property-value" aria-labelledby="duracion-label"><g:fieldValue bean="${escalaInstance}" field="duracion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${escalaInstance?.fechaLlegada}">
				<li class="fieldcontain">
					<span id="fechaLlegada-label" class="property-label"><g:message code="escala.fechaLlegada.label" default="Fecha Llegada" /></span>
					
						<span class="property-value" aria-labelledby="fechaLlegada-label"><g:fieldValue bean="${escalaInstance}" field="fechaLlegada"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${escalaInstance?.fechaSalida}">
				<li class="fieldcontain">
					<span id="fechaSalida-label" class="property-label"><g:message code="escala.fechaSalida.label" default="Fecha Salida" /></span>
					
						<span class="property-value" aria-labelledby="fechaSalida-label"><g:fieldValue bean="${escalaInstance}" field="fechaSalida"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${escalaInstance?.origen}">
				<li class="fieldcontain">
					<span id="origen-label" class="property-label"><g:message code="escala.origen.label" default="Origen" /></span>
					
						<span class="property-value" aria-labelledby="origen-label"><g:fieldValue bean="${escalaInstance}" field="origen"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:escalaInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${escalaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
