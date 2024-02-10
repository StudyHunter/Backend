package inf.questpartner.controller;

import inf.questpartner.domain.room.Room;
import inf.questpartner.dto.room.CreateRoomRequest;
import inf.questpartner.repository.room.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    //새로운 room 게시
    @GetMapping("/room/new")
    public String newRoomDto(){

        return "room/new";
    }

    //입력한 내용으로 room 데이터 저장
    @PostMapping("/room/create")
    public String createRoom(CreateRoomRequest createRoomRequest){
        log.info(createRoomRequest.toString());

        //TO를 Entity로 변환
        Room room = createRoomRequest.toRoomEntity();
        log.info(room.toString());
        //repository로 Entity를 DB에 저장
        Room saved = roomRepository.save(room);
        log.info(saved.toString());
        // 상세 페이지로 리다이렉트
        return "redirect:/room/" + saved.getId();

    }

    @GetMapping("/room/{id}")   //방 id별로 조회
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);

        //id를 조회해 데이터 가져오기
        Room roomEntity = roomRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("room", roomEntity);
        //뷰페이지 반환
        return "room/show";
    }

    @GetMapping("/room")
    public String index(Model model){   //방 목록 조회
        //모든 데이터 가져오기
        List<Room> roomList = (List<Room>) roomRepository.findAll();
        //모델에 데이터 등록하기
        model.addAttribute("roomList", roomList);
        //뷰페이지 반환
        return "room/index";
    }

    @GetMapping("/room/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정 할 데이터 가져오기
        Room roomEntity = roomRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("room", roomEntity);
        //뷰 페이지 설정
        return "room/edit";
    }

    @PostMapping("/room/update")
    public String update(CreateRoomRequest createRoomRequest){
        log.info(createRoomRequest.toString());
        //DTO를 엔티티로 변환하기
        Room room = createRoomRequest.toRoomEntity();
        log.info(room.toString());
        //엔티티를 DB에 저장하기
        Room target = roomRepository.findById(room.getId()).orElse(null); //DB에서 기존 데이터 가져옴
        if (target != null){
            roomRepository.save(room);//엔티티를 DB에 갱신
        }
        //수정 결과 페이지로 리다이렉트
        return "redirect:/room/" + room.getId();
    }

}
