이번 시간에는 MVC와 Thymeleaf에 대해 정리해보자. <br>
Thymeleaf는 스프링과의 통합을 용이하게 해주는 다양한 기능을 제공한다. <br>
이러한 특징은 백엔드를 개발하는 개발자들이 Thymeleaf를 선택하는 이유 중 하나이다. <br>
> `즉, Rest API와는 다른 구현으로 생각하자. MVC로 간단하게 만든다는 생각으로 읽으면 된다.`

<br><br>

지금부터 타임리프가 제공하는 입력 폼 기능을 적용해서 스터디룸 Room 부분을 구현해보자. <br> 
주요 특징은 다음과 같다. <br>
+ `th:object` :편리한 폼 관리를 위한 속성
+ `th:field` :HTML 태그의 id , name , value 속성을 자동으로 처리해준다.
+ 입력 폼 생성: `체크박스`, `라디오 버튼`, `셀렉트 박스`로 데이터를 받을 수 있다.

<br>

### 📚 목차
목차는 다음과 같다. <br>
+ 요구사항
+ domain 구현
+ service 구현
+ controller 구현
+ html 구현 - createRoomForm
+ html 구현 - roomDetail
+ 웹 브라우저 실행
+ mysql 조회

## 요구사항
Room 생성에 필요한 데이터를 다음과 같이 입력을 받아야 한다. <br>
![방 생성에 필요한 것들](https://github.com/StudyHunter/Backend/assets/57389368/85cc2acd-c499-432f-aaba-cc5326e6745d) <br>

+ 방 제목 : 사용자의 입력으로 받는다.
+ 모집 인원 : 버튼 입력으로 받는다. (단, 1개만 선택 가능)
+ 방 태그 : 버튼 입력으로 받는다. (단, n개 선택 가능)
+ 스터디 유형 : 버튼 입력으로 받는다. (단, 1개만 선택 가능)
+ 방 썸네일 : 버튼 입력으로 받는다. (단, 1개만 선택 가능)

<br>

등록한 Room 정보는 다음과 같이 조회된다. <br>
![image](https://github.com/StudyHunter/Backend/assets/57389368/a71842cc-6a12-4b82-8a2b-46331ed990d3) <br>
요구사항을 보면, 아래와 같은 데이터가 조회되어야 한다. <br>
+ 방 썸네일
+ 방 제목
+ 모집 현황
+ 태그


## 도메인 구현
### Room 테이블
![room 테이블 필드설명](https://github.com/StudyHunter/Backend/assets/57389368/526dc33b-dab5-4213-9fae-e30c8bd972cb)
+ Room 테이블에 필요한 필드는 "author, title, expectedUsers, roomType, thumbnail"이다.
+ tag 필드는 RoomHashTag 테이블로 받는다.
+ Room과 RoomHashTag는 N:1 연관관계 매핑으로 다룬다.

### 💡 List<Enum타입>은 RDB에서 처리하지 못한다.
처음에 Room 테이블 안에 List<TagOption> 필드로 설계했는데 MySQL에서 null로 입력되는 이슈가 있었다. <br>
찾아보니, MySQL과 같은 관계형 데이터베이스는 컬렉션 타입을 지원하지 않는다고 한다. <br>
새로운 엔티티 RoomHashTag를 만들고 그 안에 식별자(id)와 TagOption을 담기로 했다. <br>
이 부분은 다른 포스팅으로 정리해보도록 하자. <br>

<br>

### RoomHashTag 테이블
![image](https://github.com/StudyHunter/Backend/assets/57389368/813094ce-c042-495f-aeb4-3dfa3e8ba944)


## service 구현
service 코드는 다음과 같다. <br>
![roomservice](https://github.com/StudyHunter/Backend/assets/57389368/5197b596-3350-421b-b5cc-73c43e0f11aa) <br><br>
+ CreateRoomRequest DTO를 통해 Room과 RoomHashTag 엔티티 생성에 필요한 데이터를 받는다.
+ Room과 RoomHashTag를 생성하고 나서, <br> Room과 RoomHashTag의 연관관계를 처리하여 태그 정보를 Room에 담는다.

### Room과 RoomHashTag 연관관계
![image](https://github.com/StudyHunter/Backend/assets/57389368/e8d39bd3-a98a-470c-978f-980027c6077b) <br>
위의 코드처럼, Room 안에 RoomHashTag를 List<>로 태그정보를 담으면 된다.


 ## controller 구현
 ### Get /rooms/new 요청으로 등록 폼 받기 
 ![image](https://github.com/StudyHunter/Backend/assets/57389368/3181b0d9-60fc-4a9e-ae07-a1e929701691) <br>
`model.addAttribute()`는 Spring MVC에서 모델에 속성을 추가하는 데 사용되는 메서드이다. <br>
이러한 속성은 일반적으로 `동적 콘텐츠를 렌더링`하기 위해 뷰 레이어에서 접근된다. <br> <br>
model.addAttribute() 속성에 대한 설명은 다음과 같다. <br>
+ tags : <br> TagOption 열거형의 값들을 모델에 추가한다. <br> 이는 방 생성 폼에서 `태그 선택지 목록을 제공`한다.
+ thumbnails : <br> RoomThumbnail 열거형의 값들을 모델에 추가한다. <br> 이는 방 생성 폼에서 `섬네일 선택지 목록을 제공`한다.
+ roomTypes : <br> RoomType 열거형의 값들을 모델에 추가한다. <br> 이는 방 생성 폼에서 `방 유형 선택지 목록을 제공`한다.
+ form : <br> CreateRoomRequest DTO를 생성하여 모델에 추가한다. <br> 이는 방 생성 `폼에서 입력된 데이터를 처리하기 위한 빈 폼 객체`이다.

<br><br>

### Post /rooms/new 요청으로 room을 새로 등록한다.
![image](https://github.com/StudyHunter/Backend/assets/57389368/058114a7-d512-41f9-8b2e-9a446cdf07f0) <br>
위 코드는 방 생성 폼의 제출을 처리하는 메서드를 정의한다.  <br>
이 메서드는 HTTP POST 요청을 처리하고, 방을 생성한 후 생성된 방의 세부 정보 페이지로 리디렉션한다. <br>

#### ✅ public String createRoom(@ModelAttribute CreateRoomRequest form, RedirectAttributes redirectAttributes)
`@ModelAttribute` 어노테이션을 사용하여 HTTP 요청에서 전송된 데이터를 CreateRoomRequest 객체로 바인딩한다. 
그리고 `RedirectAttributes`를 매개변수로 사용하여 리디렉션 시에 플래시 속성을 추가할 수 있다.

#### ✅ redirectAttributes.addAttribute("roomId", id);
생성된 방의 ID를 리디렉션 시에 roomId라는 이름으로 리디렉션 속성에 추가한다.

#### ✅ redirectAttributes.addAttribute("status", true);
생성된 방의 상태를 나타내는 플래시 속성을 추가한다. <br>
여기서는 방이 성공적으로 생성되었음을 나타내기 위해 true 값을 사용한다.

#### ✅ return "redirect:/rooms/{roomId}";
생성된 방의 세부 정보 페이지로 리디렉션한다. <br>
{roomId}는 실제로 생성된 방의 ID 값으로 치환된다.  <br> 이렇게 하면 사용자가 방 생성 후 바로 생성된 방의 세부 정보를 확인할 수 있다.

<br><br>

### Get 방식으로 생성한 Room 조회
![image](https://github.com/StudyHunter/Backend/assets/57389368/e91f76ce-07c2-48c1-948e-4c23ae2791c2) <br>
특정 방의 세부 정보를 렌더링하기 위해 필요한 데이터를 모델에 추가하고, 해당하는 뷰를 반환해보자. <br>

#### ✅ @GetMapping("/{roomId}")
해당 메서드는 /rooms/{roomId} 경로로 GET 요청을 처리한다. {roomId}는 실제 방의 ID로 대체된다.

#### ✅ public String roomDetail(@PathVariable Long roomId, Model model)
방 세부 정보를 나타내는 메서드이다. <br>
@PathVariable 어노테이션을 사용하여 URL에서 추출한 방 ID를 메서드의 매개변수로 전달한다. <br>
또한, Model 객체를 매개변수로 사용하여 뷰로 데이터를 전달할 수 있다. <br>
> `"/{roomId}"와 "@PathVariable Long roomId"의 변수명이 같아야 찾아진다.` <br>
> 변수명이 일치하지 않으면 Spring은 해당 매개변수에 경로 변수를 바인딩하지 못하고 오류가 발생할 수 있다. <br>
> 따라서 변수명을 일치시켜야 한다. <br>

<br>
 
#### ✅ Room room = roomService.findById(roomId);
주어진 ID에 해당하는 방을 roomService를 통해 검색한다.

#### ✅ model.addAttribute("room", room);
검색된 방 객체를 모델에 추가합니다. 이를 통해 뷰에서 방 객체에 접근하여 세부 정보를 표시할 수 있습니다.

#### ✅ model.addAttribute("hashTags", room.getRoomHashTags());
방 객체에서 해시태그 목록을 추출하여 모델에 추가합니다. 이를 통해 뷰에서 방의 해시태그를 표시할 수 있습니다.

## html 구현 - createRoomForm
POST 방식으로 데이터를 전송하는 폼을 정의하자. <br>

### 방장명, 방제목 : 입력받기
![입력폼1](https://github.com/StudyHunter/Backend/assets/57389368/f0d39c73-1b46-41fe-bbb4-653d69d9d4c2) <br><br>
+ `th:action` 속성은 폼의 동작을 정의하며, @{/rooms/new}는 해당 경로로 데이터를 전송한다. 
+ `th:object` 속성은 폼과 바인딩될 객체를 지정한다.
+ `${form}`은 컨트롤러에서 모델에 추가된 객체를 참조한다. 
+ `method="post"`는 폼을 제출할 때 사용될 HTTP 메서드를 정의한다.

<br><br>

### 인원수 제한 : 라디오 버튼으로 1개 선택
```html
       <div class="form-group">
            <label>인원수 제한</label>
            <div>
                <label th:each="num, iterStat : ${#numbers.sequence(2, 6)}" class="radio-inline" th:classappend="${iterStat.first} ? '' : 'ml-3'">
                    <input type="radio" th:field="*{expectedUsers}" th:value="${num}" th:id="'option' + ${num}">
                    <span th:text="${num}"></span>
                </label>
            </div>
        </div>
```

#### ✅ ${#numbers.sequence(2, 6)} 
+ Thymeleaf의 반복문을 사용하여 2부터 6까지의 숫자 시퀀스를 생성하고, 각 숫자에 대한 라디오 버튼을 생성한다.

#### ✅ `<input type="radio">`
HTML <input> 요소를 생성하며, 라디오 버튼을 나타낸다.

#### ✅ th:field="*{expectedUsers}" 
+ Thymeleaf의 폼 필드 바인딩 기능을 사용하여 해당 라디오 버튼을 expectedUsers 속성과 바인딩한다. 
+ 즉, 사용자가 이 라디오 버튼 중 하나를 선택하면 선택된 값이 expectedUsers 속성에 자동으로 설정된다.

#### ✅ th:value="${num}"
+ 이 코드는 라디오 버튼의 값을 지정한다.
+ ${num}은 Thymeleaf 표현식으로, 반복문에서 생성된 각 숫자를 나타낸다.
+ 선택된 라디오 버튼의 값은 해당 숫자로 설정된다.

#### ✅ th:id="'option' + ${num}"
+ 이 코드는 라디오 버튼의 ID를 지정한다.
+ ${num}은 Thymeleaf 표현식으로, 반복문에서 생성된 각 숫자를 나타낸다.
+ 각 라디오 버튼은 자체적으로 고유한 ID를 가져야 하므로, 이를 위해 각 숫자를 기반으로한 고유한 ID를 생성한다.


<br><br>

### 섬네일 선택 : 라디오 버튼으로 1개 선택
```html
            <div>섬네일 선택</div>
            <div th:each="type : ${thumbnails}" class="form-check form-check-inline">
                <input type="radio" th:field="*{thumbnail}" th:value="${type.name()}"
                       class="form-check-input">
                <div>
                    <label th:for="${#ids.prev('thumbnail')}" th:text="${type.getThumbnailType()}" class="form-check-label"></label>
                    <img class="resized-image" th:src="|${type.imgPath}|">
                </div>
            </div>
```

#### ✅ `<div th:each="type : ${thumbnails}" class="form-check form-check-inline">`
+ Thymeleaf의 반복문을 사용하여 thumbnails 객체에 포함된 각 섬네일 타입에 대해 반복한다.
+ 각 섬네일 타입은 type 변수에 저장된다.

#### ✅ `<input type="radio" th:field="*{thumbnail}" th:value="${type.name()}" class="form-check-input">`
+ 각 섬네일 타입을 선택할 수 있는 라디오 버튼을 생성한다.
+ th:field 속성은 폼 필드 바인딩을 수행하며, 선택된 섬네일 타입은 thumbnail 속성과 바인딩된다.
+ th:value는 라디오 버튼의 값으로, type.name()은 각 섬네일 타입의 이름을 나타낸다.

#### ✅ `<label th:for="${#ids.prev('thumbnail')}" th:text="${type.getThumbnailType()}" class="form-check-label">`
+ 라디오 버튼 옆에 각 섬네일 타입의 이름을 표시하는 라벨을 생성한다.
+ th:for 속성은 라디오 버튼의 ID를 참조하며, th:text는 각 섬네일 타입의 이름을 표시한다.

#### ✅ `<img class="resized-image" th:src="|${type.imgPath}|">`
+ 각 섬네일 타입에 대한 이미지를 표시한다
+ th:src 속성은 이미지의 소스 경로를 나타낸다.
+ |${type.imgPath}|는 Thymeleaf의 리터럴 텍스트를 사용하여 이미지 경로를 지정한다.
  
<br><br>

### 방 종류 선택 : 셀렉트 박스로 1개 선택
```html
     <div class="form-group">
            <label>방 종류</label>
            <select type="text" id="roomType" name="roomType" class="form-control nice-select wide">
                <option value="">==방 종류 선택==</option>
                <option th:each="roomType : ${roomTypes}"
                        th:value="${roomType.name()}" th:text="${roomType.getRoomType()}"></option>
            </select>
        </div>
```

#### ✅ ` <select type="text" id="roomType" name="roomType" class="form-control nice-select wide">`
+ 셀렉트 박스를 생성한다.
+ id와 name 속성은 셀렉트 박스의 식별을 위해 사용된다.

#### ✅ `<option value="">==방 종류 선택==</option>`
+ 셀렉트 박스의 기본 옵션을 정의한다.
+ 이 옵션은 사용자가 선택하지 않았을 때의 초기 상태를 나타낸다.

#### ✅ `<option th:each="roomType : ${roomTypes}" th:value="${roomType.name()}" th:text="${roomType.getRoomType()}">`
+ Thymeleaf의 반복문을 사용하여 각 방 종류를 옵션으로 생성한다.
+ roomTypes 객체에 포함된 각 방 종류는 roomType 변수에 저장된다.
+ th:value 속성은 옵션의 값을 지정하고, th:text 속성은 옵션에 표시될 텍스트를 지정한다.
+ 이 경우 roomType.name()은 방 종류의 이름을 나타내고, roomType.getRoomType()은 방 종류의 설명을 나타낸다.
> 여기서 `RoomType은 enum 타입`이다. <br>
> name() 메서드를 호출하여 해당 열거형 상수의 이름을 가져올 수 있다. <br>
> `따라서 roomType.name()은 해당 열거형 상수의 이름을 반환한다.`

<br><br>

### 방 태그 선택 : 체크박스로 n개 선택
```html
        <div class="form-group">
            <label>방 태그선택</label>
            <div th:each="tag : ${tags}" class="form-check form-check-inline">
                <input type="checkbox" th:field="*{tags}" th:value="${tag.name()}" class="form-check-input">
                <label th:for="${#ids.prev('tags')}"
                       th:text="${tag.getViewName()}" class="form-check-label">== 방 태그 ==</label>
            </div>
        </div>
```

#### ✅ `<div th:each="tag : ${tags}" class="form-check form-check-inline">`
Thymeleaf의 반복문을 사용하여 tags 객체에 포함된 각 방 태그에 대해 반복한다. 각 방 태그는 tag 변수에 저장된다.

#### ✅ `<input type="checkbox" th:field="*{tags}" th:value="${tag.name()}" class="form-check-input">`
+ 각 방 태그를 선택할 수 있는 체크 박스를 생성한다.
+ th:field 속성은 폼 필드 바인딩을 수행하며, 선택된 방 태그들은 tags 속성과 바인딩된다.
+ th:value는 체크 박스의 값을 지정한다.
+ 여기서 ${tag.name()}은 각 방 태그의 이름을 나타낸다.

#### ✅ `<label th:for="${#ids.prev('tags')}" th:text="${tag.getViewName()}" class="form-check-label">== 방 태그 ==</label>`
+ 각 체크 박스 옆에 방 태그의 이름과 함께 라벨을 생성한다.
+ th:for 속성은 체크 박스의 ID를 참조하며, th:text는 각 방 태그의 뷰 이름을 표시한다.

 ##  html 구현 - roomDetail
 ```html
<input type="text" id="author" class="form-control" th:value="${room.author}" readonly>
<input type="text" id="title" class="form-control" th:value="${room.title}" readonly>
<input type="text" id="roomType" class="form-control" th:value="${room.roomType.name()}" readonly>
<img class="resized-image" th:src="|${room.thumbnail.imgPath}|">
<li th:each="tag : ${hashTags}" class="tag-box" th:text="${tag.tagOption.name}"></li>         
```

+ room 객체에서 속성을 가져와서, 필드 값으로 설정한다.
+ hashTags 리스트에 포함된 태그들을 가져와서, 필드 값으로 설정한다.

## 웹 브라우저 실행
### Get /rooms/new 입력폼 요청
![입력 경로](https://github.com/StudyHunter/Backend/assets/57389368/a6f4036a-02ff-4a99-a791-7f56bb45d5af) <br>
다음과 같이 url을 요청했을 때 웹 브라우저를 확인해보자. <br> <br>
![입력폼 화면 캡처](https://github.com/StudyHunter/Backend/assets/57389368/0c4bdef0-debd-4c9b-bead-b08cf66a5350)

<br><br>

### 등록 후, room 상세정보 페이지로 리다이렉션된다.
![저장된 조회 api 경로](https://github.com/StudyHunter/Backend/assets/57389368/4e561cc8-42ca-4738-9016-7c9a8d0939f5) <br>
입력폼에 데이터를 입력하고 나서 submit 버튼을 누르면, 다음과 같이 url이 이동된다. <br><br> 
![조회 화면 캡처](https://github.com/StudyHunter/Backend/assets/57389368/829b82c8-905e-44a2-a547-3d380a5aedfa)


## mysql 조회
데이터가 MySQL 데이터베이스에 성공적으로 저장되었음을 확인할 수 있다. <br> <br>
![select hash_tag](https://github.com/StudyHunter/Backend/assets/57389368/b6d0d978-2746-4263-b930-5b568b6ca683)

![select room](https://github.com/StudyHunter/Backend/assets/57389368/f31b6c3a-d1bb-4714-a3a5-8e73141a3435)



