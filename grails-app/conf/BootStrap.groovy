import grails_blog.User
import grails_blog.Role
import grails_blog.UserRole
import grails_blog.BlogSetting

class BootStrap {
    def init = { servletContext ->
		def testUser = User.findByUsername("admin") ?:
			new User(
				username: "admin",
				password: "hogehoge",
				email: "testuser@example.com"
			).save(flush: true)

		if (!Role.findByAuthority('ROLE_USER')) {
			def userRole = new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)
		}

		def adminRole = Role.findByAuthority('ROLE_ADMIN')
		if (!adminRole) {
			adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true, failOnError: true)
		}

		if (!testUser.isAdmin()) {
			UserRole.create(testUser, adminRole, true)
		}

		BlogSetting setting = BlogSetting.get(BlogSetting.SETTINGS_ID) ?:
			new BlogSetting(blogTitle: "Grails MongoDB Blog",
				postsPerPage: 5,
				footerContent: 'Blog powered by <a href="http://grails.org/">Grails</a> &amp; <a href="http://mongodb.org">MongoDB</a>').save()
    }
    def destroy = {
    }
}
