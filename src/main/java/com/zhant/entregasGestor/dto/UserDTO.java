package com.zhant.entregasGestor.dto;

import com.zhant.entregasGestor.models.UserRole;

public record UserDTO(String username, String password, UserRole role) {

}
