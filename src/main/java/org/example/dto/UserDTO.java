package org.example.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author Derek-huang
 */
@Data
public class UserDTO extends PageReq{

    @NotBlank(message = "昵称不能为空")
    private String name;

    @Length(min = 6,max = 12,message = "密码最小6位,最大12位")
    private String password;

    private String status;

    private String realName;

}
