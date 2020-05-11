package com.github.steven.tdd.ch02;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.github.steven.tdd.ch02.FriendsCollection;
import com.github.steven.tdd.ch02.FriendshipsMongo;
import com.github.steven.tdd.ch02.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * FriendshipsMongoAssertJTest.
 *
 * @author shidingfeng
 */
@RunWith(MockitoJUnitRunner.class)
public class FriendshipsMongoAssertJTest {

    @InjectMocks
    FriendshipsMongo friendships;

    @Mock
    FriendsCollection friends;

    @Test
    public void mockingWorksAsExpected() {
        Person joe = new Person("Joe");
        doReturn(joe).when(friends).findByName("Joe");
        assertThat(friends.findByName("Joe")).isEqualTo(joe);
    }

    @Test
    public void joeHas5Friends() {
        List<String> expected = Arrays.asList(
                new String[]{"Audrey", "Peter", "Michael", "Britney", "Paul"}
        );
        Person joe = spy(new Person("Joe"));
        doReturn(joe).when(friends).findByName("Joe");
        doReturn(expected).when(joe).getFriends();
        assertThat(friendships.getFriendsList("Joe"))
                .hasSize(5)
                .containsOnly("Audrey", "Peter", "Michael", "Britney", "Paul");
    }

    @Test
    public void test1() {
        doReturn(new Person("Joe")).when(friends).findByName(anyString());

        assertThat(friendships.getFriendsList("Joe")).isEmpty();
    }

    @Test
    public void test2() {
        Person joe = new Person("Joe");
        doReturn(joe).when(friends).findByName("Joe");

        Person audrey = new Person("Audrey");
        doReturn(audrey).when(friends).findByName("Audrey");

        doNothing().when(friends).save(any(Person.class));


        friendships.makeFriends("Joe", "Audrey");

        assertThat(friendships.areFriends("Joe", "Audrey")).isTrue();
        assertThat(friendships.areFriends("Audrey", "Joe")).isTrue();

        assertThat(friendships.getFriendsList("Joe")).hasSize(1).contains("Audrey");

        assertThat(friendships.getFriendsList("Audrey")).hasSize(1).contains("Joe");
    }

    @Test(timeout = 500)
    public void test3() {
        Person joe = new Person("Joe");
        doReturn(joe).when(friends).findByName("Joe");

        doNothing().when(friends).save(any(Person.class));

        friendships.makeFriends("Joe", "Audrey");
        friendships.makeFriends("Joe", "Peter");
        friendships.makeFriends("Joe", "Michael");
        friendships.makeFriends("Joe", "Britney");
        friendships.makeFriends("Joe", "Paul");

        assertThat(friendships.areFriends("Joe", "Paul")).isTrue();
        assertThat(friendships.areFriends("Audrey", "Ralph")).isFalse();

        assertThat(friendships.getFriendsList("Joe")).containsOnly("Audrey", "Peter", "Michael", "Britney", "Paul");
    }

    @Test
    public void test4() {
        doReturn(null).when(friends).findByName("Joe");
        assertThat(friendships.getFriendsList("Joe")).isEmpty();
    }

    @Test
    public void test5() {
        Person joe = new Person("Joe");
        doReturn(joe).when(friends).findByName("Joe");
        assertThat(friendships.areFriends("Joe", "Audrey")).isFalse();
    }
}
