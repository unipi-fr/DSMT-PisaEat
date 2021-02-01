package database;

import entities.BookSession;

public interface IBookSessionDao {
    BookSession createBookSession(BookSession bookSession) throws IllegalArgumentException;
}
