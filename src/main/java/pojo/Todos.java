package pojo;

import lombok.Data;

@Data
public class Todos {

    private Integer userId;
    private Integer id;
    private String title;
    private Boolean completed;

}
