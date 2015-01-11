package grails_blog.admin

import grails_blog.Post


class AdminPostController {
	def SpringSecurityService

    def index() {
		forward(action: "list")
	}

	def list(Integer max, Integer offset) {
		params.max = Math.min(max ?: 10, 10)
		params.offset = offset ?: 0

		def postList = Post.collection.find([:], [comments: 0]).limit(params.max).skip(params.offset).sort(dataCreated: -1)

		render(view: "/admin/post/index", model: [postList: postList, postTotal: Post.count()])
	}

	def create() {
		render(view: "/admin/post/create", model: [post: new Post(params)])
	}

	def save() {
		def postInstance
		String successMsg

		if (params.id) {
			postInstance = Post.get(params.id)
			if (!postInstance) {
				flash.message = message(code: "default.not.found.message", args: [message(code: 'post.label', default: 'Post'), id])
				redirect(action: "list")
				return
			}
			successMsg = message(code: 'default.updated.message', args: [message(code: 'post.title', default: 'Post'), params.id])
		} else {
			postInstance = new Post()
			if (springSecurityService.isLoggedIn()) {
			    postInstance.author = springSecurityService.getCurrentUser().username
			} else {
				postInstance.author = "guest"
			}
		}

		postInstance.properties = params
		println(postInstance)
		postInstance.tagList = params.tagList
		if (!postInstance.save()) {
			render(view: "/admin/post/create", model: [post: postInstance])
			return
		}

		flash.message = successMsg
		redirect(action: "list")
	}

	def edit(String id) {
		def postInstance = Post.get(id)
		if (!postInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.label', default: 'Post'), id])
			redirect(action: "list")
			return
		}

		render(view: "/admin/post/create", model: [post: postInstance])
	}

	def delete(String id) {
		def postInstance = Post.get(id)
		if (!postInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.label', default: 'Post'), id])
			redirect(action: "list")
			return
		}

		postInstance.delete()
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'post.label', default: 'Post'), id])
		redirect(action: "list")
	}
}
