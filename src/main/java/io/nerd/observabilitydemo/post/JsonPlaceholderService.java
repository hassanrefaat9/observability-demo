package io.nerd.observabilitydemo.post;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@Service
public interface JsonPlaceholderService {

    @GetExchange("/posts")
    List<Post> findAll();

    @GetExchange("/posts/{id}")
    Post findById(@PathVariable Integer id);

}
