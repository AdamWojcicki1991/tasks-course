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
        assertEquals("9cde7680f5c160e64daa1e9586493d5c", trelloAppKey);
        assertEquals("a77032318c5947d3d795eb0058f75d253d459bb45da9ba13d7c622b51abaaa37", trelloToken);
        assertEquals("adam00278002", userName);
    }
}
