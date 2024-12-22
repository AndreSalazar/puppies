package com.puppiesapi.project.repository;

import com.puppiesapi.project.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
