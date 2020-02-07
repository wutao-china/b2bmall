package com.legensity.b2bmall.module.company.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 公司审核表
 * </p>
 *
 * @author wutao
 * @since 2020-02-06
 */
@Setter
@Getter
@TableName("t_company_apply")
public class CompanyApply implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "id", dataType = "Integer")
    @TableId(value="ID", type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty(notes = "公司id", dataType = "Integer")
    private Integer companyId;

    @ApiModelProperty(notes = "确认人id", dataType = "Integer")
    private Integer confirmerId;

    @ApiModelProperty(notes = "确认人姓名", dataType = "String")
    private String confirmerName;

    @ApiModelProperty(notes = "申请时间", dataType = "Date")
    private Date applyTime;

    @ApiModelProperty(notes = "确认时间", dataType = "Date")
    private Date confirmTime;

    @ApiModelProperty(notes = "状态 1 申请中，2 通过， 3 拒绝", dataType = "Integer")
    private Integer status;

    @ApiModelProperty(notes = "拒绝原因", dataType = "String")
    private String refuseReason;


    public static final String ID = "id";

    public static final String COMPANY_ID = "company_id";

    public static final String CONFIRMER_ID = "confirmer_id";

    public static final String CONFIRMER_NAME = "confirmer_name";

    public static final String APPLY_TIME = "apply_time";

    public static final String CONFIRM_TIME = "confirm_time";

    public static final String STATUS = "status";

    public static final String REFUSE_REASON = "refuse_reason";

}