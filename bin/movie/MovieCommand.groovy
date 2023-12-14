package moviesrggb.movie
import java.sql.Time
import grails.validation.Validateable

class MovieCommand implements Validateable {
    Long id
    Long version

    String title
    String description
    double rating
    Time duration
    Date releasedDate
    String trailerLink
    List<String> genres

    static constraints = {
        id nullable: true
        version nullable: true
        title nullable: false
        description nullable: false
        rating nullable: false
        duration nullable: false
        releasedDate nullable: false
        trailerLink nullable: false
        genres nullable: false
    }
}