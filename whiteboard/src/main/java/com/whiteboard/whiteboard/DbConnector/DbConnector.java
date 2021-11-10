package com.whiteboard.whiteboard.DbConnector;
import java.io.File;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.schema.ISqlJetIndexDef;
import org.tmatesoft.sqljet.core.schema.ISqlJetTableDef;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.ISqlJetTransaction;
import org.tmatesoft.sqljet.core.table.SqlJetDb;


public class DbConnector {
    private static final String DB_NAME = "whiteboard.db";
    private static final String TABLE_POSTS_NAME = "posts";
    private static final String TABLE_USERS_NAME = "users";
    private static final String TABLE_METADATA_NAME = "metadata";

    private static final String POST_INDEX_FIELD = "id";
    private static final String POST_USER_ID_FIELD = "user_id";
    private static final String POST_BODY_FIELD = "body";

    private static final String USER_INDEX_FIELD = "id";
    private static final String USER_NAME_FIELD = "username";
    private static final String USER_ALIAS_FIELD = "alias";
    private static final String USER_HASHED_PASSWORD_FIELD = "hashed_password";
    private static final String USER_TYPE_FIELD = "type";
    
    private static final String METADATA_USERID_FIELD = "user_id";
    private static final String METADATA_POSTID_FIELD = "post_id";
    private static final String METADATA_NAME_FIELD = "name";
    private static final String METADATA_VALUE_FIELD = "value";

    private SqlJetDb db;

    public DbConnector(){
    }

    public void initDb() throws SqlJetException {
        File dbFile = new File(DB_NAME);
        if (!dbFile.exists()){
            createDb(dbFile);
        }
        else{
            db = SqlJetDb.open(dbFile, true);
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
        }
    }

    public static void main(String[] args) {
        DbConnector db = new DbConnector();
        try {
            db.initDb();
        } catch (Exception e){

        }
        
    }



    
    
    
}
