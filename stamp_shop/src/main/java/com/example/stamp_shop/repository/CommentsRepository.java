package com.example.stamp_shop.repository;

import com.example.stamp_shop.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
}
