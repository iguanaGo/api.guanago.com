<%@ page import="iguana.Escala" %>



<div class="fieldcontain ${hasErrors(bean: escalaInstance, field: 'avion', 'error')} required">
	<label for="avion">
		<g:message code="escala.avion.label" default="Avion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="avion" required="" value="${escalaInstance?.avion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: escalaInstance, field: 'destino', 'error')} required">
	<label for="destino">
		<g:message code="escala.destino.label" default="Destino" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="destino" required="" value="${escalaInstance?.destino}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: escalaInstance, field: 'duracion', 'error')} required">
	<label for="duracion">
		<g:message code="escala.duracion.label" default="Duracion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="duracion" required="" value="${escalaInstance?.duracion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: escalaInstance, field: 'fechaLlegada', 'error')} required">
	<label for="fechaLlegada">
		<g:message code="escala.fechaLlegada.label" default="Fecha Llegada" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fechaLlegada" required="" value="${escalaInstance?.fechaLlegada}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: escalaInstance, field: 'fechaSalida', 'error')} required">
	<label for="fechaSalida">
		<g:message code="escala.fechaSalida.label" default="Fecha Salida" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fechaSalida" required="" value="${escalaInstance?.fechaSalida}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: escalaInstance, field: 'origen', 'error')} required">
	<label for="origen">
		<g:message code="escala.origen.label" default="Origen" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="origen" required="" value="${escalaInstance?.origen}"/>

</div>

