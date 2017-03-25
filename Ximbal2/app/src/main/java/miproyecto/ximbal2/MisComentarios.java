package miproyecto.ximbal2;

/**
 * Created by joseph on 20/03/2017.
 */

public class MisComentarios {
    String Comentario, FotoUsuario, NombreUsuario, Usuario;
    int IdSitio;

    public MisComentarios(String comentario, String fotoUsuario, String nombreUsuario, String usuario, int idSitio) {
        Comentario = comentario;
        FotoUsuario = fotoUsuario;
        NombreUsuario = nombreUsuario;
        Usuario = usuario;
        IdSitio = idSitio;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public String getFotoUsuario() {
        return FotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        FotoUsuario = fotoUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public int getIdSitio() {
        return IdSitio;
    }

    public void setIdSitio(int idSitio) {
        IdSitio = idSitio;
    }
}
