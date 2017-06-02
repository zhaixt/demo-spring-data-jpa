package com.service;


import com.comm.response.BaseResponse;
import com.model.Post;

import java.util.List;

import org.springframework.data.domain.Page;

public interface PostService {
	public  BaseResponse<Page<Post>>  findPostByPostTime( int currentPage, int pageSize);
	public  BaseResponse<Integer> savePost(Post post);//返回个数
	public  BaseResponse<Integer> addLikeNum(Long postId);//返回点赞数
	public  BaseResponse<Integer> addCommentNumForPost(Long postId);//增加帖子的评论数

}
