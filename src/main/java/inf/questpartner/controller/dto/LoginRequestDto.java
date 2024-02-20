package inf.questpartner.controller.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String nickname;
    private String password;
}