package com.github.steven.tdd.ch06;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

/**
 * TicTacToe.
 *
 * @author shidingfeng
 */
public class TicTacToeCollection {
    private MongoCollection mongoCollection;

    public TicTacToeCollection()
            throws UnknownHostException {
        DB db = new MongoClient().getDB("tic-tac-toe");
        mongoCollection =
                new Jongo(db).getCollection("game");
    }
    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public boolean saveMove(TicTacToeBean bean) {
        getMongoCollection().save(bean);
        return true;
    }
}
