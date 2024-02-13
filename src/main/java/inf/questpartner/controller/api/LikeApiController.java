package inf.questpartner.controller.api;

import inf.questpartner.dto.like.LikeRequest;
import inf.questpartner.dto.like.LikeResponse;
import inf.questpartner.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static inf.questpartner.dto.like.LikeResponse.success;

@RestController
@RequestMapping("/room/{id}/like")
@RequiredArgsConstructor
public class LikeApiController {

    private final LikeService likeService;

    // 좋아요를 하는 기능
    @PostMapping
    public LikeResponse<Void> insert(@RequestBody @Valid LikeRequest likeRequest, @PathVariable Long id) throws Exception {
        likeService.insert(likeRequest);
        return success(null);
    }

    // 좋아요를 취소하는 기능
    @DeleteMapping
    public LikeResponse<Void> delete(@RequestBody @Valid LikeRequest likeRequest, @PathVariable Long id) {
        likeService.delete(likeRequest);
        return success(null);
    }
}
