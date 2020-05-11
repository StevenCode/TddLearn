package com.github.steven.tdd.ch02;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * PersonTest.
 *
 * @author shidingfeng
 */
public class PersonTest {
    @Test
    public void testGetFriends() throws Exception {
        Person p = new Person("Joe");
        assertThat(p.getFriends()).isEmpty();
    }

    @Test
    public void testAddFriend() throws Exception {
        Person p = new Person("Joe");
        p.addFriend("Ralph");
        p.addFriend("Kate");
        p.addFriend("Ralph");

        assertThat(p.getFriends()).hasSize(2).containsExactly("Ralph", "Kate");
    }

    @Test
    public void testPerson() {
        Person p = new Person();
        assertThat(p).isNotNull();
    }
}
