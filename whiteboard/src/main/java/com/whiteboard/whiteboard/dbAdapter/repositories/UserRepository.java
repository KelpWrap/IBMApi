package com.whiteboard.whiteboard.dbAdapter.repositories;


import java.util.ArrayList;
import java.util.HashMap;
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
    public List<CatalogUser> getObjectsById(int id) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.READ_ONLY);
        ISqlJetTable userTable = super.db.getTable(USERS_TABLE_NAME);
        List<CatalogUser> userList = new ArrayList<CatalogUser>();
        try {
            ISqlJetCursor userCursor = userTable.lookup(USER_INDEX, id);
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
            ISqlJetCursor userCursor = userTable.open();
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
                user.setUserId(Math.toIntExact(userCursor.getRowId()));
                user.setUserAlias(userCursor.getString(USER_ALIAS_FIELD));
                user.setUserName(userCursor.getString(USER_NAME_FIELD));
                user.setUserHashedPassword(userCursor.getString(USER_HASHED_PASSWORD_FIELD));
                user.setUserType(userCursor.getString(USER_TYPE_FIELD));
                userList.add(user);
            } while (userCursor.next());
        }
        userCursor.close();
    }

    
    public int addObject(CatalogUser user) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        int id;
        try {
            ISqlJetTable userTable = db.getTable(USERS_TABLE_NAME);
            Map<String, Object> values = new HashMap<>();
            values.put(USER_NAME_FIELD, user.getUsername());
            values.put(USER_ALIAS_FIELD, user.getUserAlias());
            values.put(USER_HASHED_PASSWORD_FIELD, user.getUserHashedPassword());
            values.put(USER_TYPE_FIELD, user.getUserType());
            id = Math.toIntExact(userTable.insertByFieldNames(values));
        }   
        finally {
            db.commit();
            db.close();
        } 
        return id;
    }

    
    public void deleteObject(CatalogUser user) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetTable userTable = db.getTable(USERS_TABLE_NAME);
            ISqlJetCursor deleteCursor = userTable.lookup(USER_INDEX, user.getUserId());
            while (!deleteCursor.eof()) {
                deleteCursor.delete();
            }
            deleteCursor.close();
        }   
        finally {
            db.commit();
            db.close();
        }   
    }

    public void clearTable() throws SqlJetException{
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetCursor deleteCursor = db.getTable(USERS_TABLE_NAME).open();
            while (!deleteCursor.eof()) {
                deleteCursor.delete();
            }
            deleteCursor.close();
        }
        finally {
            db.commit();
            db.close();
        }
    }

    
    public void replaceObject(CatalogUser user, CatalogUser updatedUser) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetTable userTable = db.getTable(USERS_TABLE_NAME);
            ISqlJetCursor cursor = userTable.lookup(USER_INDEX, user.getUserId());
            do {
                cursor.update(updatedUser.getUserId(), updatedUser.getUsername(), updatedUser.getUserAlias(), updatedUser.getUserHashedPassword(), updatedUser.getUserType());
            } while (cursor.next());
        } finally {
            db.commit();
            db.close();
        }

    }  
    
}
