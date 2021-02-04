package database;

import exceptions.BookSessionNotFoundException;
import entities.BookSession;

public interface IBookSessionDao {
    BookSession createBookSession(BookSession bookSession) throws IllegalArgumentException;

    BookSession getBookSessionById(String bookSessionId) throws BookSessionNotFoundException;

    void addUserToBookSession(String bookSessionId, String name) throws BookSessionNotFoundException;
}
