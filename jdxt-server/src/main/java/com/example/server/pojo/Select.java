package com.example.server.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("test_select")
@ApiModel(value="Select对象", description="选择题表")
public class Select implements Serializable {
    @ApiModelProperty(value="answer_A")
    private String answer_A;
    @ApiModelProperty(value="answer_B")
    private String answer_B;
    @ApiModelProperty(value="answer_C")
    private String answer_C;
    @ApiModelProperty(value="answer_D")
    private String answer_D;
    @ApiModelProperty(value="问题")
    private String question;
    @ApiModelProperty(value="选择题题号")
    private int select_id;
    @ApiModelProperty(value="所在章数")
    private String chapter;
    @ApiModelProperty(value="所在节数")
    private String section;
    @ApiModelProperty(value="正常答案")
    private String r_answer;


}
