package pojo;

import lombok.Data;

@Data
public class PostsRequest {

    private int userId;
    private String title;
    private String body;
}
