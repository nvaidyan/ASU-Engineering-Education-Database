modules = {
    core {
        resource url: 'js/libs/modernizr-2.0.6.min.js', disposition: 'head'
        resource url: 'js/plugins.js'
        resource url: 'js/script.js'
        resource url: 'js/analytics.js'
        resource url: 'css/style.css'
        resource url: 'https://www.asu.edu/asuthemes/3.0/css/header_compressed.css', attrs:[media:'all']
        resource url: "http://asu.edu/asuthemes/2.0/css/asu_footer.css", attrs:[media:"all"]
    }
    facultyList {
        dependsOn 'core'
        resource url: 'css/faculty.css'
        resource url: 'js/faculty.js'
    }
}

