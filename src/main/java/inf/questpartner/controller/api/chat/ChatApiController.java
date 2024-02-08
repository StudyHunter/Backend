package inf.questpartner.controller.api.chat;

import inf.questpartner.controller.response.ChatResponse;
import inf.questpartner.dto.chat.ChatMessageDto;
import inf.questpartner.dto.chat.CheckRequest;
import inf.questpartner.service.ChatConfigService;
import inf.questpartner.service.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
@RestController
@Slf4j
public class ChatApiController {

    private final ChattingService chattingService;
    private final ChatConfigService chatConfigService;
/*
    @PostMapping
    public void addChat(@RequestBody ChatMessageDto chatMessageDto) {
        log.info(chatMessageDto.getMessage() + " " + chatMessageDto.getWriter());
        chattingService.addChat(chatMessageDto);
    }

    @GetMapping("/{roomId}")
    public ChatResponse listChat(@PathVariable Long roomId) {
        log.info("roomId={}", roomId);
        List<ChatMessageDto> list = chattingService.listChat(roomId);
        return ChatResponse.success(list);
    }

    @PostMapping("/check")
    public ChatResponse checkSave(@RequestBody CheckRequest checkRequest) {
        return chatConfigService.checkSave(checkRequest);
    }

    @GetMapping("/check/{roomId}/{userName}")
    public ChatResponse checkRead(@PathVariable Long roomId, @PathVariable String userNickName) {
        return chatConfigService.checkRead(roomId, userNickName);
    }

 */
}
