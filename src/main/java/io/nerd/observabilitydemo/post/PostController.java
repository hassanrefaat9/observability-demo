package io.nerd.observabilitydemo.post;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final JsonPlaceholderService postService;

    public PostController(JsonPlaceholderService postService) {
        this.postService = postService;
    }


    @GetMapping
    List<Post>findAll() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    Post findById(@PathVariable Integer id) {
        return postService.findById(id);
    }
}
