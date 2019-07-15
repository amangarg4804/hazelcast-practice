package com.aman.demo;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastClientFactory;

import java.util.HashMap;
import java.util.Map;

public class BackupDemo {
    public static void main(String[] args) {
        HazelcastInstance member1 = Hazelcast.newHazelcastInstance();
        HazelcastInstance member2 = Hazelcast.newHazelcastInstance();

        Map<String, String> mapFromMember1 = member1.getMap("someMap");

        mapFromMember1.put("key1", "value1");
        mapFromMember1.put("key2", "value2");
        mapFromMember1.put("key3", "value3");
        mapFromMember1.put("key4", "value4");

        System.out.println("Number of Hazelcast members: " + Hazelcast.getAllHazelcastInstances().size());

        member1.shutdown();

        System.out.println("Number of Hazelcast members: " + Hazelcast.getAllHazelcastInstances().size());
        Map<String, String> mapFromMember2 = member2.getMap("someMap");

        mapFromMember2.values().forEach(System.out::println);

    }
}
