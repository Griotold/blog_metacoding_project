package com.cos.blog.repository;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    /**
     * @return Number of rows updated
     * */
    @Modifying
    @Query(value = "INSERT INTO reply(userId, boardId, content, createdDate) VALUES(?1, ?2, ?3, now())",
    nativeQuery = true)
    int mSave(Long userId, Long boardId, String content);
}
