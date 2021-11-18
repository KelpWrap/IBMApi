package com.whiteboard.whiteboard.dbAdapter;
import java.io.File;

import org.springframework.stereotype.Repository;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetTransaction;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

@Repository
public class DbAdapter {
    protected static final String DB_NAME = "whiteboard.db";
    protected static final String TABLE_POSTS_NAME = "posts";
    protected static final String TABLE_USERS_NAME = "users";
    protected static final String TABLE_METADATA_NAME = "metadata";

    protected static final String POST_INDEX_FIELD = "id";
    protected static final String POST_USER_ID_FIELD = "user_id";
    protected static final String POST_BODY_FIELD = "body";

    protected static final String USER_INDEX_FIELD = "id";
    protected static final String USER_NAME_FIELD = "username";
    protected static final String USER_ALIAS_FIELD = "alias";
    protected static final String USER_HASHED_PASSWORD_FIELD = "hashed_password";
    protected static final String USER_TYPE_FIELD = "type";
    
    protected static final String METADATA_USERID_FIELD = "user_id";
    protected static final String METADATA_POSTID_FIELD = "post_id";
    protected static final String METADATA_NAME_FIELD = "name";
    protected static final String METADATA_VALUE_FIELD = "value";

    protected SqlJetDb db;

    public DbAdapter(){
    }

    public void initDb() throws SqlJetException {
        File dbFile = new File(DB_NAME);
        if (!dbFile.exists()){
            createDb(dbFile);
        }
    }
    
    private void createDb(File dbFile) throws SqlJetException{
        db = SqlJetDb.open(dbFile, true);
        db.getOptions().setAutovacuum(true);
        db.runTransaction(new ISqlJetTransaction() {
            public Object run(SqlJetDb db) throws SqlJetException {
                db.getOptions().setUserVersion(1);
                return true;
            }
        }, SqlJetTransactionMode.WRITE);
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {            
            String createTablePostsQuery = "CREATE TABLE " + TABLE_POSTS_NAME + " (" + POST_INDEX_FIELD + " INT IDENTITY NOT NULL PRIMARY KEY , " 
                + POST_USER_ID_FIELD + " TEXT NOT NULL, " + POST_BODY_FIELD + " TEXT NOT NULL)";
            String createTableUsersQuery = "CREATE TABLE " + TABLE_USERS_NAME + " (" + USER_INDEX_FIELD + " INT IDENTITY NOT NULL PRIMARY KEY , " 
                + USER_NAME_FIELD + " TEXT NOT NULL, " + USER_ALIAS_FIELD + " TEXT NOT NULL , " + USER_HASHED_PASSWORD_FIELD + " TEXT NOT NULL , " 
                + USER_TYPE_FIELD + " INTEGER NOT NULL)";
            String createTableMetadataQuery =  "CREATE TABLE " + TABLE_METADATA_NAME + " (" + METADATA_POSTID_FIELD + " INT , " 
                + METADATA_USERID_FIELD + " INT , " + METADATA_NAME_FIELD + " TEXT NOT NULL , " + METADATA_VALUE_FIELD + " TEXT NOT NULL)"; 
            System.out.println();
            System.out.println(">DB schema queries:");
            System.out.println();
            System.out.println(createTablePostsQuery);
            System.out.println(createTableUsersQuery);
            System.out.println(createTableMetadataQuery);
            
            db.createTable(createTablePostsQuery);
            db.createTable(createTableUsersQuery);
            db.createTable(createTableMetadataQuery);
            System.out.println(">Database Created");
        } finally {
            db.commit();
            db.close();
        }
    }

    protected static void main(String[] args) {
        DbAdapter db = new DbAdapter();
        try {
            db.initDb();
        } catch (Exception e){

        }
        
    } 
}
