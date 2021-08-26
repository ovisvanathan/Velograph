package com.horus.velograph;

import com.github.mjeanroy.junit.servers.annotations.TestServer;
import com.github.mjeanroy.junit.servers.jetty.EmbeddedJetty;
import com.github.mjeanroy.junit.servers.junit4.JunitServerRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import java.net.URI;

import org.junit.Before;
import org.junit.After;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import com.horus.velograph.entity.*;
import com.horus.velograph.model.*;
import com.horus.velograph.api.*;
import com.horus.velograph.controller.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.grizzly.http.server.HttpServer;

import org.glassfish.jersey.logging.LoggingFeature;
import javax.ws.rs.core.Feature;

import java.util.logging.Logger;
import java.util.logging.Level;


// EmbeddedServer does not work. Use with another server
//@RunWith(JunitServerRunner.class)
public class HttpTest extends JerseyTest {

//  @TestServer
//  public static EmbeddedJetty jetty;

	private static String SERVICE_URL
      = "http://localhost:8080/server-example/rest";

	Client client;
	WebTarget target;
	HttpServer server;
	
	@Before
    public void setUp() throws Exception {

//        server = GrizzlyHttpServerFactory.createHttpServer(URI.create(SERVICE_URL), rc);       

			new ResourceConfig(CountryController.class)
							.packages("com.horus.velograph.resource")
							.packages("com.horus.velograph.controller");

    }

    @After
    public void tearDown() throws Exception {
 //      server.stop();
    }
	
	
  @Override
    public Application configure() {
			
		Logger logger = Logger.getLogger(getClass().getName());

		Feature feature = new LoggingFeature(logger, Level.INFO, null, null);

		client = ClientBuilder.newBuilder()
        .register(feature)
        .build();
	
			return new ResourceConfig(CountryController.class)
							.packages("com.horus.velograph.resource")
							.packages("com.horus.velograph.controller");

    }


	/*
  @Before
	public void setup() throws IOException {
		RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8082;
	}
	*/

	@Test  
	public void testPostCountry() {	  
	  
	   Map<String, String> data = new HashMap<String, String>();
		data.put("id", "2");
		data.put("countryName", "Bilagon");
		data.put("population", "5000000");

        target = client.target(SERVICE_URL).path("countries");

		final Response response = target
				.request()
				.post(Entity.json(data));

		String output = response.readEntity(String.class);

		System.out.println(" TPC op = " + output);		
	
	}

	@Test
	public void testGetCountries() throws IOException {
		

			target = client.target(SERVICE_URL).path("countries");
		
			Response response = target.request()
				.get();
				  
		  	String output = response.readEntity(String.class);

			System.out.println(" TPC op = " + output);	
	}

	@Test
	public void testGetCountry() throws IOException {
		
			target = client.target(SERVICE_URL).path("countries/2");

			Response response = target.request()
				.get();
			  
			String output = response.readEntity(String.class);

			System.out.println(" TPC op = " + output);
	}


	@Test  
	public void testUpdateCountry() {		
	
	   Map<String, String> data = new HashMap<String, String>();
		data.put("id", "2");
		data.put("population", "7500000");

        target = client.target(SERVICE_URL).path("countries");

		Response response = target
				.request()
				.put(Entity.json(data));

		System.out.println("TGU = " + response
          .getStatus());

		String output = response.readEntity(String.class);

		System.out.println(" TPC op = " + output);


	}

	@Test  
	public  void testDeleteCountry() {

        target = client.target(SERVICE_URL).path("countries/2");

		Response response =  target
				.request()
				.delete();

		String output = response.readEntity(String.class);

		System.out.println(" TPC op = " + output);

	}
  
	@Test  
	public void testPostGift() {	  
	  
	   Map<String, String> data = new HashMap<String, String>();
		data.put("name", "Trainset");

        target = client.target(SERVICE_URL).path("gifts");

		final Response response = target
				.request()
				.post(Entity.json(data));

		String output = response.readEntity(String.class);

		System.out.println(" TPC op = " + output);		
	
	}

	@Test
	public void testGetGifts() throws IOException {
		

			target = client.target(SERVICE_URL).path("gifts");
		
			Response response = target.request()
				.get();
				  
		  	String output = response.readEntity(String.class);

			System.out.println(" TPC op = " + output);	
	}

	@Test
	public void testGetGift() throws IOException {
		
			target = client.target(SERVICE_URL).path("gifts/2");

			Response response = target.request()
				.get();
			  
			String output = response.readEntity(String.class);

			System.out.println(" TPC op = " + output);
	}


	@Test  
	public void testUpdateGift() {		
	
	   Map<String, String> data = new HashMap<String, String>();
		data.put("id", "2");
		data.put("name", "Railset");

        target = client.target(SERVICE_URL).path("gifts");

		Response response = target
				.request()
				.put(Entity.json(data));

		System.out.println("TGU = " + response
          .getStatus());

		String output = response.readEntity(String.class);

		System.out.println(" TPC op = " + output);


	}

	@Test  
	public  void testDeleteGift() {

        target = client.target(SERVICE_URL).path("gifts/2");

		Response response =  target
				.request()
				.delete();

		String output = response.readEntity(String.class);

		System.out.println(" TPC op = " + output);

	}
  
	@Test  
	public  void testAddGraph() {

		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "Route");


        target = client.target(SERVICE_URL).path("graphs");

		final Response response = target
				.request()
				.post(Entity.json(data));

		String output = response.readEntity(String.class);

		System.out.println(" TPC op = " + output);		

	}


	@Test  
	public  void testGetGraphs() {

			target = client.target(SERVICE_URL).path("graphs");
		
			Response response = target.request()
				.get();
				  
		  	String output = response.readEntity(String.class);

			System.out.println(" TPC op = " + output);	

	}





}