
<%@ page import="iguana.Escala" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'escala.label', default: 'Escala')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-escala" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-escala" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="avion" title="${message(code: 'escala.avion.label', default: 'Avion')}" />
					
						<g:sortableColumn property="destino" title="${message(code: 'escala.destino.label', default: 'Destino')}" />
					
						<g:sortableColumn property="duracion" title="${message(code: 'escala.duracion.label', default: 'Duracion')}" />
					
						<g:sortableColumn property="fechaLlegada" title="${message(code: 'escala.fechaLlegada.label', default: 'Fecha Llegada')}" />
					
						<g:sortableColumn property="fechaSalida" title="${message(code: 'escala.fechaSalida.label', default: 'Fecha Salida')}" />
					
						<g:sortableColumn property="origen" title="${message(code: 'escala.origen.label', default: 'Origen')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${escalaInstanceList}" status="i" var="escalaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${escalaInstance.id}">${fieldValue(bean: escalaInstance, field: "avion")}</g:link></td>
					
						<td>${fieldValue(bean: escalaInstance, field: "destino")}</td>
					
						<td>${fieldValue(bean: escalaInstance, field: "duracion")}</td>
					
						<td>${fieldValue(bean: escalaInstance, field: "fechaLlegada")}</td>
					
						<td>${fieldValue(bean: escalaInstance, field: "fechaSalida")}</td>
					
						<td>${fieldValue(bean: escalaInstance, field: "origen")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${escalaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
