package com.horus.velograph.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;

import com.horus.velograph.service.GiftService;
import com.horus.velograph.entity.Gift;

import javax.inject.Inject;


@Path("/gifts")
public class GiftResource {

	@Inject
    GiftService service = new GiftService();
	
	
    @GET
	@Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") @DefaultValue("vicky") String name) {
        return "hello" + name;
    }
	
	
	@GET
	@Path("/new")
    @Produces(MediaType.TEXT_PLAIN)
    public Gift newGift() {
		return service.addGift();
    }

    @GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public Gift getGift(@PathParam("id") @DefaultValue("100") long id) {
		return (Gift) service.getGift(id);
    }

    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public List getGifts() {
		return service.getGifts();
    }

	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Gift addGift(Gift gift) {
		return service.addGift(gift);
    }

	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Gift updateGift(Gift gift) {
		return service.updateGift(gift);
    }
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    public void deleteGift(@PathParam("id") long id) {
		service.deleteGift(id);
    }
	

}