package com.example.server.pojo;

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
@TableName("test_short_answer")
@ApiModel(value="Short_Answer对象", description="简答题表")
public class ShortAnswer implements Serializable {
    @ApiModelProperty(value="问题")
    private String question;
    @ApiModelProperty(value="简答题题号")
    private int short_answer_id;
    @ApiModelProperty(value="所在章数")
    private String chapter;
    @ApiModelProperty(value="所在节数")
    private String section;
    @ApiModelProperty(value="正常答案")
    private String answer;
}
