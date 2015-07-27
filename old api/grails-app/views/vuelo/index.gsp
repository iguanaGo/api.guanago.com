
<%@ page import="iguana.Vuelo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'vuelo.label', default: 'Vuelo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-vuelo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		
		<g:if test="${flash.message}">
			<div class="message" role="status">
				${flash.message}
			</div>
		</g:if>

	<div id="list-vuelo" class="content scaffold-list" role="main">		
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="destino" title="${message(code: 'vuelo.destino.label', default: 'Destino')}" />
					
						<g:sortableColumn property="origen" title="${message(code: 'vuelo.origen.label', default: 'Origen')}" />
					
						<g:sortableColumn property="precioTotal" title="${message(code: 'vuelo.precioTotal.label', default: 'Precio Total')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${vueloInstanceList}" status="i" var="vueloInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${vueloInstance.id}">${fieldValue(bean: vueloInstance, field: "destino")}</g:link></td>
					
						<td>${fieldValue(bean: vueloInstance, field: "origen")}</td>
					
						<td>${fieldValue(bean: vueloInstance, field: "precioTotal")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${vueloInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
