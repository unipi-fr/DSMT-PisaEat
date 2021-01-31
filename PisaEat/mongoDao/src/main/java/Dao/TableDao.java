package Dao;

import com.mongodb.client.MongoCollection;
import com.sun.deploy.util.StringUtils;
import entities.MongoTable;
import entities.Table;
import org.bson.types.ObjectId;

import java.util.Optional;

public class TableDao {

    private MongoDbConnector dbConnector;
    private MongoCollection<MongoTable> collection;

    public TableDao(MongoDbConnector dbConnector) {
        this.dbConnector = dbConnector;
        this.collection = this.dbConnector.getDatabase().getCollection("table", MongoTable.class);
    }

    public void createTable(Table table){
        Optional<MongoTable> optMongoTable = tableToMongoTable(table);

        if(!optMongoTable.isPresent()) {
            return;
        }
        MongoTable mongoTable = optMongoTable.get();

        collection.insertOne(mongoTable);


    }

    protected Optional<MongoTable> tableToMongoTable(Table table) {
        if(table == null){
            return Optional.empty();
        }
        ObjectId id = null;
        String name = table.getName();
        int numberOfSeat = table.getNumberOfSeat();
        ObjectId bookSessionId = null;

        if(table.getId() != null && !table.getId().trim().isEmpty()){
            id = new ObjectId(table.getId());
        }

        if(table.getBookSessionId() != null && !table.getBookSessionId().trim().isEmpty()){
            bookSessionId = new ObjectId(table.getId());
        }

        MongoTable mongoTable = new MongoTable(id, name, numberOfSeat, bookSessionId);
        return Optional.of(mongoTable);
    }
}
