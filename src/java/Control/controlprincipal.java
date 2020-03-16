/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import entidades.entidad_pelicula;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Persistencia;

/**
 *
 * @author HAIMER
 */
@Named(value = "controlprincipal")
@ApplicationScoped
public class controlprincipal {

    Persistencia p = new Persistencia();

    /**
     * Creates a new instance of controlprincipal
     */
    public controlprincipal() {
        
        llenar();
    }
   
     entidad_pelicula en = new entidad_pelicula();
    
    ArrayList<entidad_pelicula> listapelicula;

    public ArrayList<entidad_pelicula> getListapelicula() {
        return listapelicula;
    }

    public void setListapelicula(ArrayList<entidad_pelicula> listapelicula) {
        this.listapelicula = listapelicula;
    }

    private Integer nid_pelicula;

    public Integer getNid_pelicula() {
        return nid_pelicula;
    }

    public void setNid_pelicula(Integer nid_pelicula) {
        this.nid_pelicula = nid_pelicula;
    }

    public int getNrating() {
        return nrating;
    }

    public void setNrating(int nrating) {
        this.nrating = nrating;
    }

    public int getNcantidadvotos() {
        return ncantidadvotos;
    }

    public void setNcantidadvotos(int ncantidadvotos) {
        this.ncantidadvotos = ncantidadvotos;
    }
    private String ntitulo;
    private String ndirector;
    private String ntipo;
    private String ndescripcion;
    private String nimagen;

    private int nrating;
    private int ncantidadvotos;
    
    private entidad_pelicula pelicula;

    public entidad_pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(entidad_pelicula pelicula) {
        this.pelicula = pelicula;
    }
    
    
    private int rating ;

    public String getImagen() {
        return nimagen;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setImagen(String imagen) {
        this.nimagen = imagen;
    }

    public void llenar() {

        listapelicula = new ArrayList<entidad_pelicula>();
        Object datos[][] = mostrarpeliculas();

        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[i].length; j++) {

                nid_pelicula = Integer.parseInt(String.valueOf(datos[i][0]));
                ntitulo = String.valueOf(datos[i][1]);
                ndirector = String.valueOf(datos[i][2]);
                ntipo = String.valueOf(datos[i][3]);
                ndescripcion = String.valueOf(datos[i][4]);
                nimagen = String.valueOf(datos[i][5]);
                nrating = Integer.parseInt(String.valueOf(datos[i][6]));
                ncantidadvotos = Integer.parseInt(String.valueOf(datos[i][7]));
            }

            listapelicula.add(new entidad_pelicula(Integer.toString(nid_pelicula), ntitulo, ndirector, ntipo, ndescripcion, nimagen,nrating,ncantidadvotos));
        }
    }

    public int contarpeliculas() {
        int numero = 0;
        String sql = "SELECT count(p.id_pelicula) FROM pelicula p";
        ResultSet res = p.ejecutarconsulta(sql);
        try {
            while (res.next()) {
                numero = res.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numero;
    }

    public Object[][] mostrarpeliculas() {
        Object data[][] = new Object[contarpeliculas()][8];
        ResultSet res = null;
        String sql = "SELECT id_pelicula, nombre_pelicula, director, tipo, descripcion, imagen, rating, cantidadvotos\n" +
"	FROM public.pelicula  p ORDER by  p.rating DESC ";

        res = p.ejecutarconsulta(sql);

        try {
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString(1);
                data[i][1] = res.getString(2);
                data[i][2] = res.getString(3);
                data[i][3] = res.getString(4);
                data[i][4] = res.getString(5);
                data[i][5] = res.getString(6);
                data[i][6] = res.getString(7);
                data[i][7] = res.getString(8);

                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;

    }
    
    

}
