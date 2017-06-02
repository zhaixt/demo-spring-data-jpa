package com.service.impl;


/**
 * Created by zhaixiaotong on 2016-9-17.
 * 帖子
 */
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.comm.response.BaseResponse;
import com.model.Post;
import com.repository.PostRepository;
import com.service.PostService;
import com.util.DateUtil;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public  BaseResponse<Page<Post>> findPostByPostTime(int currentPage, int pageSize) {
		BaseResponse<Page<Post>> response = new BaseResponse<>(Boolean.TRUE);
		Date date = new Date();
		Date postDate = DateUtil.nextDays(date,-7);
		try {
			Page<Post> result= postRepository.findByPostTimeGreaterThanEqual(postDate, new PageRequest(currentPage, pageSize));
//			List<Post> result = postRepository.findByPostTimeGreaterThanEqual(postDate);
//			System.out.println(result.toString());
 			response.setResult(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("根据时间查找论坛中的帖子失败");
		}
		return response;
	}
	@Override
	public  BaseResponse<Integer> savePost(Post post){
		BaseResponse<Integer> response = new BaseResponse<>(Boolean.TRUE);
		post.setPostTime(new Date());
		try {
			postRepository.save(post);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("保存帖子失败");
		}
		return response;
	}
	@Override
	public  BaseResponse<Integer> addLikeNum(Long postId)//返回点赞数
	{
		BaseResponse<Integer> response = new BaseResponse<>(Boolean.TRUE);
		try{
			Post post = postRepository.findPostByPostId(postId);
			Integer likeNum = post.getLikeNum()+1;
			post.setLikeNum(likeNum);
			postRepository.save(post);
			response.setResult(likeNum);
		}catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("给帖子下面的评论点赞失败");
		}
		return response;
	}
	
	@Override
	public  BaseResponse<Integer> addCommentNumForPost(Long postId)//增加帖子的评论数
	{
		BaseResponse<Integer> response = new BaseResponse<>(Boolean.TRUE);
		try{
			Post post = postRepository.findPostByPostId(postId);
			Integer commentNum = post.getCommentsNum()+1;
			post.setCommentsNum(commentNum);
			postRepository.save(post);
			response.setResult(commentNum);
		}catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(Boolean.FALSE);
			response.setFailMessage("增加帖子的评论数失败");
		}
		return response;
	}
}
