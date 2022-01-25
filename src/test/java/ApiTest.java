import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.PostsRequest;
import pojo.PostsResponse;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class ApiTest {

    @BeforeAll
    static void setup() {


        ArrayList<Integer> a = new ArrayList<Integer>(100);
        a.forEach(n -> {
            if(n % 15 == 0) System.out.println("BingoBongo");
            if(n/3 == 0) System.out.println("Bingo");
            if(n/5 == 0) System.out.println("Bongo");
            System.out.println(n);
        });
    }

    @Test
    public void getPosts() {
        List<PostsResponse> posts = given()
                .baseUri("http://jsonplaceholder.typicode.com")
                .basePath("/posts")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .getList("$", PostsResponse.class);

        assertThat(posts).extracting(PostsResponse::getTitle).contains("");
    }

    @Test
    public void setPost(){
        PostsRequest rq = new PostsRequest();
        rq.setUserId(10);
        rq.setTitle("some title");
        rq.setBody("some body");

        PostsResponse rs = given()
                .baseUri("http://jsonplaceholder.typicode.com")
                .basePath("/posts")
                .contentType(ContentType.JSON)
                .body(rq)
                .when().post()
                .then().extract().as(PostsResponse.class);

        assertThat(rs)
                .isNotNull()
                .extracting(PostsResponse::getUserId).isEqualTo(rq.getUserId());
    }

}
