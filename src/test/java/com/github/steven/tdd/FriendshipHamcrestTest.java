package com.github.steven.tdd;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * FriendshipHamcrestTest.
 *
 * @author shidingfeng
 */
public class FriendshipHamcrestTest {
    @Test
    public void test1() {
        Friendships friendships = new Friendships();

        assertThat(friendships.getFriendsList("Joe"), empty());
    }

    @Test
    public void test2() {
        Friendships friendships = new Friendships();
        friendships.makeFriends("Joe","Audrey");

        assertTrue(friendships.areFriends("Joe", "Audrey"));
        assertTrue(friendships.areFriends("Audrey", "Joe"));

        assertThat(friendships.getFriendsList("Joe"), hasSize(1));
        assertThat("Audrey", isIn(friendships.getFriendsList("Joe")));

        assertThat(friendships.getFriendsList("Audrey"), hasSize(1));
        assertThat("Joe", isIn(friendships.getFriendsList("Audrey")));
    }

    @Test(timeout = 10)
    public void test3() {
        Friendships friendships = new Friendships();
        friendships.makeFriends("Joe", "Audrey");
        friendships.makeFriends("Joe", "Peter");
        friendships.makeFriends("Joe", "Michael");
        friendships.makeFriends("Joe", "Britney");
        friendships.makeFriends("Joe", "Paul");

        assertThat(friendships.getFriendsList("Joe"), hasSize(5));
        assertTrue(friendships.areFriends("Joe", "Paul"));
        assertFalse(friendships.areFriends("Joe", "Ralph"));

        assertThat(friendships.getFriendsList("Joe"), containsInAnyOrder("Audrey", "Peter", "Michael", "Britney", "Paul"));
    }

    @Test
    public void test4() {
        Friendships friendships = new Friendships();
        friendships.makeFriends("Joe", "Audrey");
        friendships.makeFriends("Joe", "Audrey");

        assertThat(friendships.getFriendsList("Joe"), hasSize(1));
    }

    @Test
    public void test5() {
        Friendships friendships = new Friendships();
        assertFalse(friendships.areFriends("Joe", "Audrey"));
    }
}
