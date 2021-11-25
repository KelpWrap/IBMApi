package com.whiteboard.whiteboard.dbAdapter;
import java.io.File;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.schema.ISqlJetIndexDef;
import org.tmatesoft.sqljet.core.schema.ISqlJetTableDef;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.ISqlJetTransaction;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

@Repository
public class DbAdapter {
    protected static final String DB_NAME = "whiteboardTest.db";
    protected static final String POSTS_TABLE_NAME = "posts";
    protected static final String USERS_TABLE_NAME = "users";
    protected static final String METADATA_TABLE_NAME = "metadata";

    protected static final String POST_INDEX_FIELD = "id";
    protected static final String POST_USER_ID_FIELD = "user_id";
    protected static final String POST_BODY_FIELD = "body";

    protected static final String POST_INDEX = "post_index";


    protected static final String USER_INDEX_FIELD = "id";
    protected static final String USER_NAME_FIELD = "username";
    protected static final String USER_ALIAS_FIELD = "alias";
    protected static final String USER_HASHED_PASSWORD_FIELD = "hashed_password";
    protected static final String USER_TYPE_FIELD = "type";

    protected static final String USER_NAME_PW_INDEX = "name_pw_index";
    
    protected static final String METADATA_USERID_FIELD = "user_id";
    protected static final String METADATA_POSTID_FIELD = "post_id";
    protected static final String METADATA_NAME_FIELD = "name";
    protected static final String METADATA_VALUE_FIELD = "value";

    protected SqlJetDb db;

    
    public DbAdapter(){
    }

    public SqlJetDb getDb(){
        return db;
    }

    @Autowired
    public void initDb() throws SqlJetException {
        File dbFile = new File(DB_NAME);
        if (!dbFile.exists()){
            createDb(dbFile);
        }
        db = SqlJetDb.open(dbFile, true);
        db.close();
    }

    public void deleteDb() throws SqlJetException {
        db.open();
        try {      
            Set<String> indices = db.getSchema().getIndexNames();
            Set<String> tables = db.getSchema().getTableNames();
            for (String tableName : tables) {
               ISqlJetTableDef tableDef = db.getSchema().getTable(tableName);
               Set<ISqlJetIndexDef> tableIndices = db.getSchema().getIndexes(tableName);
               for (ISqlJetIndexDef indexDef : tableIndices) {
                  if (!indexDef.isImplicit()) {
                    db.dropIndex(indexDef.getName());
                  }
               }
               db.dropTable(tableName);
             }
           } finally {
             db.commit();
             db.close();
           }
    }

    public void clearDb() throws SqlJetException{
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            Set<String> tables = db.getSchema().getTableNames();
            for (String tableName : tables) {
                ISqlJetTable table = db.getTable(tableName);
                ISqlJetCursor clearCursor = db.getTable(tableName).open();
                while (!clearCursor.eof()) {
                    clearCursor.delete();
                }
                clearCursor.close();
            }
        } finally {
            db.commit();
            db.close();
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
            String createTablePostsQuery = "CREATE TABLE " + POSTS_TABLE_NAME + " (" + POST_INDEX_FIELD + " INT IDENTITY NOT NULL PRIMARY KEY , " 
                + POST_USER_ID_FIELD + " TEXT NOT NULL, " + POST_BODY_FIELD + " TEXT NOT NULL)";
            String createTableUsersQuery = "CREATE TABLE " + USERS_TABLE_NAME + " (" + USER_INDEX_FIELD + " INT IDENTITY NOT NULL PRIMARY KEY , " 
                + USER_NAME_FIELD + " TEXT NOT NULL, " + USER_ALIAS_FIELD + " TEXT NOT NULL , " + USER_HASHED_PASSWORD_FIELD + " TEXT NOT NULL , " 
                + USER_TYPE_FIELD + " INTEGER NOT NULL)";
            String createTableMetadataQuery =  "CREATE TABLE " + METADATA_TABLE_NAME + " (" + METADATA_POSTID_FIELD + " INT , " 
                + METADATA_USERID_FIELD + " INT , " + METADATA_NAME_FIELD + " TEXT NOT NULL , " + METADATA_VALUE_FIELD + " TEXT NOT NULL)"; 
            String createIndexUserQuery = "CREATE INDEX " + USER_NAME_PW_INDEX + " ON " + USERS_TABLE_NAME + "(" + USER_NAME_FIELD + " , " + USER_HASHED_PASSWORD_FIELD + ")"; 
            String createIndexPostQuery = "CREATE INDEX " + POST_INDEX + " ON " + POSTS_TABLE_NAME + "(" + POST_INDEX_FIELD + ")"; 
            
            System.out.println();
            System.out.println(">DB schema queries:");
            System.out.println();
            System.out.println(createTablePostsQuery);
            System.out.println(createTableUsersQuery);
            System.out.println(createTableMetadataQuery);
            System.out.println(createIndexUserQuery);
            System.out.println(createIndexPostQuery);
            
            db.createTable(createTablePostsQuery);
            db.createTable(createTableUsersQuery);
            db.createTable(createTableMetadataQuery);
            db.createIndex(createIndexUserQuery);
            db.createIndex(createIndexPostQuery);
            System.out.println(">Database Created");
        } finally {
            db.commit();
            db.close();
        }
    }

}
