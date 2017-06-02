package com.controllter;
/**
 * Created by zhaixiaotong on 2016-9-17.
 * 评论
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comm.response.BaseResponse;
import com.model.Comment;
import com.service.CommentService;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
	@Autowired
    private CommentService commentService;
	/*
	 * 根据帖子编号查到帖子
	 */
    @RequestMapping("/postId/{postId}/currentPage/{currentPage}/pageSize/{pageSize}")
    BaseResponse<Page<Comment>> findById(@PathVariable Long postId,@PathVariable int currentPage,@PathVariable int pageSize) {
        return commentService.findByPostId(postId,currentPage,pageSize);
    }
	/*
	 * 保存帖子下面的评论
	 */
    @RequestMapping(value = "/saveComment", method = RequestMethod.POST)
    BaseResponse<Integer> saveComment(@RequestBody final Comment comment) {
      return commentService.saveComment(comment);
    }
    
    /*
	 * 给帖子下的评论点赞
	 */
    //TODO  
    @RequestMapping("/like/{commentId}")
    BaseResponse<Integer> likeByCommentId(@PathVariable Long commentId) {
        return commentService.addLikeNum(commentId);
    }
}
