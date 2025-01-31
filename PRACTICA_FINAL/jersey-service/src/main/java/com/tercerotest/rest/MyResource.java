package com.tercerotest.rest;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tercerotest.controller.graph.Adyacencia;
import com.tercerotest.controller.graph.GhaphLabelNoDirect;
import com.tercerotest.controller.graph.Graphdirect;
import com.tercerotest.controller.tda.list.LinkedList;
import com.tercerotest.models.Cajero;
import com.tercerotest.controller.graph.Graph;
import com.tercerotest.controller.graph.GraphLabelDirect;
import com.tercerotest.controller.graph.GraphNoDirect;
import com.tercerotest.controller.dao.services.CajeroServicies;
import com.tercerotest.controller.excepcion.OverFlowException;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        HashMap mapa = new HashMap<>();        
        GhaphLabelNoDirect graph = new GhaphLabelNoDirect<>(5, String.class);
        try{
            CajeroServicies ps = new CajeroServicies();
            LinkedList<Cajero>  lc = ps.listAll();
            if (!lc.isEmpty()) {
                graph = new GhaphLabelNoDirect<>(lc.getSize(), Cajero.class);
                Cajero[] m = lc.toArray();
                for (int i = 0; i < lc.getSize(); i++) {
                    graph.labelsVertices((i+1), m[i]);
                }
            }
            // grap.labelsVertices(1, "A");
            // grap.labelsVertices(2, "B");
            // grap.labelsVertices(3, "C");
            // grap.labelsVertices(4, "D");
            // grap.labelsVertices(5, "E");
            // grap.insertEdgeL("A", "B");
            // grap.insertEdgeL("A", "C");
           graph.drawGraph();

        } catch (Exception e) {
            System.out.println("Error al procesar: " + e.getMessage());
            mapa.put("msg", "Error");
            mapa.put("error", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mapa).build();
        }

        return Response.ok(mapa).build();
    }
}
