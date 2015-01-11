modules = {
	application { resource url:'js/application.js' }

	'bootstrap_blog' {
		dependsOn 'jquery'
		resource url: [dir: 'blog/js',                 file: 'bootstrap.min.js']
		resource url: [dir: 'blog/css',                file: 'bootstrap.css']
		resource url: [dir: 'blog/css',                file: 'blog.css']
	}
	'bootstrap_admin' {
		dependsOn 'jquery'
		resource url: [dir: 'blog/js',                 file: 'jquery-ui-1.9.2.custom.min.js']
		resource url: [dir: 'blog/js',                 file: 'bootstrap.min.js']
		resource url: [dir: 'blog/js',                 file: 'tag-it.js']
		resource url: [dir: 'blog/js',                 file: 'bootbox.js']
		resource url: [dir: 'blog/js',                 file: 'admin.js']
		resource url: [dir: 'blog/css',                file: 'bootstrap.css']
		resource url: [dir: 'blog/css',                file: 'jquery.tagit.css']
		resource url: [dir: 'blog/css',                file: 'jquery-ui-1.10.0.custom.css']
		resource url: [dir: 'blog/css',                file: 'admin.css']
	}

	'login' {
		dependsOn 'jquery'
		resource url: [dir: 'blog/js',                 file: 'bootstrap.min.js']
		resource url: [dir: 'blog/css',                file: 'bootstrap.css']
		resource url: [dir: 'blog/css',                file: 'login.css']
	}
}