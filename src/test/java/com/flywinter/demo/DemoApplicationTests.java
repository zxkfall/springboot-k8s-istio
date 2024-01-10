package com.flywinter.demo;

import com.flywinter.demo.controller.IndexController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndexController.class)
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to SpringBoot with Kubernetes and Istio %s".formatted(new Date())));
    }


    // 固定轮转编排法
    // 从轮转调度算法演化而来， 固定的任务调度算法
    @Test
    void test() {
        //1 2 3 4 5 6   六只球队
        List<Integer> teams = List.of(1, 2, 3, 4, 5, 6);

        //Anchor
        Integer anchor = teams.get(0);
        int length = teams.size();

        for (int i = 0; i < length - 1; i++) {

        }

        List<Integer> newList = new ArrayList<>();
        List<Integer> oldList = teams;

        newList.add(anchor);
        newList.add(oldList.get(length - 1));
        for (int i = 0; i < length - 2; i++) {
            newList.add(oldList.get(i + 2));
        }

        System.out.println(oldList);
        System.out.println(newList);

    }

    @Test
    void version2() {
        //1 2 3 4 5 6   六只球队
        List<Integer> teams = List.of(1, 2, 3, 4, 5, 6);

        //Anchor
        Integer anchor = teams.get(0);
        int length = teams.size();

        List<Integer> newList = teams;
        for (int i = 0; i < length - 1; i++) {
            var oldList = new ArrayList<>(newList);
            newList = new ArrayList<>();
            newList.add(anchor);
            newList.add(oldList.get(length - 1));
            for (int j = 0; j < length - 2; j++) {
                newList.add(oldList.get(j + 1));
            }
            System.out.println(newList);
        }
    }

    // 如果为奇数，则填补一个空位变成偶数
    @Test
    void version3() {
        //1 2 3 4 5 6   六只球队
        List<Integer> teams = List.of(1, 2, 3, 4, 5, 6);
        //Anchor
        Integer anchor = teams.get(0);
        int length = teams.size();
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> newList = teams;
        for (int i = 0; i < length - 1; i++) {
            var oldList = new ArrayList<>(newList);
            newList = new ArrayList<>();
            newList.add(anchor);
            newList.add(oldList.get(length - 1));
            for (int j = 0; j < length - 2; j++) {
                newList.add(oldList.get(j + 1));
            }
            res.add(newList);
        }
        res.forEach(System.out::println);
    }
    // by anchor out res
    @Test
    void version4() {
        //1 2 3 4 5 6   六只球队
        List<Integer> teams = List.of(1, 2, 3, 4, 5, 6, 7 ,8);
        //Anchor
        Integer anchor = teams.get(0);
        int length = teams.size();
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> newList = teams;
        for (int i = 0; i < length - 1; i++) {
            var oldList = new ArrayList<>(newList);
            newList = new ArrayList<>();
            newList.add(anchor);
            newList.add(oldList.get(length - 1));
            for (int j = 0; j < length - 2; j++) {
                newList.add(oldList.get(j + 1));
            }
            res.add(newList);
        }
        res.forEach(System.out::println);
    }

}
