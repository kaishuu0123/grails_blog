package grails_blog

class User {

	transient springSecurityService

	String username
	String password
	String email
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['isAdmin']

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		username index: true, indexAttributes: [unique:true, dropDups:true]
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def isAdmin() {
		def auths = getAuthorities()
		auths.each { role ->
			if ('ROLE_ADMIN'.equals(role.authority.trim())) {
				return true
			}
		}
		return false
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		//password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
		password = springSecurityService.encodePassword(password)
	}
}
