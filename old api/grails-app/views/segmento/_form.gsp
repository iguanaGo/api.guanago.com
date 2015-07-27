<%@ page import="iguana.Segmento" %>



<div class="fieldcontain ${hasErrors(bean: segmentoInstance, field: 'aerolinea', 'error')} required">
	<label for="aerolinea">
		<g:message code="segmento.aerolinea.label" default="Aerolinea" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="aerolinea" required="" value="${segmentoInstance?.aerolinea}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: segmentoInstance, field: 'duracion', 'error')} required">
	<label for="duracion">
		<g:message code="segmento.duracion.label" default="Duracion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="duracion" required="" value="${segmentoInstance?.duracion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: segmentoInstance, field: 'escalas', 'error')} ">
	<label for="escalas">
		<g:message code="segmento.escalas.label" default="Escalas" />
		
	</label>
	<g:select name="escalas" from="${iguana.Escala.list()}" multiple="multiple" optionKey="id" size="5" value="${segmentoInstance?.escalas*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: segmentoInstance, field: 'numeroDeVuelo', 'error')} required">
	<label for="numeroDeVuelo">
		<g:message code="segmento.numeroDeVuelo.label" default="Numero De Vuelo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numeroDeVuelo" required="" value="${segmentoInstance?.numeroDeVuelo}"/>

</div>

