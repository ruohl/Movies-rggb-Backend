package moviesrggb.movie
import moviesrggb.genre.Genre
import java.sql.Time

class Movie {
    String title
    String description
    double rating
    Time duration
    static hasMany = [genres: Genre]
    Date releasedDate
    String trailerLink

    static constraints = {
        title nullable: false
        description nullable: false
        rating nullable: false
        duration nullable: false
        genres nullable: false
        releasedDate nullable: false
        trailerLink nullable: false
    }
}