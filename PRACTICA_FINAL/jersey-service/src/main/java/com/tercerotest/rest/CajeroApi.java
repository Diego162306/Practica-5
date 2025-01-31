package com.tercerotest.rest;
import java.util.HashMap;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.models.Cajero;
import com.google.gson.Gson;

import com.tercerotest.controller.dao.services.CajeroServicies;

@Path("cajeross")
public class CajeroApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        CajeroServicies ps = new CajeroServicies();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }

  
    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        // todo
        // Validation

        HashMap res = new HashMap<>();

        try {

            CajeroServicies ps = new CajeroServicies();
            ps.getcajero().setId(Integer.parseInt(map.get("id").toString()));
            ps.getcajero().setLatitude(Double.parseDouble(map.get(("lat")).toString()));
            ps.getcajero().setLongitude(Double.parseDouble(map.get(("lng")).toString()));
            ps.getcajero().setSaldoDsp(Double.parseDouble(map.get("saldoDsp").toString()));
            ps.getcajero().setEstado((map.get("estado").toString().equalsIgnoreCase("true") ? Boolean.TRUE : Boolean.FALSE));
            ps.getcajero().setBanco(map.get("banco").toString());
            System.out.println("Datos recibidos: " + map);

            
            ps.save();
            res.put("msg", "Ok");
            res.put("data", "Guardado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        // todo
        // Validation

        HashMap res = new HashMap<>();

        try {

            CajeroServicies ps = new CajeroServicies();
            ps.getcajero().setId(Integer.parseInt(map.get("id").toString()));
            ps.getcajero().setLatitude(Double.parseDouble(map.get(("lat")).toString()));
            ps.getcajero().setLongitude(Double.parseDouble(map.get(("lng")).toString()));
            ps.getcajero().setSaldoDsp(Double.parseDouble(map.get("saldoDsp").toString()));
            ps.getcajero().setEstado((map.get("isMural").toString().equalsIgnoreCase("true") ? Boolean.TRUE : Boolean.FALSE));
            ps.getcajero().setBanco(map.get("banco").toString());
            
            ps.update();
            res.put("msg", "Ok");
            res.put("data", "Guardado correctamente");
            return Response.ok(res).build();

        } catch (IllegalArgumentException e) {
            
            res.put("msg", "Error en validación");
            res.put("data", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(res).build();
    
        } catch (Exception e) {
            res.put("msg", "Error en el servidor");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
   
    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFamilia(@PathParam("id") int id) {
        HashMap<String, Object> res = new HashMap<>();

        try {
            CajeroServicies fs = new CajeroServicies();

            // Intentar eliminar la familia
            boolean familiaDeleted = fs.delete(id - 1);

            if (familiaDeleted) {
                res.put("message", "Familia y Generador eliminados exitosamente");
                return Response.ok(res).build();
            } else {
                // Si no se eliminó, enviar un error 404
                res.put("message", "Familia no encontrada o no eliminada");
                return Response.status(Response.Status.NOT_FOUND).entity(res).build();
            }
        } catch (Exception e) {
            // En caso de error, devolver una respuesta de error interno
            res.put("message", "Error al intentar eliminar la familia");
            res.put("error", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
