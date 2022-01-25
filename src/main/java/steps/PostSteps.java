package steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.PostsResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostSteps {

    private static final RequestSpecification SPECIFICATION =
            new RequestSpecBuilder()
                    .setBaseUri("http://jsonplaceholder.typicode.com")
                    .setBasePath("/posts")
                    .setContentType(ContentType.JSON)
                    .build();

    public static List<PostsResponse> getPosts(){
        return given().spec(SPECIFICATION)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath()
                .getList("$", PostsResponse.class);
    }

}
