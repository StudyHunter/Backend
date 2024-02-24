### createRoomForm
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="fragments/header :: header" />

<body>

<div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader"/>

    <form th:action="@{/rooms/new}" th:object="${form}" method="post">
        <div class="form-group">
            <label>방장명</label>
            <input type="text" th:field="*{author}" class="form-control" placeholder="방장명을 입력하세요">
        </div>

        <div class="form-group">
            <label>방제목</label>
            <input type="text" th:field="*{title}" class="form-control" placeholder="방장명을 입력하세요">
        </div>

        <div class="form-group">
            <label>인원수 제한</label>
            <div>
                <label th:each="num, iterStat : ${#numbers.sequence(2, 6)}" class="radio-inline" th:classappend="${iterStat.first} ? '' : 'ml-3'">
                    <input type="radio" th:field="*{expectedUsers}" th:value="${num}" th:id="'option' + ${num}">
                    <span th:text="${num}"></span>
                </label>
            </div>
        </div>

        <div>
            <div>섬네일 선택</div>
            <div th:each="type : ${thumbnails}" class="form-check form-check-inline">
                <input type="radio" th:field="*{thumbnail}" th:value="${type.name()}"
                       class="form-check-input">
                <div>
                    <label th:for="${#ids.prev('thumbnail')}" th:text="${type.getThumbnailType()}" class="form-check-label"></label>
                    <img class="resized-image" th:src="|${type.imgPath}|">
                </div>
            </div>
        </div>

        <div class="form-group">
            <label>방 종류</label>
            <select type="text" id="roomType" name="roomType" class="form-control nice-select wide">
                <option value="">==방 종류 선택==</option>
                <option th:each="roomType : ${roomTypes}"
                        th:value="${roomType.name()}" th:text="${roomType.getRoomType()}"></option>
            </select>
        </div>



        <div class="form-group">
            <label>방 태그선택</label>
            <div th:each="tag : ${tags}" class="form-check form-check-inline">
                <input type="checkbox" th:field="*{tags}" th:value="${tag.name()}" class="form-check-input">
                <label th:for="${#ids.prev('tags')}"
                       th:text="${tag.getViewName()}" class="form-check-label">== 방 태그 ==</label>
            </div>
        </div>



        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <br/>
    <div th:replace="fragments/footer :: footer" />

</div> <!-- /container -->

</body>
</html>
```

<br><br><br>

### roomDetail
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="fragments/header :: header" />

<body>

<div class="container">
    <div th:replace="fragments/bodyHeader :: bodyHeader"/>

    <div class="py-5 text-center">
        <h2>방 정보</h2>
    </div>

    <div>
        <label>방장명</label>
        <input type="text" id="author" class="form-control" th:value="${room.author}" readonly>
    </div>

    <br>

    <div>
        <label>방제목</label>
        <input type="text" id="title" class="form-control" th:value="${room.title}" readonly>
    </div>

    <br>

    <div>
        <label>스터디 유형</label>
        <input type="text" id="roomType" class="form-control" th:value="${room.roomType.name()}" readonly>
    </div>

    <br>

    <div>
        <label>방 섬네일</label>
        <div>
            <img class="resized-image" th:src="|${room.thumbnail.imgPath}|">
        </div>
    </div>

    <br>

    <div>
        <label>방 태그들</label>
        <div>
            <li th:each="tag : ${hashTags}" class="tag-box" th:text="${tag.tagOption.name}"></li>
        </div>

    </div>


    <br/>
    <div th:replace="fragments/footer :: footer" />

</div> <!-- /container -->


</body>
</html>
```

