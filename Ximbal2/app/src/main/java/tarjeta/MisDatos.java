package tarjeta;

/**
 * Created by joseph on 14/03/2017.
 */
public class MisDatos {
    private int id,Idestablecimiento, idSitio;
    private String nombre;
    private String descripcion;
    private String Direccion;
    private String Latitud;
    private String Longitud;
    private String nombreEstablecimiento;
    private String img_link;
    private String fotos;
    private String info;


    public MisDatos(int id, int idestablecimiento, String nombre, String descripcion, String direccion, String latitud, String longitud, String nombreEstablecimiento, String img_link, String fotos, int idSitio,String info) {
        this.id = id;
        Idestablecimiento = idestablecimiento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        Direccion = direccion;
        Latitud = latitud;
        Longitud = longitud;
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.img_link = img_link;
        this.fotos = fotos;
        this.idSitio = idSitio;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getIdSitio() {
        return idSitio;
    }

    public void setIdSitio(int idSitio) {
        this.idSitio = idSitio;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdestablecimiento() {
        return Idestablecimiento;
    }

    public void setIdestablecimiento(int idestablecimiento) {
        Idestablecimiento = idestablecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }
}
