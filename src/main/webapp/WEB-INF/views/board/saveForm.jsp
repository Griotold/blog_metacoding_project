<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

    <div class="container">
        <form>
          <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" placeholder="Enter Title" id="title">
          </div>

          <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control summernote" rows="3" id="content"></textarea>
          </div>

        </form>
        <button id="btn-save2" class="btn btn-primary">글쓰기 완료</button>

    </div>

<%@ include file="../layout/footer.jsp"%>
<script>
  $('.summernote').summernote({
    placeholder: '게시글을 작성해보세요!',
    tabsize: 2,
    height: 300
  });
</script>



