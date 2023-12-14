package moviesrggb.genre
import moviesrggb.movie.Movie

class Genre {
    String name
    String description

    static hasMany = [movies: Movie]
    static belongsTo = [Movie]
    static constraints = {
        name nullable: false
        description nullable: false
    }
}