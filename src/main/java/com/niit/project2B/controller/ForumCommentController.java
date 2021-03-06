package com.niit.project2B.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.project2B.DAO.ForumCommentDAO;
import com.niit.project2B.model.ForumComment;

@RestController
public class ForumCommentController {
	@Autowired
	private ForumCommentDAO forumCommentDAO;
	
	@PostMapping(value="/commentforum/{fid}")
	public ResponseEntity<ForumComment> forumcomment(@RequestBody ForumComment forumcomment,HttpSession session,@PathVariable("fid") int fid){
		int uid=(Integer) session.getAttribute("uid");
		forumcomment.setForumid(fid);
		forumcomment.setUserid(uid);
		forumcomment.setCommenttime(new Date());
		forumCommentDAO.saveorupdate(forumcomment);
		return new ResponseEntity<ForumComment>(forumcomment,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/getforumcomment/{fid}")
	public ResponseEntity<List<ForumComment>> getcomment(@PathVariable("fid") int fid){
		List<ForumComment> comments =forumCommentDAO.list(fid);
		return new ResponseEntity<List<ForumComment>>(comments,HttpStatus.OK);
	}

}
