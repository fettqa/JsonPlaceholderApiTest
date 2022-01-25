package pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PostsResponse extends PostsRequest {
    private int id;
}
