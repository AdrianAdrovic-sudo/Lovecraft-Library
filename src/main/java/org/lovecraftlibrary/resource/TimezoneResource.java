package org.lovecraftlibrary.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lovecraftlibrary.model.Member;
import org.lovecraftlibrary.service.TimezoneService;

@Path("/getTimezoneByIP")
public class TimezoneResource {

    @Inject
    TimezoneService timezoneService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimezoneByIP(@QueryParam("userId") Long userId) {
        Member member = timezoneService.assignTimezoneToMember(userId);
        return Response.ok(member).build();
    }
}