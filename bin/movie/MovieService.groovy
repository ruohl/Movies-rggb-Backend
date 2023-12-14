package moviesrggb.movie
import moviesrggb.movie.Movie
import java.util.LinkedHashMap
import grails.transaction.Transactional
import org.hibernate.transform.Transformers

@Transactional
class MovieService {
    def sessionFactory

    public List<Movie> listMovies(){
        def query = """
        SELECT
            movie.id,
            movie.title,
            movie.description,
            movie.rating,
            movie.duration,
            movie.released_date,
            movie.trailer_link,
            STRING_AGG(genre.name, ', ') as genre_names
        FROM
            movie movie
        INNER JOIN
            movie_genres mgtabla
        ON 
            movie.id = mgtabla.movie_id
        INNER JOIN
            genre genre
        ON
            mgtabla.genre_id = genre.id
        GROUP BY
            movie.id, movie.title, movie.description, movie.rating, movie.duration, movie.released_date, movie.trailer_link
        ORDER BY
            movie.id ASC;
        """
        def movies = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.title = it.title
            item.description = it.description
            item.rating = it.rating
            item.duration = it.duration
            item.releasedDate = it.released_date
            item.trailerLink = it.trailer_link
            item.genres = it.genre_names.split(', ')
            return item
        }
        return movies
    }

    public Movie save(MovieCommand movieCommand){
        def movie = new Movie()
        movie.title = movieCommand.title
        movie.description = movieCommand.description
        movie.rating = movieCommand.rating
        movie.duration = movieCommand.duration
        movie.releasedDate = movieCommand.releasedDate
        movie.trailerLink = movieCommand.trailerLink
        movie.save(flush: true)
        movieCommand.genres.each{
            def genre = Genre.findByName(it)
            movie.addToGenres(genre)
        }
        movie.save(flush: true, failOnError: true)
        return movie
    }

    public Movie getMovie(Long id){
        return Movie.get(id)
    }

    public Movie update(MovieCommand movieCommand){
        Movie movie = Movie.get(movieCommand.id)
        movie.title = movieCommand.title
        movie.description = movieCommand.description
        movie.rating = movieCommand.rating
        movie.duration = movieCommand.duration
        movie.releasedDate = movieCommand.releasedDate
        movie.trailerLink = movieCommand.trailerLink
        movie.genres.clear()
        movie.save(flush: true)
        movieCommand.genres.each{
            def genre = Genre.findByName(it)
            movie.addToGenres(genre)
        }
        movie.save(flush: true, failOnError: true)
        return movie
    }

    public Movie delete(Long id){
        Movie movie = Movie.get(id)
        movie.delete(flush: true)
        return movie
    }

    def getMovieCommand(Long id) {
        def movie = Movie.get(id)
        def movieCommand = new MovieCommand()
        movieCommand.id = movie.id
        movieCommand.title = movie.title
        movieCommand.description = movie.description
        movieCommand.rating = movie.rating
        movieCommand.duration = movie.duration
        movieCommand.releasedDate = movie.releasedDate
        movieCommand.trailerLink = movie.trailerLink
        movieCommand.genres = movie.genres.collect{it.name}
        return movieCommand
    }
}