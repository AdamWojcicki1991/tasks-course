package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards() {
        //GIVEN
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Name", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto);
        //WHEN
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //THEN
        assertEquals(1, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("Name", trelloBoards.get(0).getName());
        assertEquals(Collections.EMPTY_LIST, trelloBoards.get(0).getLists());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //GIVEN
        TrelloBoard trelloBoard = new TrelloBoard("1", "Name", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //WHEN
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //THEN
        assertEquals(1, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("Name", trelloBoards.get(0).getName());
        assertEquals(Collections.EMPTY_LIST, trelloBoards.get(0).getLists());
    }

    @Test
    public void shouldMapToList() {
        //GIVEN
        TrelloListDto trelloListDto = new TrelloListDto("1", "Name", true);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);
        //WHEN
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        //THEN
        assertEquals(1, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("Name", trelloLists.get(0).getName());
        assertTrue(trelloLists.get(0).isClosed());
    }

    @Test
    public void shouldMapToListDto() {
        //GIVEN
        TrelloList trelloList = new TrelloList("1", "Name", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        //WHEN
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        //THEN
        assertEquals(1, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("Name", trelloLists.get(0).getName());
        assertTrue(trelloLists.get(0).isClosed());
    }

    @Test
    public void shouldMapToCard() {
        //GIVEN
        TrelloCardDto trelloCardDto = new TrelloCardDto("Name", "Description", "Pos", "ListID");
        String nameDto = trelloCardDto.getName();
        String descriptionDto = trelloCardDto.getDescription();
        String posDto = trelloCardDto.getPos();
        String listIdDto = trelloCardDto.getListId();
        //WHEN
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        String name = trelloCard.getName();
        String description = trelloCard.getDescription();
        String pos = trelloCard.getPos();
        String listId = trelloCard.getListId();
        //THEN
        assertEquals(nameDto, name);
        assertEquals(descriptionDto, description);
        assertEquals(posDto, pos);
        assertEquals(listIdDto, listId);
    }

    @Test
    public void shouldMapToCardDto() {
        //GIVEN
        TrelloCard trelloCard = new TrelloCard("Name", "Description", "Pos", "ListID");
        String name = trelloCard.getName();
        String description = trelloCard.getDescription();
        String pos = trelloCard.getPos();
        String listId = trelloCard.getListId();
        //WHEN
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        String nameDto = trelloCardDto.getName();
        String descriptionDto = trelloCardDto.getDescription();
        String posDto = trelloCardDto.getPos();
        String listIdDto = trelloCardDto.getListId();
        //THEN
        assertEquals(name, nameDto);
        assertEquals(description, descriptionDto);
        assertEquals(pos, posDto);
        assertEquals(listId, listIdDto);
    }
}
