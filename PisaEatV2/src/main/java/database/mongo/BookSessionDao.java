package database.mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import database.IBookSessionDao;
import exceptions.BookSessionNotFoundException;
import database.mongo.entities.MongoBookSession;
import entities.BookSession;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

public class BookSessionDao implements IBookSessionDao {
    private final MongoCollection<MongoBookSession> collection;

    public BookSessionDao(MongoDbConnector dbConnector) {
        this.collection = dbConnector.getDatabase().getCollection("book_session", MongoBookSession.class);
    }

    @Override
    public BookSession createBookSession(BookSession bookSession) {
        if (bookSession == null) {
            throw new IllegalArgumentException();
        }

        MongoBookSession mongoBookSession = bookSessionToMongoBookSession(bookSession);

        collection.insertOne(mongoBookSession);

        return mongoBookSessionToBookSession(mongoBookSession);
    }

    @Override
    public BookSession getBookSessionById(String bookSessionId) throws BookSessionNotFoundException {
        ObjectId id = null;

        try {
            id = new ObjectId(bookSessionId);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        MongoBookSession mongoBookSession = collection.find(
                eq("_id", id)
        ).first();

        if (mongoBookSession == null) {
            throw new BookSessionNotFoundException(bookSessionId);
        }

        return mongoBookSessionToBookSession(mongoBookSession);
    }

    @Override
    public void addUserToBookSession(String bookSessionId, String name) throws BookSessionNotFoundException {
        ObjectId id = null;

        try {
            id = new ObjectId(bookSessionId);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        UpdateResult result  = collection.updateOne(
                eq("_id", id),
                push("users", name)
        );

        long matchedCount = result.getMatchedCount();

        if (matchedCount == 0) {
            throw new BookSessionNotFoundException(bookSessionId);
        }
    }

    protected MongoBookSession bookSessionToMongoBookSession(BookSession bookSession) {
        if (bookSession == null) {
            return null;
        }

        ObjectId id = null;
        String pin = bookSession.getPin();
        ArrayList<String> users = bookSession.getUsers();
        String booker = bookSession.getBookingName();

        if (bookSession.getId() != null && !bookSession.getId().trim().isEmpty()) {
            id = new ObjectId(bookSession.getId());
        }

        return new MongoBookSession(id, pin, users, booker);
    }

    protected BookSession mongoBookSessionToBookSession(MongoBookSession mongoBookSession) {
        if (mongoBookSession == null) {
            return null;
        }

        String id = null;
        String pin = mongoBookSession.getPin();
        ArrayList<String> users = mongoBookSession.getUsers();
        String booker = mongoBookSession.getBookingName();

        if (mongoBookSession.getId() != null) {
            id = mongoBookSession.getId().toHexString();
        }

        return new BookSession(id, pin, users, booker);
    }
}
