/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import modelo.Persistencia;
import entidades.entidad_pelicula;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.*;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author HAIMER
 */
@Named(value = "controlPelicula")
@ApplicationScoped
public class ControlPelicula {

    Persistencia p = new Persistencia();
    
       private Integer nid_pelicula;
        private String ntitulo;
        private String ndirector;
        private String ntipo ;
        private String ndescripcion;
        private String nimagen;
        
        private int  nrating ;                
        private    int   ncantidadvotos;

        
            private String pelicula;
    private String director;
    private String tipo;
    private String descripcion;
       private int rating ;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }



    private String titulo;
    private Integer id_pelicula;
    
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

  
    

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

   

   

    /**
     * Creates a new instance of ControlPelicula
     */
    ArrayList<entidad_pelicula> listapelicula;

    ArrayList<String> idpelicula;

    public ControlPelicula() {

        llenar();

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

            listapelicula.add(new entidad_pelicula(Integer.toString(nid_pelicula), ntitulo, ndirector,ntipo,ndescripcion,nimagen,nrating,ncantidadvotos));
        }
    }

    public ArrayList<entidad_pelicula> getListapelicula() {
        return listapelicula;
    }

    public void setListapelicula(ArrayList<entidad_pelicula> listapelicula) {
        this.listapelicula = listapelicula;
    }

  


    public void insertpelicula() {
           
       String ruta = file.getFileName();
        Path path = Paths.get(ruta); 
  
        // call getFileName() and get FileName path object 
        Path fileName = path.getFileName(); 
  
        // print FileName 
       this.imagen = fileName.toString(); 
        
        boolean inserto = false;
        String sql = "INSERT INTO pelicula(nombre_pelicula, director, tipo, descripcion,imagen,rating, cantidadvotos)"
                + " VALUES ('" + this.titulo + "','" + this.director + "','"+this.tipo+"','"+this.descripcion+"','"+this.imagen+"','0','0')";

        inserto = p.ejecutarDML(sql);
        
        
        String fromFile = ruta;
        String toFile = "C:\\Users\\HAIMER\\Documents\\NetBeansProjects\\Sistema_peliculas\\web\\imagenes\\"+this.imagen+"";
        boolean result = copyFile(fromFile, toFile);
        System.out.println(result?
            "Success! File copying (Éxito! Fichero copiado)":
                "Error! Failed to copy the file (Error! No se ha podido copiar el fichero)");
        
        if (inserto== true ) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("pelicula ingresada "));

        }else {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("pelicula no ingresada"));

        }
        
        this.titulo= null;
        this.director=null;
        this.descripcion=null;

        llenar();
    }

    public int contarpeliculas() {
        int numero = 0;
        String sql = "SELECT count(p.id_pelicula) FROM pelicula p;";
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
        String sql = "SELECT * FROM pelicula; ";

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
    
    

    public void seleccioneliminar() {
        idpelicula = new ArrayList<String>();
        for (entidad_pelicula p : listapelicula) {

            if (p.isSelect()) {

                idpelicula.add(p.getId_pelicula());
            }

        }
    }

    public void eliminarpelicula() {
        seleccioneliminar();

        if (!idpelicula.isEmpty()) {

            for (int i = 0; i < idpelicula.size(); i++) {
                boolean elimino = false;

                String sql = "Delete from pelicula where id_pelicula= '" + idpelicula.get(i) + "'";
                elimino = p.ejecutarDML(sql);

                if (elimino == true) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("datos eliminados"));

                }

            }
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("seleccione datos para eliminar"));

        }

        llenar();

    }
    
    
    public  void actualizar (){
        
         boolean actualizo = false;

        String sql = "UPDATE pelicula SET  nombre_pelicula='"+selectedCar.getPelicula()+"', director='"+selectedCar.getDirector()
                +"', tipo='"+selectedCar.getTipo()+"', descripcion='"+selectedCar.getDescripcion()+"' WHERE id_pelicula='"+selectedCar.getId_pelicula()+"'";

        actualizo = p.ejecutarDML(sql);
        
       
       
    }
    
    
    
    
    
    
    
    
    
    public boolean copyFile(String fromFile, String toFile) {
        File origin = new File(fromFile);
        File destination = new File(toFile);
        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                // We use a buffer for the copy (Usamos un buffer para la copia).
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
    
    
    
     private entidad_pelicula selectedCar;

    public entidad_pelicula getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(entidad_pelicula selectedCar) {
        this.selectedCar = selectedCar;
    }
    
    public Integer getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Integer id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
        
        
    public String getNimagen() {
        return nimagen;
    }

    public void setNimagen(String nimagen) {
        this.nimagen = nimagen;
    }
    
      public  void actualizarreting (){
        
         boolean actualizo = false;
         
         int id = this.nid_pelicula;
       
          int votos =this.ncantidadvotos;
           int re= this.nrating;          
         
           int nuevotos = votos+1;
           int nuret= (re+this.rating)/2;

       String sql = "UPDATE pelicula SET  rating='"+nuevotos+"', cantidadvotos='"+nuret+"'  WHERE id_pelicula='"+this.nid_pelicula+"'";

       actualizo = p.ejecutarDML(sql);
        
       
       
    }

}
