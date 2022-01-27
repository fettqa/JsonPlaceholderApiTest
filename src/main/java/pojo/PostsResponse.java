package pojo;

import lombok.Data;

import java.util.function.Function;

@Data
public class PostsResponse extends PostsRequest {
    private int id;


    public PostsRequest convertToRequest() {
        Function<PostsResponse, PostsRequest> convertResponseBodyToRequest =
                x -> PostsRequest.builder()
                        .userId(x.getUserId())
                        .title(x.getTitle())
                        .body(x.getBody())
                        .build();
        return convertResponseBodyToRequest.apply(this);
    }

}
