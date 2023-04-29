<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

    <div class="container">
        <button class="btn btn-info" onclick="history.back()">돌아가기</button>
        <c:if test="${board.user.id == principal.user.id}">
            <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
            <button id="btn-delete2" class="btn btn-danger">삭제</button>
        </c:if>
        <br/><br/>
        <div>
            글 번호 : <span id="id"><i>${board.id} </i></span>
            작성자 : <span><i>${board.user.username} </i></span>
        </div>
        <br/><br/>

        <div class="form-group">
            <h3>${board.title}</h3>
        </div>
        <hr/>

        <div class="form-group">
            <div>${board.content}</div>
        </div>


    </div>

<%@ include file="../layout/footer.jsp"%>




