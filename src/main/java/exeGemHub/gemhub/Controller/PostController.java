package exeGemHub.gemhub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exeGemHub.gemhub.DTO.PostDto;
import exeGemHub.gemhub.DTO.PostDto;
import exeGemHub.gemhub.DTO.PostDto;
import exeGemHub.gemhub.Entity.PostBlog;
import exeGemHub.gemhub.Entity.PostBlog;
import exeGemHub.gemhub.Entity.PostBlog;
import exeGemHub.gemhub.Service.PostService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
    public List<PostBlog> getAllPost() {
        return postService.findAll();
    }
	
	@PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<PostBlog> addPost(@RequestBody @Validated PostDto postDto){
        return ResponseEntity.ok(postService.createPost(postDto));
    }
	
	@PutMapping
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public void updatePost(){};

    @DeleteMapping
    public void deleteAllPost(){
        postService.deleteAllPost();
    }

    @GetMapping("/{id}")
    public PostBlog getPostById(@PathVariable("id") int id){
        return postService.findById(id).get();
    }

    @PostMapping("/{id}")
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public void addPostById(){}
    
    @PutMapping("/{id}")
    public ResponseEntity<PostBlog> updatePostById(@PathVariable("id") int id , @RequestBody @Validated PostDto PostDto){
        return ResponseEntity.ok(postService.updatePostById(id , PostDto));
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable("id") int id) {
        postService.deletePostById(id);
    }
}