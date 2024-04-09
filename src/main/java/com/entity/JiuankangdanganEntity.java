package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 健康档案
 *
 * @author 
 * @email
 */
@TableName("jiuankangdangan")
public class JiuankangdanganEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiuankangdanganEntity() {

	}

	public JiuankangdanganEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 档案标题
     */
    @TableField(value = "jiuankangdangan_name")

    private String jiuankangdanganName;


    /**
     * 其他成员
     */
    @TableField(value = "jiuankangdangan_qita")

    private String jiuankangdanganQita;


    /**
     * 档案单位
     */
    @TableField(value = "jiuankangdangan_types")

    private Integer jiuankangdanganTypes;


    /**
     * 记录时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 健康状况
     */
    @TableField(value = "jiuankangdangan_content")

    private String jiuankangdanganContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：档案标题
	 */
    public String getJiuankangdanganName() {
        return jiuankangdanganName;
    }


    /**
	 * 获取：档案标题
	 */

    public void setJiuankangdanganName(String jiuankangdanganName) {
        this.jiuankangdanganName = jiuankangdanganName;
    }
    /**
	 * 设置：其他成员
	 */
    public String getJiuankangdanganQita() {
        return jiuankangdanganQita;
    }


    /**
	 * 获取：其他成员
	 */

    public void setJiuankangdanganQita(String jiuankangdanganQita) {
        this.jiuankangdanganQita = jiuankangdanganQita;
    }
    /**
	 * 设置：档案单位
	 */
    public Integer getJiuankangdanganTypes() {
        return jiuankangdanganTypes;
    }


    /**
	 * 获取：档案单位
	 */

    public void setJiuankangdanganTypes(Integer jiuankangdanganTypes) {
        this.jiuankangdanganTypes = jiuankangdanganTypes;
    }
    /**
	 * 设置：记录时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：记录时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：健康状况
	 */
    public String getJiuankangdanganContent() {
        return jiuankangdanganContent;
    }


    /**
	 * 获取：健康状况
	 */

    public void setJiuankangdanganContent(String jiuankangdanganContent) {
        this.jiuankangdanganContent = jiuankangdanganContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Jiuankangdangan{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", jiuankangdanganName=" + jiuankangdanganName +
            ", jiuankangdanganQita=" + jiuankangdanganQita +
            ", jiuankangdanganTypes=" + jiuankangdanganTypes +
            ", insertTime=" + insertTime +
            ", jiuankangdanganContent=" + jiuankangdanganContent +
            ", createTime=" + createTime +
        "}";
    }
}
