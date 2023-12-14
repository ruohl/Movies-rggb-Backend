package moviesrggb.genre
import grails.converters.JSON

class GenreController {

    def genreService

    def list() {
        def genres = genreService.listGenres()
        [genres: genres]
    }

    def create() {
    
    }

    def save(GenreCommand genreCommand) {
        genreService.save(genreCommand)
    }

    def edit(Long id) {
        def genreCommand = genreService.getGenreCommand(id)
        [genreCommand: genreCommand]
    }

    def update(GenreCommand genreCommand) {
        genreService.update(genreCommand)
    }

    def delete(Long id) {
        genreService.delete(id)
    }

    def ajaxGetGenres() {
        def genres = genreService.listGenres()
        render genres as JSON
    }

}