/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.text.ParseException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import modelo.Usuarios;

/**
 *
 * @author AYLEEN ROMERO PATIÑO
 */
@Stateless
@Path("modelo.usuarios")
public class UsuariosFacadeREST extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "IngresoLaboratoriosRestPU")
    private EntityManager em;

    public UsuariosFacadeREST() {
        super(Usuarios.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Usuarios entity) {
        super.create(entity);
    }
//Metodo para guardar un usuario

    @POST
    @Path("registro")
    @Produces({"text/plain", "application/json"})
    public String createByParams(@FormParam("cedula") String cedula,
            @FormParam("clave") String clave,
            @FormParam("nombres") String nombres,
            @FormParam("apellidos") String apellidos,
            @FormParam("correo") String correo,
            @FormParam("celular") String celular,
            @FormParam("rolUsuario") int rolUsuario
    ) {

        try {
            Usuarios u = new Usuarios(cedula, clave, nombres, apellidos, correo, celular, rolUsuario);
            super.create(u);
            return "true";

        } catch (Exception e) {

            return "false";
        }
    }
//Metodo para guardar un usuario

    @POST
    @Path("editar")
    @Produces({"text/plain", "application/json"})
    public String editarUsuario(
            @FormParam("idUsuario") int idUsuario,
            @FormParam("cedula") String cedula,
            @FormParam("clave") String clave,
            @FormParam("nombres") String nombres,
            @FormParam("apellidos") String apellidos,
            @FormParam("correo") String correo,
            @FormParam("celular") String celular,
            @FormParam("rolUsuario") int rolUsuario
    ) {
        try {
            TypedQuery<Usuarios> qry;
            qry = getEntityManager().createNamedQuery("Usuarios.findByIdUsuario", Usuarios.class);
            qry.setParameter("idUsuario", idUsuario);
             Usuarios u = qry.getSingleResult();
             u.setApellidos(apellidos);
             u.setCedula(cedula);
             u.setNombres(nombres);
             u.setCorreo(correo);
             u.setClave(clave);
             u.setCelular(celular);
             u.setRolUsuario(rolUsuario);
            super.edit(u);
            return "true";

        } catch (Exception e) {
            return "false";
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Usuarios entity) {
        super.edit(entity);
    }

    //Metodo para eliminar un usuario
    @GET
    @Path("eliminar/{id}")
    @Consumes({"application/xml", "application/json"})
    public boolean remove(@PathParam("id") Integer id) {
        try {
            super.remove(super.find(id));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @GET
    @Path("{id}")
    @Produces({"application/json", "application/json"})
    public Usuarios find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("cedula={cedula},clave={clave}")
    @Produces({"application/json", "application/json"})
    public Usuarios buscar(@PathParam("cedula") String cedula, @PathParam("clave") String clave) {
        TypedQuery<Usuarios> qry;
        qry = getEntityManager().createQuery("SELECT u FROM Usuarios u WHERE u.cedula = :cedula and u.clave = :clave", Usuarios.class);
        qry.setParameter("cedula", cedula);
        qry.setParameter("clave", clave);
        try {
            Usuarios u = qry.getSingleResult();
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Path("cedula={cedula}")
    @Produces({"application/json", "application/json"})
    public Usuarios buscar(@PathParam("cedula") String cedula) {
        TypedQuery<Usuarios> qry;
        qry = getEntityManager().createQuery("SELECT u FROM Usuarios u WHERE u.cedula = :cedula", Usuarios.class);
        qry.setParameter("cedula", cedula);
        try {
            Usuarios u = qry.getSingleResult();
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Override
    @Produces({"application/json", "application/json"})
    public List<Usuarios> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json", "application/json"})
    public List<Usuarios> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
