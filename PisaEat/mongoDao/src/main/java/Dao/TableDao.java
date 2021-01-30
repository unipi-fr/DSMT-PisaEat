package Dao;

import Entities.Table;
import com.mongodb.client.MongoCollection;

public class TableDao {

    private MongoDbConnector dbConnector;
    private MongoCollection<Table> collection;

    public TableDao(MongoDbConnector dbConnector) {
        this.dbConnector = dbConnector;
        this.collection = this.dbConnector.getDatabase().getCollection("table", Table.class);
    }

    public MongoCollection<Table> getTableCollection(){
        return this.collection;
    }

    public void createTable(Table table){
        collection.insertOne(table);
    }
}
