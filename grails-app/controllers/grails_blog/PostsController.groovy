package grails_blog

class PostsController {

	def springSecurityService

    def index() {
		forward(action: "list")
	}

	def list(Integer max, Integer offset) {
		params.max = Math.min(max ?: 2, 10)
		params.offset = offset ?: 0

		def postList = Post.collection.find([active: true], [comments: 0]).limit(params.max).skip(params.offset).sort(dateCreated: -1)
		render(view: "/blog/posts/index", model: [posts: postList, postTotal: Post.count()])
	}

	def show(String slug) {
		Post post
		User user = springSecurityService.getCurrentUser()
		if (user && user.isAdmin()) {
			post = Post.collection.findOne(slug: slug) as Post
		} else {
			post = Post.collection.findOne(slug: slug, active: true) as Post
		}

		if (!post) {
			response.sendError(404)
		}

		render(view: "/blog/posts/post", model: [post: post])
	}

	def addComment(String postSlug) {
		Post post = Post.findBySlug(postSlug)

		if (post) {
			params.remove("id")
			Comment comment = new Comment(params)
			comment.dateCreated = new Date()
			post.commentsCount = (post.commentsCount ?: 0) + 1
			post.comments.add(comment)
			post.save(failOnError: true)
		}

		redirect(action: "show", params: [slug: postSlug])
	}

	def tags(String tag) {
		def posts = Post.collection.find(tags: tag, active: true)
		render(view: "/blog/posts/index", model: [posts: posts])
	}
}
