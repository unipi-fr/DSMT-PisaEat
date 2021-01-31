package it.unipi.dsmt.RestAPI;

import Dao.MongoDbConnector;
import Dao.TableDao;
import database.ITableDao;
import entities.Table;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/table")
public class TableResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public void createTable(Table table) {
        ITableDao iTableDao = new TableDao(MongoDbConnector.getInstance());

        try {
            iTableDao.createTable(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}