package exeGemHub.gemhub.Service;

import java.util.List;
import java.util.Optional;

import exeGemHub.gemhub.DTO.PostDto;
import exeGemHub.gemhub.Entity.PostBlog;

public interface PostService {

    Optional<PostBlog> findById(int id);


    List<PostBlog> findAll();

    PostBlog createPost(PostDto postDto);

    void deleteAllPost();

    PostBlog updatePostById(int id, PostDto postDto);

    void deletePostById(int id);
}