package com.lynn.oauth_demo.vo;

import lombok.Getter;
import lombok.Setter;


/**
* members 的 Vo
*/
@Getter
@Setter
public class MembersVo {
    /**  */
    private Object accountStatus;
    /**  */
    private java.time.LocalDateTime createdAt;
    /**  */
    private String email;
    /**  */
    private String fullName;
    /**  */
    private java.time.LocalDateTime lastLogin;
    /**  */
    private Integer memberId;
    /**  */
    private String passwordHash;
    /**  */
    private String phoneNumber;
    /**  */
    private java.time.LocalDateTime registrationDate;
    /**  */
    private java.time.LocalDateTime updatedAt;
    /**  */
    private String username;

}
