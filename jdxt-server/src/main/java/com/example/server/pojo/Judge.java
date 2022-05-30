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
@TableName("test_judge")
@ApiModel(value="Judge对象", description="判断题表")
public class Judge implements Serializable {
    @ApiModelProperty(value="问题")
    private String question;
    @ApiModelProperty(value="判断题题号")
    private int judge_id;
    @ApiModelProperty(value="所在章数")
    private String chapter;
    @ApiModelProperty(value="所在节数")
    private String section;
    @ApiModelProperty(value="正常答案")
    private String r_answer;


}
