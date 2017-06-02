package com.service;


import com.comm.response.BaseResponse;
import com.model.Comment;

import java.util.List;

import org.springframework.data.domain.Page;

public interface CommentService {
	public BaseResponse<Page<Comment>> findByPostId(Long postId, int currentPage, int pageSize);
	public BaseResponse<Integer> saveComment(Comment user);//返回个数
	public BaseResponse<Integer> addLikeNum(Long commentId);//给帖子下面的评论点赞,返回点赞数

//	List<Comment> findByPostId(Long postId);
}
