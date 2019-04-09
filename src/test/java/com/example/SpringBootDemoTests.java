package com.example;

import org.junit.Test;

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

}
