package inf.questpartner.controller.api;

import inf.questpartner.domain.room.Room;
import inf.questpartner.domain.room.common.RoomThumbnail;
import inf.questpartner.domain.room.common.RoomType;
import inf.questpartner.domain.tag.TagOption;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.dto.room.UpdateRoomRequest;
import inf.questpartner.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomApiController {

    private final RoomService roomService;

    // 방 생성 폼
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("tags", TagOption.values());
        model.addAttribute("thumbnails", RoomThumbnail.values());
        model.addAttribute("roomTypes", RoomType.values());
        model.addAttribute("form", new CreateRoomRequest());

        return "room/createRoomForm";
    }


    // 등록 이벤트
    @PostMapping("/new")
    public String createRoom(@ModelAttribute CreateRoomRequest form, RedirectAttributes redirectAttributes) {
        Long id = roomService.createRoom(form);
        redirectAttributes.addAttribute("roomId", id);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/rooms/{roomId}";
    }

    //방 목록 조회
    @GetMapping("/list")
    public List<Room> roomList(){
        /*List<Room> roomList = roomService.findByAll();
        return "room/roomList";*/
        return roomService.findByAll();
    }

    //방 상세 페이지 조회
    @GetMapping("/{roomId}")
    public String roomDetail(@PathVariable("roomId") Long roomId, Model model) {
        Room room = roomService.findById(roomId);
        model.addAttribute("room", room);
        model.addAttribute("hashTags", room.getRoomHashTags());

        return "room/roomDetail";
    }

    //방 수정 폼
    @PatchMapping("/{roomId}/edit")
    public Room updateForm(@PathVariable("roomId") Long roomId, @ModelAttribute UpdateRoomRequest form){
        //서비스를 통해 방 정보 수정
        Room updated = roomService.updateRoom(roomId, form);
        //수정 후 해당 방 상세 페이지로 이동
        return updated;
        //return "redirect:/rooms/{roomId}";
    }

    //방 삭제
    @DeleteMapping("/{roomId}/delete")
    public ResponseEntity delete(@PathVariable("roomId") Long roomId){
        //서비스를 통해 방 삭제
        Room deleted = roomService.deleteRoom(roomId);
        //삭제 후 방 목록으로 리다이렉트
        return new ResponseEntity(HttpStatus.OK);
        /*
         *return (delete  != null) ?
            엔티티.status(HttpStatus.NO_CONTENT).build() :
            엔티티.status(HttpStatus.BAD_REQUEST).build();
         */

    }


    /*

    // 검색 조회
    @GetMapping
    public Set<Room> findRoomByTag(@RequestParam List<TagOption> tags) {
        List<Room> roomList = roomService.findAll();
        return roomService.findRoomsByTag(tags, roomList);
    }

    // 데이터 기반으로 추천 받기
    @GetMapping("/recommend")
    public List<RoomTag> recommendRooms(@RequestParam(value = "id") Long id) {
        User user = userService.findById(id);
        List<RoomTag> roomTags = roomService.findRoomTags();

        return roomService.recommendLogic(user.toUserWishDto(), roomTags);
    }
     */

}
