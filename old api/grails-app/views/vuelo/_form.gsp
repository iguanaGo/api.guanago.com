<%@ page import="iguana.Vuelo" %>



<div class="fieldcontain ${hasErrors(bean: vueloInstance, field: 'destino', 'error')} required">
	<label for="destino">
		<g:message code="vuelo.destino.label" default="Destino" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="destino" required="" value="${vueloInstance?.destino}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vueloInstance, field: 'origen', 'error')} required">
	<label for="origen">
		<g:message code="vuelo.origen.label" default="Origen" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="origen" required="" value="${vueloInstance?.origen}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vueloInstance, field: 'precioTotal', 'error')} required">
	<label for="precioTotal">
		<g:message code="vuelo.precioTotal.label" default="Precio Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="precioTotal" required="" value="${vueloInstance?.precioTotal}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vueloInstance, field: 'segmentos', 'error')} ">
	<label for="segmentos">
		<g:message code="vuelo.segmentos.label" default="Segmentos" />
		
	</label>
	<g:select name="segmentos" from="${iguana.Segmento.list()}" multiple="multiple" optionKey="id" size="5" value="${vueloInstance?.segmentos*.id}" class="many-to-many"/>

</div>

