
<%@ page import="iguana.Vuelo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vuelo.label', default: 'Vuelo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-vuelo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-vuelo" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list vuelo">
			
				<g:if test="${vueloInstance?.destino}">
				<li class="fieldcontain">
					<span id="destino-label" class="property-label"><g:message code="vuelo.destino.label" default="Destino" /></span>
					
						<span class="property-value" aria-labelledby="destino-label"><g:fieldValue bean="${vueloInstance}" field="destino"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vueloInstance?.origen}">
				<li class="fieldcontain">
					<span id="origen-label" class="property-label"><g:message code="vuelo.origen.label" default="Origen" /></span>
					
						<span class="property-value" aria-labelledby="origen-label"><g:fieldValue bean="${vueloInstance}" field="origen"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vueloInstance?.precioTotal}">
				<li class="fieldcontain">
					<span id="precioTotal-label" class="property-label"><g:message code="vuelo.precioTotal.label" default="Precio Total" /></span>
					
						<span class="property-value" aria-labelledby="precioTotal-label"><g:fieldValue bean="${vueloInstance}" field="precioTotal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${vueloInstance?.segmentos}">
				<li class="fieldcontain">
					<span id="segmentos-label" class="property-label"><g:message code="vuelo.segmentos.label" default="Segmentos" /></span>
					
						<g:each in="${vueloInstance.segmentos}" var="s">
						<span class="property-value" aria-labelledby="segmentos-label"><g:link controller="segmento" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:vueloInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${vueloInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
