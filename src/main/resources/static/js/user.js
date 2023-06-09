let index = {
    init: function(){
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });

    },

    save: function(){
    //alert('user의 save함수 호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        //console.log(data);

        // ajax 호출시 default가 비동기 호출
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("회원가입이 완료되었습니다.");
            location.href="/";
        }).fail(function(xhr, status, error){
            if(xhr.status === 400) {
                alert("입력값이 올바르지 않습니다.");
            } else if(xhr.status === 422) {
                alert("이미 존재하는 회원입니다.");
            } else {
                alert("서버에 오류가 발생했습니다.");
            }

        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청

    },

    update: function(){

        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("회원수정이 완료되었습니다.");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));

        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청

    },

}

index.init();