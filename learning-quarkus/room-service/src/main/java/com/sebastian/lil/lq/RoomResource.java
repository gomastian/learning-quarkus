package com.sebastian.lil.lq;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Path("/rooms")
public class RoomResource {

    @Inject
    RoomService roomService;
    @Inject
    MeterRegistry meterRegistry;

    private final static Logger LOG = Logger.getLogger(RoomResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms(@QueryParam("bedInfo")String bedInfo) {
        Timer timer = Timer.builder("roomservice").tag("method", "getAllRooms").register(meterRegistry);
        long start = System.nanoTime();
        if(!StringUtils.isAllEmpty(bedInfo)){
            return roomService.getRoomsByBedInfo(bedInfo);
        }
        LOG.info("exiting getAllRooms");
        timer.record(System.nanoTime()-start, TimeUnit.NANOSECONDS);
        return roomService.getAllRooms();
    }

    @GET
    @Path("/{roomNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoom(@PathParam("roomNumber")String roomNumber){
        return roomService.getRoomByNumber(roomNumber);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Room createRoom(@RequestBody Room room){
        return roomService.addRoom(room);
    }
}
