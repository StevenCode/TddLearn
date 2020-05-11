package com.github.steven.tdd.ch02;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

/**
 * FriendsCollection.
 *
 * @author shidingfeng
 */
public class FriendsCollection {
    private MongoCollection friends;

    public FriendsCollection() {
        try {
            DB db = new MongoClient().getDB("friendships");
            friends = new Jongo(db).getCollection("friends");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Person findByName(String name) {
        return friends.findOne("{_id: #}", name).as(Person.class);
    }

    public void save(Person person) {
        friends.save(person);
    }
}
