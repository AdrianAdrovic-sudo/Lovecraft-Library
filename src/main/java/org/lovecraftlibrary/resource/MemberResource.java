package org.lovecraftlibrary.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lovecraftlibrary.model.Loan;
import org.lovecraftlibrary.model.Member;
import org.lovecraftlibrary.model.MemberBook;
import org.lovecraftlibrary.service.MemberService;
import java.util.List;

@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberResource {

    @Inject
    MemberService memberService;

    @POST
    public Response create(Member member) {
        memberService.save(member);
        return Response.status(Response.Status.CREATED).entity(member).build();
    }

    @GET
    public List<Member> getAll() {
        return memberService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Member member = memberService.findById(id);
        if (member == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(member).build();
    }

    @GET
    @Path("/search")
    public List<Member> searchByName(@QueryParam("name") String name) {
        return memberService.findByName(name);
    }

    @GET
    @Path("/{id}/loans")
    public List<Loan> getLoansByMember(@PathParam("id") Long id) {
        return memberService.findLoansByMember(id);
    }

    @GET
    @Path("/{id}/wishlist")
    public List<MemberBook> getWishlistByMember(@PathParam("id") Long id) {
        return memberService.findWishlistByMember(id);
    }
}