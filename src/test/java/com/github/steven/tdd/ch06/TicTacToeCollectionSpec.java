package com.github.steven.tdd.ch06;

import java.net.UnknownHostException;

import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * TicTacToeSpec.
 *
 * @author shidingfeng
 */
public class TicTacToeCollectionSpec {
    TicTacToeCollection collection;
    @Before
    public void before() throws UnknownHostException {
        collection = spy(new TicTacToeCollection());
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
        TicTacToeBean bean =
                new TicTacToeBean(3, 2, 1, 'Y');
        MongoCollection mongoCollection =
                mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection)
                .getMongoCollection();
        collection.saveMove(bean);
        verify(mongoCollection, times(1)).save(bean);
    }

    @Test
    public void whenSaveMoveThenReturnTrue() {
        TicTacToeBean bean =
                new TicTacToeBean(3, 2, 1, 'Y');
        MongoCollection mongoCollection =
                mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection)
                .getMongoCollection();
        assertTrue(collection.saveMove(bean));
    }
}
