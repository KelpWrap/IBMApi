package com.whiteboard.whiteboard.dbAdapter.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whiteboard.whiteboard.dbAdapter.DbAdapter;
import com.whiteboard.whiteboard.elements.CatalogPost;

import org.springframework.stereotype.Repository;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;

@Repository
public class PostRepository extends DbAdapter{
    
    
    public List<CatalogPost> getObjectsById(int id) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.READ_ONLY);
        ISqlJetTable postTable = super.db.getTable(POSTS_TABLE_NAME);
        List<CatalogPost> postList = new ArrayList<CatalogPost>();
        try {
            ISqlJetCursor postCursor = postTable.lookup(POST_INDEX, id);
            getPostsFromCursor(postList, postCursor);
        } catch (Exception e) {
            System.out.println("oops");
        }finally {
            db.commit();
            db.close();
        }
        return postList;
    }

    public List<CatalogPost> getObjects() throws SqlJetException{
        db.open();
        db.beginTransaction(SqlJetTransactionMode.READ_ONLY);
        ISqlJetTable postTable = super.db.getTable(POSTS_TABLE_NAME);
        List<CatalogPost> postList = new ArrayList<CatalogPost>();
        try {
            ISqlJetCursor postCursor = postTable.open();
            getPostsFromCursor(postList, postCursor);
        } finally {
            db.commit();
            db.close();
        }

        return postList;
    }

    private void getPostsFromCursor(List<CatalogPost> postList, ISqlJetCursor postCursor) throws SqlJetException {
        if (!postCursor.eof()) {
            do {
                CatalogPost post = new CatalogPost();
                post.setPostId(Math.toIntExact(postCursor.getRowId()));
                post.setPostBody(postCursor.getString(POST_BODY_FIELD));
                post.setPostUserid(postCursor.getString(POST_USER_ID_FIELD));
                postList.add(post);
            } while (postCursor.next());
        }
        postCursor.close();
    }

    
    public int addObject(CatalogPost post) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        int id;
        try {
            ISqlJetTable postTable = db.getTable(POSTS_TABLE_NAME);
            Map<String, Object> values = new HashMap<>();
            values.put(POST_USER_ID_FIELD, post.getPostUserid());
            values.put(POST_BODY_FIELD, post.getPostBody());
            id = Math.toIntExact(postTable.insertByFieldNames(values));
        }   
        finally {
            db.commit();
            db.close();
        }
        return id; 
    }

    
    public void deleteObject(CatalogPost post) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetTable postTable = db.getTable(POSTS_TABLE_NAME);
            ISqlJetCursor deleteCursor = postTable.lookup(POST_INDEX, post.getPostId());
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
            ISqlJetCursor deleteCursor = db.getTable(POSTS_TABLE_NAME).open();
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

    
    public void replaceObject(CatalogPost post, CatalogPost updatedPost) throws SqlJetException {
        db.open();
        db.beginTransaction(SqlJetTransactionMode.WRITE);
        try {
            ISqlJetTable postTable = db.getTable(POSTS_TABLE_NAME);
            ISqlJetCursor cursor = postTable.lookup(POST_INDEX, post.getPostId());
            do {
                cursor.update(updatedPost.getPostId(), updatedPost.getPostUserid(), updatedPost.getPostBody());
            } while (cursor.next());
        } finally {
            db.commit();
            db.close();
        }
    }  
}
