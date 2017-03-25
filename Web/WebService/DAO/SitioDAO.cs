using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WebService.BO;
using WebService.DAO;

using System.Data;

namespace WebService.DAO
{
    public class SitioDAO
    {
        ConexionDAO BD = new ConexionDAO();
        string sql;


        public DataTable Recuperar(object obj)
        {
            sql = "EXEC DevolverTablaSITIOS";
            return BD.Tabla(sql);
        }
        public int AgregarSitio(object obj)
        {
            SitioBO Sitio = (SitioBO)obj;
            sql = "EXEC AgregarSITIOS   '" + Sitio.Descripcion + "','" + Sitio.Nombre + "' ," + Sitio.IdEstablecimiento + ", '"+ Sitio.Estatus + "', '" + Sitio.Direccion + "', '" + Sitio.Longitud + "', '" + Sitio.Latitud + "', " + Sitio.Idusuario + "";
            return BD.Ejecutar(sql);
        }
        public int EliminarSitio(object obj)
        {
            SitioBO Sitio = (SitioBO)obj;
            sql = "EXEC EliminarSITIOS " + Sitio.IdSitio + "";
            return BD.Ejecutar(sql);
        }
        public int ModificarSitio(object obj)
        {
            SitioBO Sitio = (SitioBO)obj;
            sql = "EXEC ModifcarSITIOS  " + Sitio.IdSitio + " ,'" + Sitio.Descripcion + "','" + Sitio.Nombre + "' ," + Sitio.IdEstablecimiento + ", '" + Sitio.Estatus + "', '" + Sitio.Direccion + "', '" + Sitio.Longitud + "', '" + Sitio.Latitud + "', " + Sitio.Idusuario + "";
            return BD.Ejecutar(sql);
        }
        public DataTable BuscarSitio(object obj)
        {
            SitioBO Sitio = (SitioBO)obj;
            sql = "EXEC FiltrarSITIOS  " + Sitio.IdSitio + " , '" + Sitio.Descripcion + "','" + Sitio.Nombre + "' ," + Sitio.IdEstablecimiento + ", '" + Sitio.Direccion + "', '" + Sitio.Longitud + "', '" + Sitio.Latitud + "'";
            return BD.Tabla(sql);
        }
        //Nuevos
        public DataTable DevolverTopSitios()
        {
            sql = "EXEC DevolverVistaSitios  ";
            return BD.Tabla(sql);
        }
        public DataTable DevolverTopEventos()
        {
            sql = "EXEC DevolverVistaEventos  ";
            return BD.Tabla(sql);
        }
        public DataTable filtrarCalificaciones(int id)
        {
            sql = "EXEC FiltrarCalificacion  " + id;
            return BD.Tabla(sql);
        }



        public DataTable agregarComentarioSitio(int idUsuario, string comentario, String fecha, int idsitio)
        {
            sql = "EXEC agregarComentarioSitio  " + idUsuario + ", "+ comentario+ ", " + fecha + ",  " + idsitio;
            return BD.Tabla(sql);
        }

        public DataTable agregarCalificacion(int idUsuario, string calificacion, int idsitio)
        {
            sql = "EXEC agregarCalificacion  " + idUsuario + ", " + calificacion + ",  " + idsitio;
            return BD.Tabla(sql);
        }




        public DataTable agregarComentarioEvento(int idUsuario, string comentario, String fecha, int idEvento)
        {
            sql = "EXEC agregarComentarioEvento  " + idUsuario + ", " + comentario + ", " + fecha + ",  " + idEvento;
            return BD.Tabla(sql);
        }

        public DataTable agregarCalificacionEvento(int idUsuario, string calificacion, int idEvento)
        {
            sql = "EXEC agregarCalificacionEvento  " + idUsuario + ", " + calificacion + ",  " + idEvento;
            return BD.Tabla(sql);
        }
    }
}