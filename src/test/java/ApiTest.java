import org.junit.jupiter.api.Test;
import pojo.PostsRequest;
import pojo.PostsResponse;
import steps.PostSteps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiTest {

    @Test
    public void getPosts() {
        List<PostsResponse> oldPosts = PostSteps.getPosts();

        PostsRequest newPost = PostsRequest.builder()
                .userId(11)
                .title("some title")
                .body("some body")
                .build();

        assertThat(oldPosts).extracting(PostsResponse::getUserId).isNotEqualTo(newPost.getUserId());

        PostsResponse newPostResponse = PostSteps.setPost(newPost);
        PostsRequest createdPost = newPostResponse.convertToRequest();

        assertThat(newPost).isEqualTo(createdPost);
        assertThat(newPostResponse).extracting(PostsResponse::getUserId).isEqualTo(newPost.getUserId());

        List<PostsResponse> newPosts = PostSteps.getPosts();

        assertThat(newPosts.stream().map(PostsResponse::getUserId)).contains(newPost.getUserId());

    }

}
