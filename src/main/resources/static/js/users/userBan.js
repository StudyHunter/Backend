function banRequest() {


    var form = $("#userForm")[0];
    var formData = new FormData(form);

    //data json 으로 저장 ->
    var data = {
        "id" : $.trim($("#id").val()),
        "userStatus" : $.trim($("#userStatus").val()),
    }
    formData.append("updateItemRequest", new Blob([JSON.stringify(data)] , {type: "application/json"}));     //updateItemRequest 이름으로 게시글 내용 저장
    var id = $('#id').val();
    var userStatus = $('#userStatus').val();

    $.ajax({
                    url : "/admin/users/ban",
                    type : "post",
                    data : formData,
                    headers: {
					    "Content-Type":"application/json"  //전달하는 데이터가 JSON형태임을 알려줌
				    },
				    data: JSON.stringify({'id': id, 'userStatus': userStatus}),
                    success : function(data) {
                        alert("밴처리 성공");
                        window.location.replace('/admin/users'); // 메인 홈 창으로 이동
                    },
                    error : function(error){
                        alert(JSON.stringify(error));
                    }
        });


}


function addItemSubmit(){

    // CreateItemRequest 정보 저장
    var form = $("#userForm")[0];
    var formData = new FormData(form);
    var id = $('#id').val();

    //data json 으로 저장
    var data = {
        "id" : $.trim($("#id").val()),
        "userStatus" : $.trim($("#userStatus").val())
    }
    formData.append("banRequest", new Blob([JSON.stringify(data)] , {type: "application/json"}));

    $.ajax({
                url : "/admin/users/" + id + "/ban",
                type : "post",
                data : formData,
                contentType : false, // 이미지 업로드 필수 파라미터
                processData : false,
                success : function(data) {
                    alert("밴처리 성공");
                    window.location.replace('/admin/users'); // 메인 홈 창으로 이동
                },
                error : function(error){
                    alert(JSON.stringify(error));
                }
    });
}


