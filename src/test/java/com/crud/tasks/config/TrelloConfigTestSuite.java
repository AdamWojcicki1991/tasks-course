package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {
    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void gettersWorkTest() {
        //GIVEN
        //WHEN
        String endpoint = trelloConfig.getTrelloApiEndpoint();
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        String trelloToken = trelloConfig.getTrelloToken();
        String userName = trelloConfig.getTrelloUserName();
        //THEN
        assertEquals("https://api.trello.com/1", endpoint);
        assertEquals("test_key", trelloAppKey);
        assertEquals("test_token", trelloToken);
        assertEquals("test_user", userName);
    }
}
