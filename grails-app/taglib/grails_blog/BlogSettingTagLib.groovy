package grails_blog

class BlogSettingTagLib {
	static namespace = "blog"
    static defaultEncodeAs = [taglib:'text']

	def settings = { attrs ->
		BlogSetting settings = BlogSetting.get(BlogSetting.SETTINGS_ID)
		out << settings."${attrs.name}"
	}
}
