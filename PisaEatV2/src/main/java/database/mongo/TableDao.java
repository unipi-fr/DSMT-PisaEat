package database.mongo;

import com.mongodb.client.MongoCollection;
import database.ITableDao;
import database.mongo.entities.MongoTable;
import entities.Table;
import org.bson.types.ObjectId;

public class TableDao implements ITableDao {

    private final MongoCollection<MongoTable> collection;

    public TableDao(MongoDbConnector dbConnector) {
        this.collection = dbConnector.getDatabase().getCollection("table", MongoTable.class);
    }

    public Table createTable(Table table) {
        if (table == null) {
            throw new IllegalArgumentException();
        }

        MongoTable mongoTable = tableToMongoTable(table);

        collection.insertOne(mongoTable);

        return mongoTableToTable(mongoTable);
    }

    /*@Override
    public Iterable<Table> getTables() {
        return null;
    }*/

    protected MongoTable tableToMongoTable(Table table) {
        if (table == null) {
            return null;
        }

        ObjectId id = null;
        String name = table.getName();
        int numberOfSeat = table.getNumberOfSeat();
        ObjectId bookSessionId = null;

        if (table.getId() != null && !table.getId().trim().isEmpty()) {
            id = new ObjectId(table.getId());
        }

        if (table.getBookSessionId() != null && !table.getBookSessionId().trim().isEmpty()) {
            bookSessionId = new ObjectId(table.getBookSessionId());
        }

        return new MongoTable(id, name, numberOfSeat, bookSessionId);
    }

    protected Table mongoTableToTable(MongoTable mongoTable) {
        if (mongoTable == null) {
            return null;
        }

        String id = null;
        String name = mongoTable.getName();
        int numberOfSeat = mongoTable.getNumberOfSeat();
        String bookSessionId = null;

        if (mongoTable.getId() != null) {
            id = mongoTable.getId().toHexString();
        }

        if (mongoTable.getBookSessionId() != null) {
            bookSessionId = mongoTable.getBookSessionId().toHexString();
        }

        return new Table(id, name, numberOfSeat, bookSessionId);
    }
}
