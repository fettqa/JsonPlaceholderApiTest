package pojo;

import lombok.Data;

@Data
public class Comments {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;


}
