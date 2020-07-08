package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/trello")
public class TrelloController {
    private final TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
//        trelloClient.getTrelloBoards().ifPresent(trelloBoard -> trelloBoard.stream()
//                .filter(trelloBoardDto -> countTrelloBoardRequiredFields(trelloBoardDto) == 2)
//                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
//                .forEach(trelloBoardDto -> {
//                    System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
//                    System.out.println("This board contains lists: ");
//                    trelloBoardDto.getLists().forEach(trelloList ->
//                            System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//                }));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }

//    private Long countTrelloBoardRequiredFields(final TrelloBoardDto trelloBoardDto) {
//        return Arrays.stream(trelloBoardDto.getClass().getDeclaredFields())
//                .filter(field -> field.getName().equals("id") ^ field.getName().equals("name"))
//                .count();
//    }
}
