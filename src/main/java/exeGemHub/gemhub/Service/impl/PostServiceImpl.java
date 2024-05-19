package exeGemHub.gemhub.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exeGemHub.gemhub.DTO.PostDto;
import exeGemHub.gemhub.Entity.PostBlog;
import exeGemHub.gemhub.Entity.Product;
import exeGemHub.gemhub.Repository.PostRepo;
import exeGemHub.gemhub.Service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
    private PostRepo postRepo;

	@Override
	public Optional<PostBlog> findById(int id) {
		return postRepo.findById(id);
	}

	@Override
	public List<PostBlog> findAll() {
		return postRepo.findAll();
	}

	@Override
	public PostBlog createPost(PostDto postDto) {
		PostBlog postBlog = new PostBlog();
		postBlog.setAuthor(postDto.getAuthor());
		postBlog.setCreateTime(postDto.getCreateTime());
		postBlog.setDescription(postDto.getDescription());
		postBlog.setImage(postDto.getImage());
		postBlog.setTitle(postDto.getTitle());
		postRepo.save(postBlog);
		return postBlog;
	}

	@Override
	public void deleteAllPost() {
		postRepo.deleteAll();
	}

	@Override
	public PostBlog updatePostById(int id, PostDto postDto) {
		PostBlog p = postRepo.findById(id).get();
		p.setAuthor(postDto.getAuthor());
		p.setCreateTime(postDto.getCreateTime());
		p.setDescription(postDto.getDescription());
		p.setImage(postDto.getImage());
		p.setTitle(postDto.getTitle());
		postRepo.save(p);
		return p;
	}

	@Override
	public void deletePostById(int id) {
		postRepo.deleteById(id);
	}

}