package grails_blog.admin

import grails_blog.User

class AdminUserController {

	def springSecurityService
	def passwordEncoder

    def index() {
		forward(action: "list")
	}

	def list(Integer max, Integer offset) {
		params.max = Math.min(max ?: 10, 100)

		render(view: "admin/user/index", model: [userList: User.list(params), userTotal: User.count()])
	}

	def create() {
		render(view: "/admin/user/create", model: [user: new User(params)])
	}

	def edit(String id) {
		def userInstance = User.get(id)
		if (!userInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
			redirect(action: "list")
			return
		}

		render(view: "/admin/user/create", model: [user: userInstance])
	}

	def password() {
		User currentUser = springSecurityService.getCurrentUser()
		if (currentUser) {
			render(view: "/admin/user/password", model: [user: currentUser])
		} else {
			redirect(controller: 'login')
		}
	}

	def updatePassword(String currentPassword, String newPassword, String confirmPassword) {
		User currentUser = User.get(springSecurityService.getCurrentUser().id)
		if (passwordEncoder.isPasswordValid(currentUser.password, currentPassword, null)) {
			if (newPassword.equals(confirmPassword)) {
				currentUser.password = newPassword
				currentUser.save()
				flash.message = message(code: 'user.password.changed.success', default: 'Password updated successfully')
				redirect(action: 'password')
			} else {
				flash.error = message(code: 'user.password.mismatch.error', default: 'New and Confirm password do not match')
				redirect(action: 'password')
			}
		} else {
			flash.error = message(code: 'user.password.currentPassword.error', default: 'Current password is incorrect')
			redirect(action: 'password')
		}
	}
}
