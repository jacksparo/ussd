/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ussd.service;

import com.ussd.entity.Request;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jgitundu
 */
@Stateless
@Path("request")
public class RequestFacadeREST extends AbstractFacade<Request> {

    @PersistenceContext(unitName = "com.mycompany_ussd_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public RequestFacadeREST() {
        super(Request.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Request entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Request entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("handler")
    @Produces({MediaType.TEXT_PLAIN})
    public String ussdHandler(
            @QueryParam(value = "sessionId") String sessionId,
            @QueryParam(value = "phoneNumber") String phoneNumber,
            @QueryParam(value = "input") String input
    ) {

        String[] rawStringArray = input.split("\\*");
        int rawLevel = rawStringArray.length;
        String lastInput = rawStringArray[rawLevel - 1];
        if (lastInput.equalsIgnoreCase("00")) {
            Request request = em.find(Request.class, sessionId);
            request.setInput("");
        } else if (!lastInput.equalsIgnoreCase("0")) {
            Request request = em.find(Request.class, sessionId);
            if (request == null) {
                em.persist(new Request(sessionId, input, phoneNumber));
            } else {
                String dbInput = request.getInput();
                if (input != "") {
                    request.setInput(dbInput + lastInput + "*");
                }
            }
        } else {

        }

        return "CON Welocome to my USSD\n1.Buy Aitrtime\n2.Redeem Bonga";
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Request find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Request> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Request> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
