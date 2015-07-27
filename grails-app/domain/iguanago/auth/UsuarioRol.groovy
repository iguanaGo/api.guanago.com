package iguanago.auth

import org.apache.commons.lang.builder.HashCodeBuilder

class UsuarioRol implements Serializable {

	private static final long serialVersionUID = 1

	Usuario usuario
	Rol rol

	boolean equals(other) {
		if (!(other instanceof UsuarioRol)) {
			return false
		}

		other.usuario?.id == usuario?.id &&
		other.rol?.id == rol?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (usuario) builder.append(usuario.id)
		if (rol) builder.append(rol.id)
		builder.toHashCode()
	}

	static UsuarioRol get(long usuarioId, long rolId) {
		UsuarioRol.where {
			usuario == Usuario.load(usuarioId) &&
			rol == Rol.load(rolId)
		}.get()
	}

	static boolean exists(long usuarioId, long rolId) {
		UsuarioRol.where {
			usuario == Usuario.load(usuarioId) &&
			rol == Rol.load(rolId)
		}.count() > 0
	}

	static UsuarioRol create(Usuario usuario, Rol rol, boolean flush = false) {
		def instance = new UsuarioRol(usuario: usuario, rol: rol)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(Usuario u, Rol r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UsuarioRol.where {
			usuario == Usuario.load(u.id) &&
			rol == Rol.load(r.id)
		}.deleteAll()

		if (flush) { UsuarioRol.withSession { it.flush() } }

		rowCount > 0
	}

	static void removeAll(Usuario u, boolean flush = false) {
		if (u == null) return

		UsuarioRol.where {
			usuario == Usuario.load(u.id)
		}.deleteAll()

		if (flush) { UsuarioRol.withSession { it.flush() } }
	}

	static void removeAll(Rol r, boolean flush = false) {
		if (r == null) return

		UsuarioRol.where {
			rol == Rol.load(r.id)
		}.deleteAll()

		if (flush) { UsuarioRol.withSession { it.flush() } }
	}

	static constraints = {
		rol validator: { Rol r, UsuarioRol ur ->
			if (ur.usuario == null) return
			boolean existing = false
			UsuarioRol.withNewSession {
				existing = UsuarioRol.exists(ur.usuario.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['rol', 'usuario']
		version false
	}
}
