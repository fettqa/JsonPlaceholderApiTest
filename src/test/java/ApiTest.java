import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.PostsRequest;
import pojo.PostsResponse;
import steps.PostSteps;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест api: http://jsonplaceholder.typicode.com/")
public class ApiTest {

    @Description("Testing /posts")
    @Story("api /posts test")
    @Test
    public void postsTest() {
        List<PostsResponse> oldPosts = PostSteps.getPosts();

        PostsRequest newPost = PostsRequest.builder()
                .userId(11)
                .title("some title")
                .body("some body")
                .build();

        assertThat(oldPosts)
                .extracting(PostsResponse::getUserId)
                .isNotEqualTo(newPost.getUserId());

        PostsResponse newPostResponse = PostSteps.setPost(newPost);
        PostsRequest createdPost = newPostResponse.convertToRequest();

        assertThat(newPost).isEqualTo(createdPost);
        assertThat(newPostResponse).extracting(PostsResponse::getUserId).isEqualTo(newPost.getUserId());

        List<PostsResponse> newPosts = PostSteps.getPosts();
        List<Integer> newUserIds = newPosts.stream().map(PostsResponse::getUserId).collect(Collectors.toList());

        assertThat(newUserIds).contains(newPost.getUserId());

    }

}
