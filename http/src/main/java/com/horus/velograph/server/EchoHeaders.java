package com.horus.velograph.server;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import com.horus.velograph.client.filter.AddHeaderOnRequestFilter;

@Path("/echo-headers")
public class EchoHeaders {

    public static final String REALM_KEY = "realm";
    public static final String REALM_VALUE = "Baeldung";
    public static final String QOP_KEY = "qop";
    public static final String QOP_VALUE = "auth";
    public static final String NONCE_KEY = "nonce";
    public static final String NONCE_VALUE = "dcd98b7102dd2f0e8b11d0f600bfb0c093";
    public static final String OPAQUE_KEY = "opaque";
    public static final String OPAQUE_VALUE = "5ccc069c403ebaf9f0171e9517f40e41";
    static final String SSE_HEADER_KEY = "x-sse-header-key";

	public static final String SERVER_SENT_EVENTS = "text/event-stream";

    @Context
    HttpHeaders headers;

    @GET
    public Response getHeadersBack() {
        return echoHeaders();
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("/digest")
    public Response getHeadersBackFromDigestAuthentication() {
        // As the Digest authentication require some complex steps to work we'll simulate the process
        // https://en.wikipedia.org/wiki/Digest_access_authentication#Example_with_explanation
        if (headers.getRequestHeader("authorization") == null) {
            String authenticationRequired = "Digest " + REALM_KEY + "=\"" + REALM_VALUE + "\", " + QOP_KEY + "=\"" + QOP_VALUE + "\", " + NONCE_KEY + "=\"" + NONCE_VALUE + "\", " + OPAQUE_KEY + "=\"" + OPAQUE_VALUE + "\"";
            return Response.status(Response.Status.UNAUTHORIZED)
                    .header("WWW-Authenticate", authenticationRequired)
                    .build();
        } else {
            return echoHeaders();
        }
    }

    @GET
    @Path("/events")
    @Produces(SERVER_SENT_EVENTS)
    public void getServerSentEvents(@Context SseEventSink eventSink, @Context Sse sse) {
        OutboundSseEvent event = sse.newEventBuilder()
                .name("echo-headers")
                .data(String.class, headers.getRequestHeader(AddHeaderOnRequestFilter.FILTER_HEADER_KEY).get(0))
                .build();
        eventSink.send(event);
    }

    private Response echoHeaders() {
        Response.ResponseBuilder responseBuilder = Response.noContent();

        headers.getRequestHeaders()
                .forEach((k, v) -> {
                    v.forEach(value -> responseBuilder.header(k, value));
                });

        return responseBuilder.build();
    }
}