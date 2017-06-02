package com.repository;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByPostTimeGreaterThanEqual(Date postTime, Pageable pageable);
//    List<Post> findByPostTimeGreaterThanEqual(Date postTime);
    Post save(Post post);
	Post findPostByPostId(long postId);

}
