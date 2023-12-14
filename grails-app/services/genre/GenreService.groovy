package moviesrggb.genre
import moviesrggb.genre.Genre
import java.util.LinkedHashMap
import org.hibernate.transform.Transformers
import grails.transaction.Transactional

@Transactional
class GenreService {

    def sessionFactory

    public List<Genre> listGenres(){
        def query = """
        SELECT
            g.id,
            g.name,
            g.description
        FROM
            genre g
        ORDER BY
            g.id ASC;
        """
        def genres = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.name = it.name
            item.description = it.description
            return item
        }
        return genres
    }

    public Genre getGenre(Long id){
        return Genre.get(id)
    }
    
    public Genre save(GenreCommand genreCommand){
        def genre = new Genre()
        genre.name = genreCommand.name
        genre.description = genreCommand.description
        genre.save(flush: true)
        return genre
    }

    public Genre update(GenreCommand genreCommand){
        def genre = Genre.get(genreCommand.id)
        genre.name = genreCommand.name
        genre.description = genreCommand.description
        genre.save(flush: true)
        return genre
    }

    public Genre delete(GenreCommand genreCommand){
        def genre = Genre.get(genreCommand.id)
        genre.delete(flush: true)
        return genre
    }

}