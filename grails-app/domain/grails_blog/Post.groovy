package grails_blog

class Post {

	String id
	String title
	String slug
	String content
	List<String> tags
	Date dateCreated
	Date lastUpdated
	String author
	Integer commentsCount = 0
	Boolean active = false
	List<Comment> comments

	static embedded = ['comments']

	String tagList

	static transients = ['tagList']

    static constraints = {
		commentsCount nullable: true
		slug nullable: true
		content nullable: true
    }

	static mapping = {
		slug index: true, indexAttributes: [unique: true, dropDups: true]
	}

	def afterLoad() {
		this.tagList = ""
		if (this.tags) {
			this.tags.each { tag ->
				this.tagList = this.tagList + tag + ","
			}
			this.tagList = this.tagList.substring(0, this.tagList.size() - 1).trim()
		}
	}

	def beforeInsert() {
		if (this.title && !this.slug) {
			this.slug = createSlug(title)
		}
		this.tags?.clear()
		if (tagList) {
			this.tags = convertToTags(tagList)
		}
	}

	def beforeUpdate() {
		if (this.title && !this.slug) {
			this.slug = createSlug(title)
		}
		this.tags?.clear()

		if (tagList) {
			this.tags = convertToTags(tagList)
		}
	}

	private String createSlug(String str) {
		def slug = str.replaceAll("\\s","-") // whitespace becomes -
		slug = slug.replaceAll("\\W","") // remove alphanumeric chars
		slug = slug.toLowerCase()
		println("HELLOOOOOOOOOOOOOOOOOOOOOOOO")
		println(slug)
		return slug
	}

	private List<String> convertToTags(String str) {
		def tags = str.split(",").collect{ it.trim() }.toList()
	}
}
