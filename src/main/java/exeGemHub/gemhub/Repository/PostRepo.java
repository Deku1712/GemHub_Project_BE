package exeGemHub.gemhub.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import exeGemHub.gemhub.Entity.PostBlog;


@Repository
public interface PostRepo extends JpaRepository<PostBlog, Integer>{

}