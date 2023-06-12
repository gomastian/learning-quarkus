package com.sebastian.lil.lq;

import javax.inject.Inject;


import java.util.ArrayList;
import java.util.List;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.quarkus.runtime.api.session.QuarkusCqlSession;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Runner implements QuarkusApplication {
    @Inject
    QuarkusCqlSession cqlSession;

    @ConfigProperty(name="application.bed-info")
    private String bedInfo;

    private static final Logger LOG = Logger.getLogger(Runner.class);
    @Override
    public int run(String... args) throws Exception {
        LOG.debug("starting application");
        String cql = "select room_number, bed_info, name from lil.rooms where bed_info=?";
        List<Room> rooms = new ArrayList<>();
        PreparedStatement ps = cqlSession.prepare(cql);
        BoundStatement bs = ps.bind(bedInfo);
        ResultSet rs = cqlSession.execute(bs);
        for(Row row:rs){
            rooms.add(new Room(row.getString("room_number"), row.getString("bed_info"),
                    row.getString("name")));
        }
        rooms.forEach(System.out::println);
        LOG.debug("closing application");
        return 0;
    }
}
