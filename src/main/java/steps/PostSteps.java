package steps;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.PostsRequest;
import pojo.PostsResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PostSteps {

    private static final RequestSpecification SPECIFICATION =
            new RequestSpecBuilder()
                    .setBaseUri("http://jsonplaceholder.typicode.com")
                    .setBasePath("/posts")
                    .setContentType(ContentType.JSON)
                    .build();

    @Step("Получение списка пользователей")
    public static List<PostsResponse> getPosts() {
        return given().spec(SPECIFICATION)
                .when()
                .get().then()
                .statusCode(200)
                .and().assertThat()
                .body("$", notNullValue())
                .and()
                .extract().jsonPath()
                .getList("$", PostsResponse.class);
    }

    @Step("Добавить пост {rqBody}")
    public static PostsResponse setPost(PostsRequest rqBody) {
        return given().spec(SPECIFICATION)
                .when()
                .body(rqBody)
                .when().post()
                .then()
                .statusCode(201)
                .body("$", notNullValue())
                .extract().as(PostsResponse.class);

    }

}
