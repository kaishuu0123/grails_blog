// environment specific settings
environments {
    development {
        mongo {
            host = "localhost"
            port = 27017
            databaseName = "grails_blog"
        }
    }
    test {
        mongo {
            host = "localhost"
            port = 27017
            databaseName = "grails_blog"
        }
    }
    production {
        mongo {
            host = "localhost"
            port = 27017
            databaseName = "grails_blog"
        }
    }
}
