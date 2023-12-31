package mini.soccerlocation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.soccerlocation.request.PostCreate;
import mini.soccerlocation.request.PostEdit;
import mini.soccerlocation.request.PostSearch;
import mini.soccerlocation.response.PostResponse;
import mini.soccerlocation.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    /**
     * API
     * 저장
     * 조회 ( 단건 조회, 전체 조회(페이징 처리), 조건 조회(페이징 처리) )
     * 수정
     * 삭제
     */

    @PostMapping("/post/save")
    public Long write(@Valid @RequestBody PostCreate postCreate) {

        return postService.write(postCreate);
    }

    @GetMapping("/post/{postId}")
    public PostResponse findOne(@PathVariable("postId") Long postId){
        PostResponse post = postService.getOne(postId);
        log.info(" >>>>> {} ",  post.getTitle());
        return post;
    }

    @GetMapping("/post/all")
    public List<PostResponse> findAll() {
        return postService.getAllPostPagingAndDesc();
    }

    @PostMapping("/post/search")
    public List<PostResponse> findSearch(@RequestBody PostSearch postSearch){
        log.info(">>>> PostSearch  {} {} ", postSearch.getTitle(), postSearch.getContent());
        return postService.getByTitleNameAndContent(postSearch);
    }

    @PostMapping("/post/{postId}")
    public PostResponse edit(@PathVariable Long postId, @RequestBody PostEdit postEdit) {
        return postService.edit(postId, postEdit);
    }

    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable Long postId){
        postService.delete(postId);
    }
}
