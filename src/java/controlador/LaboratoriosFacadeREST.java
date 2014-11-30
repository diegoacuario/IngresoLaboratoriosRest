/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
import modelo.Equipos;
import modelo.Laboratorios;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
@Stateless
@Path("modelo.laboratorios")
public class LaboratoriosFacadeREST extends AbstractFacade<Laboratorios> {

    @PersistenceContext(unitName = "IngresoLaboratoriosRestPU")
    private EntityManager em;

    public LaboratoriosFacadeREST() {
        super(Laboratorios.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Laboratorios entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Laboratorios entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json", "application/json"})
    public Laboratorios find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json; charset=UTF-8", "application/json"})
    public List<Laboratorios> findAll() {
        TypedQuery<Laboratorios> qry;
        qry = getEntityManager().createNamedQuery("Laboratorios.findByBloqueado", Laboratorios.class);
        qry.setParameter("bloqueado", 0);
        return qry.getResultList();
    }
    /*
     @GET
     @Override
     @Produces({"application/json; charset=UTF-8", "application/json"})
     public List<Laboratorios> findAll() {
     return super.findAll();
     }
     */

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json", "application/json"})
    public List<Laboratorios> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    //Metodo para guardar un equipo

    @POST
    @Path("registro")
    @Produces({"text/plain", "application/json"})
    public String createByParams(@FormParam("cod") String cod,
            @FormParam("nom") String nom,
            @FormParam("des") String des
    ) {
        try {
            Laboratorios l = new Laboratorios(cod, nom, des, 0);
            super.create(l);
            return "true";
        } catch (Exception e) {
            return "false";
        }
    }

    @GET
    @Path("cod={id}")
    @Produces({"application/json; charset=utf-8", "application/json"})
    public Laboratorios buscarPorLaboratorio(@PathParam("id") String cod) {
        TypedQuery<Laboratorios> qry;
        qry = getEntityManager().createQuery("SELECT l FROM Laboratorios l WHERE l.codigo = :cod", Laboratorios.class);
        qry.setParameter("cod", cod);
        try {
            Laboratorios u = qry.getSingleResult();
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    @POST
    @Path("editar")
    @Produces({"text/plain", "application/json"})
    public String editarLaboratorio(
            @FormParam("idLab") Integer idLab,
            @FormParam("codigo") String codigo,
            @FormParam("nombre") String nombre,
            @FormParam("desc") String desc
    ) {
        try {
            TypedQuery<Laboratorios> qry;
            qry = getEntityManager().createNamedQuery("Laboratorios.findByIdLaboratorio", Laboratorios.class);
            qry.setParameter("idLaboratorio", idLab);
            Laboratorios lab = qry.getSingleResult();
            lab.setCodigo(codigo);
            lab.setNombre(nombre);
            lab.setDescripcion(desc);
            super.edit(lab);
            return "true";

        } catch (Exception e) {
            System.out.println(e);
            return "false";
        }
    }
        //Metodo para bloquear un lab
    @GET
    @Path("bloquear/{id}")
    @Produces({"text/plain", "application/json"})
    public boolean bloquear(@PathParam("id") String id) {
        try {
            TypedQuery<Laboratorios> qry;
            qry = getEntityManager().createNamedQuery("Laboratorios.findByCodigo", Laboratorios.class);
            qry.setParameter("codigo", id);
            Laboratorios l = qry.getSingleResult();
            l.setBloqueado(1);
            super.edit(l);
            return true;

        } catch (Exception e) {
            return false;
        }

    }
    //Metodo para desbloquear un lab
   @GET
    @Path("desbloquear/{id}")
    @Produces({"text/plain", "application/json"})
    public boolean desbloquear(@PathParam("id") String id) {
        try {
            TypedQuery<Laboratorios> qry;
            qry = getEntityManager().createNamedQuery("Laboratorios.findByCodigo", Laboratorios.class);
            qry.setParameter("codigo", id);
            Laboratorios l = qry.getSingleResult();
            l.setBloqueado(0);
            super.edit(l);
            return true;

        } catch (Exception e) {
            return false;
        }

    }
}
