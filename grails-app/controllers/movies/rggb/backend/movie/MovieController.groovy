package moviesrggb.movie
import grails.converters.JSON



class MovieController {
    def movieService

    def list() {
        def movies = movieService.listMovies()
        [movies: movies]
    }

    def create() {
    
    }

    def save(MovieCommand movieCommand) {
        movieService.save(movieCommand)
    }

    def edit(Long id) {
        def movieCommand = movieService.getMovieCommand(id)
        [movieCommand: movieCommand]
    }

    def update(MovieCommand movieCommand) {
        movieService.update(movieCommand)
    }

    def delete(Long id) {
        movieService.delete(id)
    }

    def ajaxGetMovies() {
        def movies = movieService.listMovies()
        render movies as JSON
    }
}