package database.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDbConnector {
    private static volatile MongoDbConnector instance;
    private static final String password = "pisaeat";
    private static final String projectName = "pisaeat";
    private static final String dbName = "pisaeat";
    /*private static String CONNECTION_STRING =
            String.format(
                    "mongodb+srv://pisaeat:%1$s@cluster0.mxzsb.mongodb.net/%2$s?retryWrites=true&w=majority",
                    password,
                    projectName);
            ;*/
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private final MongoClient client;
    private final MongoDatabase database;
    private final CodecRegistry pojoCodecRegistry;

    private MongoDbConnector() {
        // regisrty is for POJO
        this.pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(
                        PojoCodecProvider.builder().automatic(true).build()
                )
        );


        this.client = MongoClients.create(CONNECTION_STRING);
        this.database = this.client.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
    }

    public static MongoDbConnector getInstance() {
        if (instance == null) {
            synchronized (MongoDbConnector.class) {
                if (instance == null) {
                    instance = new MongoDbConnector();
                }
            }
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
