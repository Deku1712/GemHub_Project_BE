package exeGemHub.gemhub.DTO;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private String image;
	private String author;
	private Date createTime;
	private String title;
	private String description;
}