package com.git.manager.comment;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

	private CommentRepository repo;
	
	@Autowired
	public CommentController(CommentRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping(value = "/comments")
	public List<Comment> getComments() throws InterruptedException {
		return repo.findAll(Sort.by("id").descending());
	}
	
	@GetMapping("/comments/paging")
	public Page<Comment> getCommentsPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}
	@PostMapping(value = "/comments")
	public Comment addComment(@RequestBody Comment comment, HttpServletResponse res) {
	
		if ((comment.getName() == null || comment.getName().isEmpty())
				|| (comment.getMemo() == null || comment.getMemo().isEmpty())) {

			// res.setStatus(400);
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			return null;
		}
		
		
		Comment commentItem = Comment.builder().memo(comment.getMemo()).name(comment.getName()).createdTime(new Date().getTime()).build();
		
		Comment commentSaved = repo.save(commentItem);
		
		res.setStatus(HttpServletResponse.SC_ACCEPTED);
		
		return commentSaved;
	}
}
