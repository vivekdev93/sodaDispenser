package com.sodadispenser.soda;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {SodaApplication.class})
public class SodaApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAvailableTypes() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/getTypes"));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void TestGetDesiredSodaWithExactAmount() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/dispenseSoda?sodaType=MAZZA&numberOfQuarters=50"));
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void TestGetDesiredSodaWithWrongAmount() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/dispenseSoda?sodaType=MAZZA&numberOfQuarters=40"));
        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void TestInventory() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/getInventory"));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void TestAllTransactions() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/getAllTransactions"));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}