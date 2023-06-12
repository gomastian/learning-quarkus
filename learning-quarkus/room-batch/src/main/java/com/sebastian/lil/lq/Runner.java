package com.sebastian.lil.lq;

import javax.inject.Inject;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Runner implements QuarkusApplication {

    @Inject
    @RestClient
    RoomServiceRest roomService;

    @Override
    public int run(String... args) throws Exception {
        List<Room> rooms = roomService.getAllRooms();
        rooms.forEach(System.out::println);
        return 0;
    }
}
