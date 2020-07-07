package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/trello")
public class TrelloController {
    private final TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {
        trelloClient.getTrelloBoards().ifPresent(trelloBoard -> trelloBoard.stream()
                .filter(trelloBoardDto -> countTrelloBoardRequiredFields(trelloBoardDto) == 2)
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName())));
    }

    private Long countTrelloBoardRequiredFields(final TrelloBoardDto trelloBoardDto) {
        return Arrays.stream(trelloBoardDto.getClass().getDeclaredFields())
                .filter(field -> field.getName().equals("id") ^ field.getName().equals("name"))
                .count();
    }
}
