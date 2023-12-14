package moviesrggb.genre

import grails.validation.Validateable

class GenreCommand implements Validateable {
    Long id
    Long version

    String name
    String description

    static constraints = {
        id nullable: true
        version nullable: true
        name nullable: false
        description nullable: false
    }
}