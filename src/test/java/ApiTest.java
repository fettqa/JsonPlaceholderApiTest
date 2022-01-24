import io.restassured.http.ContentType;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojo.Posts;

import java.util.List;
import java.util.function.Function;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiTest {

    @BeforeAll
    static void setup() {
    }

    @Test
    public void getPosts() {
        List<Posts> posts = given()
                .baseUri("http://jsonplaceholder.typicode.com")
                .basePath("/posts")
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .getList("$",Posts.class);

        ObjectAssert<List<Posts>> a = assertThat(posts);
        a.extracting(Posts::getTitle).contains("");
    }
}
