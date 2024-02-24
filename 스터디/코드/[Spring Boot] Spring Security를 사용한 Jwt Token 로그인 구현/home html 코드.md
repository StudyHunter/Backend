### home.html

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="fragments/header :: header" />

<body>

<div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader"/>

    <div class="container">


        <div class="card card-7 text-center">
            <div class="card-body">
                <div class="input-group input--medium">
                    <label>방제</label>
                    <input class="input--style-1" type="text" th:field="${roomSearch.title}" th:value="title">
                </div>

                <button th:onclick="goSearch()" class="btn-submit" type="submit">search</button>
            </div>
        </div>

        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">전체 스터디룸</div>
                <div class="panel-body" th:unless="${#lists.size(rooms) != 0}">
                    검색결과가 없습니다.
                </div>
                <div class="panel-body" th:if="${#lists.size(rooms) != 0}">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>방제</th>
                            <th>섬네일</th>
                            <th>태그들</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr th:each="room : ${rooms}">
                            <input type="hidden" id="id" name="id" class="id" th:value="${room.id}">
                            <td th:text="${room.title}"></td>
                            <td><img class="resized-image" th:src="|${room.thumbnail.imgPath}|"></td>
                            <td> <li th:each="tag : ${room.roomHashTags}" class="tag-box" th:text="${tag.tagOption.name}"></li></td>
                            <td><button id="enterRoomButton" type="button">입장</button></td>
                            <td></td>
                        </tr>

                        </tbody>
                    </table>

                </div>
                <div class="text-center" th:if="${#lists.size(rooms) != 0}">
                    <nav aria-label="Page navigation">
                        <ul class="pagination pagination-sm">
                            <li th:if="${page.isPrev()}" class="page-item"><a th:onclick="goPage([[${page.curPage-1}]],[[${page.sortParam}]]);" class="page-link">Prev</a></li>
                            <li th:unless="${page.isPrev()}" class="page-item disabled"><a class="page-link">Prev</a></li>
                            <li class="page-item" th:each="num, index : ${#numbers.sequence(page.startPage, page.endPage)}">
                                <a th:if="${#strings.equals(page.curPage+1, num)}" th:text="${num}" class="page-link" style="background-color: #eeeeee">1</a>
                                <a th:unless="${#strings.equals(page.curPage+1, num)}" th:onclick="goPage([[${index.current-1}]],[[${page.sortParam}]]);" th:text="${num}" class="page-link">1</a>
                            </li>
                            <li th:if="${page.isNext()}" class="page-item"><a th:onclick="goPage([[${page.curPage+1}]],[[${page.sortParam}]]);" class="page-link">Next</a></li>
                            <li th:unless="${page.isNext()}" class="page-item disabled"><a class="page-link">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>

    <br/>
    <div th:replace="fragments/footer :: footer" />

</div> <!-- /container -->



<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

<script>
    function getSearchParam(searchParam) {
        var title = document.getElementById('title').value;

        if (title !== "") {
            searchParam += "&title=" + title;
        }

        return searchParam;
    }

    function goSearch() {
        var param = "/home?";

        param = getSearchParam(param);
        location.replace(param);
    }


    function goPage(page) {
        var param = "/home?";
        param += "page=" + page;
        param = getSearchParam(param);

        location.replace(param);
    }
</script>

<script>
        $.ajax({
            type: 'GET',
            url: '/api/v1/user',
            contentType:'application/json; charset=utf-8',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Content-type","application/json");
                xhr.setRequestHeader("Authorization", localStorage.getItem("Authorization"));
            },
        }).done(function(data,status,xhr) {
            $("#profile").html("사용자의 ID는 " + data.user.nickname + "입니다.<br>10*100초뒤에 토큰이 만료됩니다.");
        }).fail(function(xhr, status, error){
            var jsonResponse = JSON.parse(xhr.responseText);
            if(jsonResponse.status == "403" || jsonResponse.status == "500"){ //로그인을 안했거나 토큰이 만료됐거나
                alert("로그인이 필요한 페이지입니다.");
                location.href = "/login_page";
            }
        });
</script>

<script>
    $(document).ready(function() {
    var id = $.trim($("#id").val()); // 방의 ID 가져오기

    // 사용자 정보 가져오기
    $.ajax({
        type: 'GET',
        url: '/api/v1/user',
        contentType: 'application/json; charset=utf-8',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Content-type", "application/json");
            xhr.setRequestHeader("Authorization", localStorage.getItem("Authorization"));
        },
        success: function(userData, status, xhr) {
            var username = userData.user.nickname; // 사용자 닉네임 가져오기

            $("#enterRoomButton").click(function() {
                $.ajax({
                    type: "POST",
                    url: "/room/" + id + "/enter/" + username, // 수정된 URL
                    success: function(data) {
                        console.log("Entered room successfully");
                        location.href = "/rooms/" + id;
                        // 성공적으로 입장한 경우에 대한 처리
                    },
                    error: function(xhr, status, error) {
                        console.error("Error entering room:", error);
                        // 오류 발생시 처리
                    }
                });
            });
        },
        error: function(xhr, status, error) {
            var jsonResponse = JSON.parse(xhr.responseText);
            if (jsonResponse.status == "403" || jsonResponse.status == "500") {
                alert("로그인이 필요한 페이지입니다.");
                location.href = "/login_page";
            }
        }
    });
});

</script>


</body>
</html>
```
