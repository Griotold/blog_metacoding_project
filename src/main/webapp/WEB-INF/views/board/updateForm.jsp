<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

    <div class="container">
        <form>
          <input type="hidden" id="id" value="${board.id}">
          <div class="form-group">
            <input value="${board.title}" type="text" class="form-control" placeholder="Enter Title" id="title">
          </div>

          <div class="form-group">
            <textarea class="form-control summernote" rows="3" id="content">${board.content}</textarea>
          </div>

        </form>
        <button id="btn-update2" class="btn btn-primary">글 수정 완료</button>

    </div>

<%@ include file="../layout/footer.jsp"%>
<script>
  $('.summernote').summernote({
    placeholder: '게시글을 작성해보세요!',
    tabsize: 2,
    height: 300
  });
</script>



