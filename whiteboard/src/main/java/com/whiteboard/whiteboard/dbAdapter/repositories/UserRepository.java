package com.whiteboard.whiteboard.dbAdapter.repositories;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.whiteboard.whiteboard.dbAdapter.DbAdapter;
import com.whiteboard.whiteboard.elements.CatalogUser;

import org.springframework.stereotype.Repository;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
@Repository

public class UserRepository extends DbAdapter{
    public List<CatalogUser> getObjectsById(String id) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.READ_ONLY);
        ISqlJetTable userTable = super.db.getTable(USERS_TABLE_NAME);
        List<CatalogUser> userList = new ArrayList<CatalogUser>();
        try {
            ISqlJetCursor userCursor = userTable.lookup(POST_INDEX_FIELD, id);
            getUsersFromCursor(userList, userCursor);
        } finally {
            db.commit();
            db.close();
        }
        
        return userList;
    }

    public List<CatalogUser> getObjects() throws SqlJetException{
        db.open();
        db.beginTransaction(SqlJetTransactionMode.READ_ONLY);
        ISqlJetTable userTable = super.db.getTable(USERS_TABLE_NAME);
        List<CatalogUser> userList = new ArrayList<CatalogUser>();
        try {
            ISqlJetCursor userCursor = userTable.order(POST_INDEX_FIELD);
            getUsersFromCursor(userList, userCursor);
        } finally {
            db.commit();
            db.close();
        }
        return userList;
    }

    private void getUsersFromCursor(List<CatalogUser> userList, ISqlJetCursor userCursor) throws SqlJetException {
        if (!userCursor.eof()) {
            do {
                CatalogUser user = new CatalogUser();
                user.setUserId(userCursor.getString(USER_INDEX_FIELD));
                userList.add(user);
            } while (userCursor.next());
        }
        userCursor.close();
    }

    
    public void addObject(CatalogUser user) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetTable userTable = db.getTable(USERS_TABLE_NAME);
        }   
        finally {
            db.commit();
        }
        db.close();
    }

    
    public void deleteObject(CatalogUser user) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetTable userTable = db.getTable(USERS_TABLE_NAME);
            ISqlJetCursor deleteCursor = userTable.lookup(POST_INDEX_FIELD, user.getPostId());
            while (!deleteCursor.eof()) {
                deleteCursor.delete();
            }
            deleteCursor.close();
        }   
        finally {
            db.commit();
        }
        db.close();
        
    }

    
    public void replaceObject(CatalogUser user, CatalogUser updatedPost) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetTable userTable = db.getTable(USERS_TABLE_NAME);
            ISqlJetCursor cursor = userTable.lookup(USER_INDEX_FIELD, user.getUserId());
            do {
                cursor.update(updatedPost.getPostId(), updatedPost.getPostUserid(), updatedPost.getPostBody());
            } while (cursor.next());
        } finally {
            db.commit();
        }
        db.close();
    }  
    
}
