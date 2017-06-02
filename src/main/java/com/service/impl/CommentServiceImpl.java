package com.service.impl;



/**
 * Created by zhaixiaotong on 2016-9-17.
 * 帖子下的评论
 */
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.comm.response.BaseResponse;
import com.model.Comment;
import com.model.Post;
import com.repository.CommentRepository;
import com.repository.PostRepository;
import com.service.CommentService;
import com.service.PostService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
    private PostService postService;
	
	@Autowired
	private PostRepository postRepository;
	
	public BaseResponse<Page<Comment>> findByPostId(Long postId, int currentPage, int pageSize) {
		BaseResponse<Page<Comment>> response = new BaseResponse<>(Boolean.TRUE);
		   
		try {
//			 BaseResponse<Page<Comment>> response = null;
			response.setResult(commentRepository.findByPostId(postId, new PageRequest(currentPage, pageSize)));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("根据帖子编号查找评论信息失败");
		}
		return response;
	}
	@Override
	public BaseResponse<Integer> saveComment(Comment comment){
		BaseResponse<Integer> response = new BaseResponse<>(Boolean.TRUE);
		comment.setCommentTime(new Date());
		try {
			commentRepository.save(comment);
			//添加评论，帖子的评论数+1
			Long postId = comment.getPostId();
//			postService.addCommentNumForPost(postId);
			Post post = postRepository.findPostByPostId(postId);
			Integer commentNum = post.getCommentsNum()+1;
			post.setCommentsNum(commentNum);
			postRepository.save(post);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("保存帖子下面的评论失败");
		}
		return response;
	}
	//给评论点赞
	@Override
	public BaseResponse<Integer> addLikeNum(Long commentId)//返回点赞数
	{
		BaseResponse<Integer> response = new BaseResponse<>(Boolean.TRUE);

		try{
			Comment comment = commentRepository.findCommentByCommentId(commentId);
			Integer likeNum = comment.getLikeNum()+1;
			comment.setLikeNum(likeNum);
			commentRepository.save(comment);
			response.setResult(likeNum);
		}catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("给帖子下面的评论点赞失败");
		}
		return response;
	}

	  
}
