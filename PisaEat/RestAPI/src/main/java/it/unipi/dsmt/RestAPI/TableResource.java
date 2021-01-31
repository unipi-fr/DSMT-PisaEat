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
    @Produces(MediaType.APPLICATION_JSON)
    public Table createTable(Table table) {
        ITableDao iTableDao = new TableDao(MongoDbConnector.getInstance());

        try {
            Table newTable= iTableDao.createTable(table);
            return newTable;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}