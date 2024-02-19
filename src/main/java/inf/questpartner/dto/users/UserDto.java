package inf.questpartner.dto.users;

import inf.questpartner.domain.users.common.RoleType;
import inf.questpartner.domain.users.common.UserStatus;
import inf.questpartner.domain.users.user.User;



public record UserDto(
        String username,
        String password,
        UserStatus status,
        String email,
        RoleType roleType
//        List<UserDetailsDto> userDetails, // 계정에서의 참조는 없앤다.

) {

    // factory method of 선언
    public static UserDto of(String username, String password, UserStatus status, String email, RoleType roleType) {
        return new UserDto(username, password, status, email, roleType);
    }

    // security에서 사용할 팩토리 메서드
    public static UserDto of(String email) {
        return new UserDto(
                null, null, null, email, null
        );
    }

    // Principal에서 사용할 factory method of 선언
    public static UserDto of(String username, String password, String email, RoleType roleType) {
        return new UserDto(
                username, password, null, email, roleType);
    }

    // 서비스 레이어에서 entity를 dto로 변환시켜주는 코드
    public static UserDto fromEntity(User entity) {
        return UserDto.of(
                entity.getNickname(),
                entity.getPassword(),
                entity.getUserStatus(),
                entity.getEmail(),
                RoleType.USER
//                userDetails,

        );
    }

    // request -> dto 변환 메서드
    public static UserDto fromRequest(UserRequest request) {
        return of(
                request.getUsername(),
                request.getPassword(),
                request.getStatus(),
                request.getEmail(),
                request.getRoleType()
        );
    }

}