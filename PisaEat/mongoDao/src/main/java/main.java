import Dao.MongoDbConnector;
import Dao.TableDao;
import Entities.Table;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class main {
    public static void main(String[] args) {
        tryTableDao();

    }

    private static void tryTableDao() {
        TableDao tableDao = new TableDao(MongoDbConnector.getInstance());

        Table t1 = new Table("1", 8,new ObjectId());
        Table t2 = new Table("2", 8,new ObjectId());
        Table t3 = new Table("3", 8,new ObjectId());

        MongoCollection<Table> tableCollection = tableDao.getTableCollection();

        // insert 2 tables
        tableCollection.insertOne(t1);
        tableDao.createTable(t2);
        tableCollection.insertOne(t3);
        System.out.println("Inserted:");
        System.out.println(t1);
        System.out.println(t2);

        // using findOneAndUpdate to modify a sessionId
        t2 = tableCollection.
                findOneAndUpdate(
                    and(
                            eq("_id", t2.getId()),
                            eq("bookSessionId", t2.getBookSessionId())
                    )
                ,set("bookSessionId", null));
        System.out.println("Modified T2 document");
        System.out.println(t2);

        // using findOneAndUpdate to modify a wrong sessionId
        Table t4 = tableCollection.
                findOneAndUpdate(
                        and(
                                eq("_id", t3.getId()),
                                eq("bookSessionId", t1.getBookSessionId())
                        )
                        ,set("bookSessionId", null));
        System.out.println("trying to modify T3 document with wrong session");
        System.out.println(t4);
    }
}
