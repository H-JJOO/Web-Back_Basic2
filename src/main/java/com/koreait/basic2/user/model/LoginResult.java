package com.koreait.basic2.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResult {
    private final int result;
    private final UserEntity loginUser;
}
