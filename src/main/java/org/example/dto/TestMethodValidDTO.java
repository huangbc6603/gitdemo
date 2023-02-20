package org.example.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author Derek-huang
 */
@Data
public class TestMethodValidDTO {

    private int id;

    @NotEmpty(message="用户名不能为空")
    @Pattern(regexp="^\\w{6,18}$",message="用户名必须由6到18位的数字字母或者下划线组成")
    private String username;

    @NotNull
    @Length(min=6,max=32,message="密码必须为6到32位的字符串")
    private String password;

    @Past
    private Date birthday;

    @NotNull(message="年龄不能为空")
    @Max(value=1)
    @Min(value=1)
    private Integer age;

    @DecimalMax(value="2.5")
    @DecimalMin(value="1.0")
    private Double height;

    @Email
    private String email;


}
