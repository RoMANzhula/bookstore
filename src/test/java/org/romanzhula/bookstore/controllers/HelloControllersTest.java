package org.romanzhula.bookstore.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloControllers.class) //without all context from our app
class HelloControllersTest {

    @Autowired //inject MockMvc
    MockMvc mockMvc; //without web-server

    @Test
    void testHelloPageWithName() throws Exception {
        mockMvc
                .perform(get("/hello/")
                .param("name", "Roman"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    void testHelloPageSomeWord() throws Exception {
        mockMvc
                .perform(get("/hello/")
                        .param("name", "Roman"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
        ;
    }

    @Test
    void testHelloPageWithWordFriend() throws Exception {
        mockMvc
                .perform(get("/hello/")
                        .param("name", "Roman"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(content().string(containsString("friend")))
        ;
    }

    @Test
    void testHelloPageWrongParam() throws Exception {
        mockMvc
                .perform(get("/hello/")
                        .param("something_wrong", "Roman"))
                .andExpect(status().is4xxClientError())
        ;
    }

}