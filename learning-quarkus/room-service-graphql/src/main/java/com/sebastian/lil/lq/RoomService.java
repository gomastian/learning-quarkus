package com.sebastian.lil.lq;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

@ApplicationScoped
public class RoomService {
    @Inject
    CqlSession cqlSession;

    private static final String getAllCql = "select room_number, bed_info, name from lil.rooms";

    public List<Room> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        ResultSet rs = cqlSession.execute(getAllCql);
        for(Row row:rs){
            rooms.add(new Room(row.getString("room_number"), row.getString("bed_info"),
                    row.getString("name")));
        }
        return rooms;
    }
}
