package grails_blog

class UserTagLib {

	def springSecurityService

	static namespace = "user"
    static defaultEncodeAs = [taglib:'html']

	def current_user = { attrs ->
		out << springSecurityService.getCurrentUser().username
	}
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
}
