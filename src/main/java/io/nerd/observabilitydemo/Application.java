package io.nerd.observabilitydemo;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import io.nerd.observabilitydemo.post.JsonPlaceholderService;
import io.nerd.observabilitydemo.post.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    JsonPlaceholderService jsonPlaceholderService() {
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(JsonPlaceholderService.class);
    }

    @Bean
    @Observed(name = "posts.load-all-posts", contextualName = "posts-service.find-all")
    CommandLineRunner commandLineRunner() {
        return args -> {
            List<Post> posts = jsonPlaceholderService().findAll();
            log.info("All posts: {}", posts.size());
        };
    }
}
