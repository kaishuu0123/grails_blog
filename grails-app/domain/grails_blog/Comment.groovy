package grails_blog

class Comment implements Serializable {

	String name
	String email
	String website
	String content
	String dateCreated

    static constraints = {
    }
}
