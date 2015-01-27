import grails.util.Environment

grails.servlet.version = "3.0"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.server.port.http = 8090
if (Environment.current == Environment.PRODUCTION) {
    grails.server.port.http = 8092
}
if (Environment.current == Environment.TEST) {
    grails.server.port.http = 8091
}

grails.project.fork = [
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

// See gradle.build for dependencies

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false
    // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
//        mavenRepo "http://repo.desirableobjects.co.uk/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'mysql:mysql-connector-java:5.1.29'
        // runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'
        test "org.grails:grails-datastore-test-support:1.0.2-grails-2.4"
        compile (group:'org.apache.poi', name:'poi',version:'3.11')
        compile (group:'org.apache.poi', name:'poi-ooxml',version:'3.11')
        compile (group:'org.apache.poi', name:'ooxml-schemas',version:'1.1')
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.55"

        // plugins for the compile step
        compile ":scaffolding:2.1.2"
        compile ':cache:1.1.8'
        compile ":asset-pipeline:1.9.9"

        compile ':angular-template-asset-pipeline:1.4.1'
        compile ':angular-annotate-asset-pipeline:1.1.2'

        compile ':fixtures:1.3'

        // plugins needed at runtime but not for compilation
        runtime ":hibernate4:4.3.6.1" // or ":hibernate:3.6.10.18"

        test ':geb:0.9.2'
        compile ":excel-import:1.0.4"
//        compile ":bootstrap-file-upload:2.1.2"
//        compile ":ajax-uploader:1.1"
    }

}

/*
environments {
    development {
        grails.server.port.http = 8090
    }
    test {
        grails.server.port.http = 8091
    }
    production {
        grails.server.port.http = 8092
    }
}
*/
