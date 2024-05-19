package exeGemHub.gemhub.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PostBlog")
public class PostBlog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "createTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy")
    private Date createTime;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	
}