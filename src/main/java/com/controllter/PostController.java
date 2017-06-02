package com.controllter;


import com.comm.response.BaseResponse;
import com.model.Post;
import com.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhaixiaotong on 2016-9-17.
 * 帖子
 */
@RestController
@RequestMapping(value = "/post")
public class PostController {
	@Autowired
    private PostService postService;
	/*
	 * 查出一定时间的所有帖子
	 */
    @RequestMapping("/currentPage/{currentPage}/pageSize/{pageSize}")
    BaseResponse<Page<Post>> findById(@PathVariable int currentPage,@PathVariable int pageSize) {
        return postService.findPostByPostTime( currentPage, pageSize);
    }
    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    BaseResponse<Integer> saveUser(@RequestBody final Post post) {
      return postService.savePost(post);
    }
    /*
	 * 给帖子点赞
	 */
    //TODO  
    @RequestMapping("/like/{postId}")
    BaseResponse<Integer> likeByPostId(@PathVariable Long postId) {
        return postService.addLikeNum(postId);
    }
}
