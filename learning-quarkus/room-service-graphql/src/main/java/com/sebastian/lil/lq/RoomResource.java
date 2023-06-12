package com.sebastian.lil.lq;

import javax.inject.Inject;

import java.util.List;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class RoomResource {
    @Inject
    RoomService roomService;

    @Query("allRooms")
    @Description("Gets all rooms for the hotel")
    public List<Room> getAllRooms(){
       return roomService.getAllRooms();
    }

}