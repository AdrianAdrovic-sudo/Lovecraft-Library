package org.lovecraftlibrary.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.lovecraftlibrary.client.IpifyClient;
import org.lovecraftlibrary.client.TimeApiClient;
import org.lovecraftlibrary.model.Member;
import org.lovecraftlibrary.model.TimeZone;

@ApplicationScoped
public class TimezoneService {

    @Inject
    EntityManager em;

    @Inject
    @RestClient
    IpifyClient ipifyClient;

    @Inject
    @RestClient
    TimeApiClient timeApiClient;

    @Transactional
    public Member assignTimezoneToMember(Long userId) {
        Member member = em.find(Member.class, userId);

        if (member == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("Member with id " + userId + " not found")
                            .build());
        }

        String ip = ipifyClient.getPublicIp();
        TimeZone timeZone = timeApiClient.getTimeZoneByIp(ip);
        timeZone.setMember(member);

        em.persist(timeZone);

        member.getTimeZones().add(timeZone);
        member.getTimeZones().size();

        return member;
    }
}