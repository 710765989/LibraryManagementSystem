package generator.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
* 用户
* @TableName user
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {
    /**
    * 书名
    */
    @NotBlank(message="[书名]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    private String username;
    /**
    * 作者
    */
    @NotBlank(message="[作者]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    private String password;
    /**
    * 用户身份类型
    */
    @NotBlank(message="[用户身份类型]不能为空")
    @Size(max= 1,message="编码长度不能超过1")
    @Length(max= 1,message="编码长度不能超过1")
    private String type;
}
