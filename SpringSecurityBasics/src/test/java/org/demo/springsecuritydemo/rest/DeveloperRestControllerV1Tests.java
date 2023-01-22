package org.demo.springsecuritydemo.rest;

import org.demo.springsecuritydemo.WithMockCustomUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeveloperRestControllerV1Tests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(authorities = "developers:read")
    public void testGetDeveloperById_WithAuth() throws Exception {
        //given
        Integer id = 1;

        //when/then
        mockMvc.perform(get("/api/v1/developers/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void testGetDeveloperById_WithoutAuth() throws Exception {
        //given
        Integer id = 1;

        //when/then
        mockMvc.perform(get("/api/v1/developers/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockCustomUser
    public void testDeleteDeveloperById_Forbidden() throws Exception {
        //given
        Integer id = 1;

        //when/then
        mockMvc.perform(delete("/api/v1/developers/1"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testLogin_Success() throws Exception {
        mockMvc.perform(formLogin("/auth/login").user("admin").password("admin"))
                .andDo(print())
                .andExpect(authenticated());
    }


    @Test
    public void testLogin_Fail() throws Exception {
        mockMvc.perform(formLogin("/auth/login").user("user").password("user"))
                .andDo(print())
                .andExpect(unauthenticated());
    }

}
