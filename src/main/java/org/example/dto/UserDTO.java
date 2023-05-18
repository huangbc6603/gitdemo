package org.example.dto;

import lombok.Data;

/**
 * @author Derek-huang
 */
@Data
public class UserDTO extends PageReq{

    private String name;

    private String password;

    private String status;

    private String realName;

}
