package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("userlearn")
@ApiModel(value="Userlaern对象", description="用户学习记录表")
public class UserLearn implements Serializable {
    @ApiModelProperty(value = "uid")
    @TableId(value = "uid")
    private String uid;

    @ApiModelProperty(value = "l_chapter")
    private String l_chapter;

    @ApiModelProperty(value = "l_section")
    private String l_section;

}
