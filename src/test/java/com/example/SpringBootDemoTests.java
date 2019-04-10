package com.example;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

public class SpringBootDemoTests {

    @Test
    public void testQueue() {
        Queue<String> queue = new LinkedTransferQueue<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        for (String str: queue) {
            System.out.println(queue.poll());
        }
        System.out.println(queue);
        System.out.println(queue.poll());
    }

    @Test
    public void testPathMatcher() {
        PathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("/**", ""));
    }

    @Test
    public void testEquals() {
        System.out.println(Objects.equals(null, null));
    }

}
