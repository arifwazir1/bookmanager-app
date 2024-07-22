package com.example.bookmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class BookDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "book.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKS = "books";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_DESCRIPTION = "description";

    public BookDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_AUTHOR + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

    public boolean addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, book.getTitle());
        values.put(COLUMN_AUTHOR, book.getAuthor());
        values.put(COLUMN_DESCRIPTION, book.getDescription());

        long result = db.insert(TABLE_BOOKS, null, values);
        db.close();
        return result != -1;
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BOOKS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
                int authorIndex = cursor.getColumnIndex(COLUMN_AUTHOR);
                int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);

                if (idIndex != -1 && titleIndex != -1 && authorIndex != -1 && descriptionIndex != -1) {
                    int id = cursor.getInt(idIndex);
                    String title = cursor.getString(titleIndex);
                    String author = cursor.getString(authorIndex);
                    String description = cursor.getString(descriptionIndex);

                    Book book = new Book(id, title, author, description);
                    bookList.add(book);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookList;
    }
}


