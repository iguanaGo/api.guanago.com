
<%@ page import="iguana.Segmento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'segmento.label', default: 'Segmento')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-segmento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-segmento" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="aerolinea" title="${message(code: 'segmento.aerolinea.label', default: 'Aerolinea')}" />
					
						<g:sortableColumn property="duracion" title="${message(code: 'segmento.duracion.label', default: 'Duracion')}" />
					
						<g:sortableColumn property="numeroDeVuelo" title="${message(code: 'segmento.numeroDeVuelo.label', default: 'Numero De Vuelo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${segmentoInstanceList}" status="i" var="segmentoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${segmentoInstance.id}">${fieldValue(bean: segmentoInstance, field: "aerolinea")}</g:link></td>
					
						<td>${fieldValue(bean: segmentoInstance, field: "duracion")}</td>
					
						<td>${fieldValue(bean: segmentoInstance, field: "numeroDeVuelo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${segmentoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
