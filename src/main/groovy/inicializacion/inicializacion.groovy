package moviesrggb.inicializacion
import moviesrggb.genre.Genre
import moviesrggb.movie.Movie
import java.sql.Time

class Inicializacion {
    
    static def comienzo() {
        println "##################################################################"
        println "################# Inicializando la aplicación ####################"
        inicializarGenres()
        inicializarMovies()
    }

    private static void inicializarGenres() {
        println "Inicializando genres"
        def genres = [
            ["name": "Action", "description": "Películas de acción"],
            ["name": "Sci-Fi", "description": "Películas de ciencia ficción"],
            ["name": "Animation", "description": "Películas de animaciones"],
            ["name": "Adventure", "description": "Películas de adventuras"],
            ["name": "Comedy", "description": "Películas de comedia"],
            ["name": "Crime", "description": "Películas de crimen"],
            ["name": "Drama", "description": "Películas de drama"]
        ]       
        genres.each { genre ->
            if(Genre.findByName(genre.name)) return
            new Genre(genre).save(flush: true, failOnError: true)
        }
    }

    private static void inicializarMovies() {
        println "Inicializando movies"
        def movies = [
            ["title": "Tenet", "description": "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real-time.", "rating": 7.8, "duration": Time.valueOf("2:30:00"), "genres": [Genre.findByName("Action"), Genre.findByName("Sci-Fi")], "releasedDate": Date.parse("yyyy-MM-dd", "2020-09-03"), "trailerLink": "https://www.youtube.com/watch?v=LdOM0x0XDMo"],
            ["title": "Spider-Man: Into the Spider-Verse", "description": "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five spider-powered individuals from other dimensions to stop a threat for all realities.", "rating": 8.4, "duration": Time.valueOf("1:57:00"), "genres": [Genre.findByName("Action"), Genre.findByName("Animation"), Genre.findByName("Adventure")], "releasedDate": Date.parse("yyyy-MM-dd", "2018-12-14"), "trailerLink": "https://www.youtube.com/watch?v=tg52up16eq0"],
            ["title": "Knives Out", "description": "A detective investigates the death of a patriarch of an eccentric, combative family.", "rating": 7.9, "duration": Time.valueOf("2:10:00"), "genres": [Genre.findByName("Comedy"), Genre.findByName("Crime"), Genre.findByName("Drama")], "releasedDate": Date.parse("yyyy-MM-dd", "2019-11-27"), "trailerLink": "https://www.youtube.com/watch?v=qGqiHJTsRkQ"],
            ["title": "Guardians of the Galaxy", "description": "A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.", "rating": 8, "duration": Time.valueOf("2:01:00"), "genres": [Genre.findByName("Action"), Genre.findByName("Adventure"), Genre.findByName("Comedy")], "releasedDate": Date.parse("yyyy-MM-dd", "2014-08-01"), "trailerLink": "https://www.youtube.com/watch?v=d96cjJhvlMA"],
            ["title": "Avengers: Age of Ultron", "description": "When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping program called Ultron, things go horribly wrong and it's up to Earth's mightiest heroes to stop the villainous Ultron from enacting his terrible plan.", "rating": 7.3, "duration": Time.valueOf("2:21:00"), "genres": [Genre.findByName("Action"), Genre.findByName("Adventure"), Genre.findByName("Sci-Fi")], "releasedDate": Date.parse("yyyy-MM-dd", "2015-05-01"), "trailerLink": "https://www.youtube.com/watch?v=tmeOjFno6Do"]
        ]
        movies.each { movie ->
            if(Movie.findByTitle(movie.title)) return
            new Movie(movie).save(flush: true, failOnError: true)
        }
    }

    
}