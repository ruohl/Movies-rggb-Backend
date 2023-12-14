package movies.rggb.backend
import moviesrggb.inicializacion.Inicializacion
class BootStrap {

    def init = { servletContext ->
        Inicializacion.comienzo()
    }
    def destroy = {
    }
}
