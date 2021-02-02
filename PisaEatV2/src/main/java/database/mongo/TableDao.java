package database.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import database.ITableDao;
import database.exceptions.TableNotFoundException;
import database.mongo.entities.MongoTable;
import entities.Table;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

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

    @Override
    public Collection<Table> getTables() {
        List<Table> tables = new ArrayList<Table>();

        FindIterable<MongoTable> findIterable = collection.find();
        for (MongoTable mongoTable : findIterable) {
            tables.add(mongoTableToTable(mongoTable));
        }

        return tables;
    }

    @Override
    public Table getTableById(String tableId) throws TableNotFoundException {
        ObjectId id = null;

        try {
            id = new ObjectId(tableId);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        MongoTable mongoTable = collection.find(
                eq("_id", id)
        ).first();

        if (mongoTable == null)
            throw new TableNotFoundException(tableId);

        return mongoTableToTable(mongoTable);
    }

    @Override
    public Table updateTable(Table table) throws TableNotFoundException {
        MongoTable mongoTable = tableToMongoTable(table);

        UpdateResult result = collection.updateOne(
                eq("_id", mongoTable.getId()),
                combine(
                        set("name", mongoTable.getName()),
                        set("numberOfSeat", mongoTable.getNumberOfSeat()),
                        set("bookSessionId", mongoTable.getBookSessionId())
                )
        );

        long matchedCount = result.getMatchedCount();

        if (matchedCount == 0) {
            throw new TableNotFoundException(table.getId());
        }

        return table;
    }

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
