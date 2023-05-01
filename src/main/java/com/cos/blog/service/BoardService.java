package com.cos.blog.service;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final ReplyRepository replyRepository;

    private final UserRepository userRepository;

    @Transactional
    public void write(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
    @Transactional(readOnly = true)
    public Page<Board> list(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board detailServe(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateServe(Long id, Board board) {
        Board findedBoard = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 수정 실패 : 아이디를 찾을 수 없습니다.");
                });
        findedBoard.setTitle(board.getTitle());
        findedBoard.setContent(board.getContent());
    }
    @Transactional
    public void writeReply(ReplySaveRequestDto replyDto) {
        User user = userRepository.findById(replyDto.getUserId())
                .orElseThrow(() -> {
                    return new IllegalArgumentException("이런 회원이 없네요?");
                });
        Reply reply = new Reply();


        Board board = boardRepository.findById(replyDto.getBoardId())
                .orElseThrow(() -> {
                    return new IllegalArgumentException("해당 게시글이 없어요");
                });
        reply.setUser(user);
        reply.setBoard(board);
        reply.setContent(replyDto.getContent());
        replyRepository.save(reply);
    }
    // 영속성 컨텍스트를 거치지 않고 네이티브 쿼리로 곧바로 댓글 등록하기
    @Transactional
    public void writeReply2(ReplySaveRequestDto requestDto) {
        int updatedCount = replyRepository.mSave(requestDto.getUserId(),
                requestDto.getBoardId(), requestDto.getContent());
        System.out.println("updatedCount = " + updatedCount);
    }
}
