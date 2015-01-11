package grails_blog

class PostTagLib {
	static namespace = "post"
    static defaultEncodeAs = [taglib:'html']

	def count = { attrs ->
		out << Post.countByActive(attrs.active)
	}
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
}
