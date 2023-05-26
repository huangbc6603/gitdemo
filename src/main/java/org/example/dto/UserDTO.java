package org.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author Derek-huang
 */
@Data
@ApiModel(value = "userDTO实体类",description = "接收前端传入的参数")
public class UserDTO extends PageReq{

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "昵称不能为空")
    private String name;

    @ApiModelProperty(value = "用户密码")
    @Length(min = 6,max = 12,message = "密码最小6位,最大12位")
    private String password;

    private String status;

    private String realName;

}
