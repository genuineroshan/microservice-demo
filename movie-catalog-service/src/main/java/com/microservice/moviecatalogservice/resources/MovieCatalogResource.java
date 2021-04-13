package com.microservice.moviecatalogservice.resources;

import com.microservice.moviecatalogservice.models.CatalogItem;
import com.microservice.moviecatalogservice.models.Movie;
import com.microservice.moviecatalogservice.models.Rating;
import com.microservice.moviecatalogservice.models.UserRating;
import com.microservice.moviecatalogservice.services.MovieInfo;
import com.microservice.moviecatalogservice.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    private final WebClient.Builder webClientBuilder;
    private final MovieInfo movieInfo;
    private final UserRatingInfo userRatingInfo;

    public MovieCatalogResource(WebClient.Builder webClientBuilder, MovieInfo movieInfo, UserRatingInfo userRatingInfo) {
        this.webClientBuilder = webClientBuilder;
        this.movieInfo = movieInfo;
        this.userRatingInfo = userRatingInfo;
    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        UserRating userRating = userRatingInfo.getUserRating(userId);
        return userRating.getUserRating().stream().map(rating -> movieInfo.getCatalogItem(rating)).collect(Collectors.toList());
    }
}
