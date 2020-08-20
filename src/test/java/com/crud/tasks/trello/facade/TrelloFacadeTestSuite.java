package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {
    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList() {
        //GIVEN
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));

        given(trelloService.fetchTrelloBoards()).willReturn(trelloBoards);
        given(trelloMapper.mapToBoards(trelloBoards)).willReturn(mappedTrelloBoards);
        given(trelloMapper.mapToBoardsDto(anyList())).willReturn(new ArrayList<>());
        given(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).willReturn(new ArrayList<>());
        //WHEN
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //THEN
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //GIVEN
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "my_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "my_task", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "my_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "my_task", mappedTrelloLists));

        given(trelloService.fetchTrelloBoards()).willReturn(trelloBoards);
        given(trelloMapper.mapToBoards(trelloBoards)).willReturn(mappedTrelloBoards);
        given(trelloMapper.mapToBoardsDto(anyList())).willReturn(trelloBoards);
        given(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).willReturn(mappedTrelloBoards);
        //WHEN
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //THEN
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("my_task", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("my_list", trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void shouldCreateCard() {
        //GIVEN
        TrelloCard trelloCard = new TrelloCard("Name", "Description", "Pos", "ListId");
        TrelloCardDto trelloCardDto = new TrelloCardDto("Name", "Description", "Pos", "ListId");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Name", "http://test.com/cards/");

        given(trelloService.createdTrelloCard(trelloCardDto)).willReturn(createdTrelloCardDto);
        given(trelloMapper.mapToCard(trelloCardDto)).willReturn(trelloCard);
        given(trelloMapper.mapToCardDto(trelloCard)).willReturn(trelloCardDto);
        //WHEN
        CreatedTrelloCardDto card = trelloFacade.createCard(trelloCardDto);
        //THEN
        assertEquals("1", card.getId());
        assertEquals("Name", card.getName());
        assertEquals("http://test.com/cards/", card.getShortUrl());
    }
}
