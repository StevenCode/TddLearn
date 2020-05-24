package com.github.steven.tdd.ch06;

import java.net.UnknownHostException;

import com.mongodb.MongoException;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * TicTacToeSpec.
 *
 * @author shidingfeng
 */
public class TicTacToeCollectionSpec {
    TicTacToeCollection collection;
    MongoCollection mongoCollection;
    TicTacToeBean bean;
    @Before
    public void before() throws UnknownHostException {
        collection = spy(new TicTacToeCollection());
        mongoCollection = mock(MongoCollection.class);
        bean = new TicTacToeBean(3, 2, 1, 'Y');

    }
    @Test
    public void
    whenInstantiatedThenMongoHasDbNameTicTacToe() {
        assertEquals("tic-tac-toe",
                collection.getMongoCollection()
                        .getDBCollection().getDB()
                        .getName());
    }
    @Test
    public void whenInstantiatedThenMongoHasNameGame() {
        assertEquals("game",
                collection.getMongoCollection()
                        .getName());
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave() {
        doReturn(mongoCollection).when(collection)
                .getMongoCollection();
        collection.saveMove(bean);
        verify(mongoCollection, times(1)).save(bean);
    }

    @Test
    public void whenSaveMoveThenReturnTrue() {
        MongoCollection mongoCollection =
                mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection)
                .getMongoCollection();
        assertTrue(collection.saveMove(bean));
    }

    @Test
    public void givenExceptionWhenSaveMoveThenReturnFalse() {
        doThrow(new MongoException("Bla"))
                .when(mongoCollection)
                .save(any(TicTacToeBean.class));
        doReturn(mongoCollection).when(collection)
                .getMongoCollection();
        assertFalse(collection.saveMove(bean));
    }

    @Test
    public void whenDropThenInvokeMongoCollectionDrop() {
        doReturn(mongoCollection)
                .when(collection)
                .getMongoCollection();
        collection.drop();
        verify(mongoCollection).drop();
    }


    @Test
    public void whenDropThenReturnTrue() {
        doReturn(mongoCollection).when(collection)
                .getMongoCollection();
        assertTrue(collection.drop());
    }

    @Test
    public void givenExceptionWhenDropThenReturnFalse() {
        doThrow(new MongoException("Bla"))
                .when(mongoCollection)
                .drop();
        doReturn(mongoCollection).when(collection)
                .getMongoCollection();
        assertFalse(collection.drop());
    }

}
