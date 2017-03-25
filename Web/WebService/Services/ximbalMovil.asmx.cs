using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using WebService.BO;
using WebService.DAO;
using System.Data;
using System.Web.Script.Services;
using Newtonsoft.Json;

namespace WebService.Services
{
    /// <summary>
    /// Summary description for ximbalMovil
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class ximbalMovil : System.Web.Services.WebService
    {
        EstablecimientoDAO EstablecimientoDAO = new EstablecimientoDAO();
        FotoDAO FotoDAO = new FotoDAO();
        FotoEDAO FotoDAOE = new FotoEDAO();
        SitioDAO SitioDAO = new SitioDAO();
        TipoEstablecimientoDAO TipoEstablecimientoDAO = new TipoEstablecimientoDAO();
        VISTADETALLESITIODAO VISTADETALLESITIO = new VISTADETALLESITIODAO();
        EventoDAO EventoDAO = new EventoDAO();
        private void SalidaJSON(DataTable obj)
        {
            //Convertir a JSON FORMAT
            string SalidaJSON = string.Empty;
            SalidaJSON = JsonConvert.SerializeObject(obj);
            //Salida en el web service
            HttpContext Contexto = HttpContext.Current;
            Context.Response.ContentType = "application/json";
            Context.Response.Write(SalidaJSON);
            Context.Response.End();
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void buscarSitioMovil(string nombre, string longitud, string latitud, string direccion, string estatus, int idEstablecimiento, int idSitio)
        {
            SitioBO lugar = new SitioBO();
            lugar.Nombre = nombre;
            lugar.Longitud = longitud;
            lugar.Latitud = latitud;
            lugar.Direccion = direccion;
            lugar.Estatus = estatus;
            lugar.IdEstablecimiento = idEstablecimiento;
            lugar.IdSitio = idSitio;
            SalidaJSON(SitioDAO.BuscarSitio(lugar));
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void ObtenerSitioMovil(string nombre, string longitud, string latitud, string direccion, string estatus, int idEstablecimiento, int idSitio)
        {
            SitioBO lugar = new SitioBO();
            lugar.Nombre = nombre;
            lugar.Longitud = longitud;
            lugar.Latitud = latitud;
            lugar.Direccion = direccion;
            lugar.Estatus = estatus;
            lugar.IdEstablecimiento = idEstablecimiento;
            lugar.IdSitio = idSitio;
            SalidaJSON(SitioDAO.BuscarSitio(lugar));
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void buscarEstablecimientoMovil(int idTipoEstablecimiento, string nombre, int id)
        {
            EstablecimientoBO establecimiento = new EstablecimientoBO();
            establecimiento.IdEstablecimiento = idTipoEstablecimiento;
            establecimiento.Nombre = nombre;
            establecimiento.IdEstablecimiento = id;
            SalidaJSON(EstablecimientoDAO.BuscarEstablecimiento(establecimiento));
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void buscarTipoEstablecimientoMovil(int idtipoEstablecimiento, string nombre)
        {
            TipoEstablecimientoBO tipoEstablecimiento = new TipoEstablecimientoBO();
            tipoEstablecimiento.IdTipoEstablecimiento = idtipoEstablecimiento;
            tipoEstablecimiento.Nombre = nombre;
            SalidaJSON(TipoEstablecimientoDAO.BuscarTipoEstablecimiento(tipoEstablecimiento));
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void buscarFotoMovil(string ruta, int idSitio)
        {
            FotoBO foto = new FotoBO();
            foto.Foto = ruta;
            foto.IdSitio = idSitio;
            SalidaJSON(FotoDAO.BuscarFoto(foto));
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void buscarFotoEventoMovil(string ruta, int idSitio)
        {
            FotoBO foto = new FotoBO();
            foto.Foto = ruta;
            foto.IdSitio = idSitio;
            SalidaJSON(FotoDAOE.BuscarFoto(foto));
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void buscarEventosMovil(string nombre, string longitud, string latitud, string direccion, DateTime fechaInicio, DateTime fechafin)
        {
            EventoBO lugar = new EventoBO();
            lugar.Nombre = nombre;
            lugar.Longitud = longitud;
            lugar.Latitud = latitud;
            lugar.Direccion = direccion;
            lugar.FechaInicio = fechaInicio;
            lugar.FechaFin = fechafin;
            SalidaJSON(EventoDAO.BuscarEvento(lugar));
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void devolverTopSitios(int i)
        {

            SalidaJSON(SitioDAO.DevolverTopSitios());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void devolverTopEventos(int i)
        {
            SalidaJSON(SitioDAO.DevolverTopEventos());
        }
        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void FiltrarCalificacion(int i)
        {

            SalidaJSON(SitioDAO.filtrarCalificaciones(i));
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void agregarComentarioSitio(int idUsuario , string comentario ,string fecha, int Idsitio)
        {

            SalidaJSON(SitioDAO.agregarComentarioSitio(idUsuario, comentario, fecha, Idsitio));
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void agregarCalificacionSitio(int idUsuario, string calificacion,int Idsitio)
        {

            SalidaJSON(SitioDAO.agregarCalificacion(idUsuario, calificacion, Idsitio));
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        public void agregarComentarioEvento(int idUsuario, string comentario, string fecha, int IdEvento)
        {

            SalidaJSON(SitioDAO.agregarComentarioSitio(idUsuario, comentario, fecha, IdEvento));
        }

        [WebMethod]
        [ScriptMethod(ResponseFormat = ResponseFormat.Json)]
        
        public void agregarCalificacionEvento  (int idUsuario, string calificacion, int IdEvento)
        {

            SalidaJSON(SitioDAO.agregarCalificacion(idUsuario, calificacion, IdEvento));
        }


    }
}
