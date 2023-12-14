package movies.rggb.backend

class UrlMappings {

    static mappings = {
        "/movies" {
            controller = "movie"
            action = [GET: "ajaxGetMovies", POST: "save", PUT: "update", DELETE: "delete"]
        }
    }
}
