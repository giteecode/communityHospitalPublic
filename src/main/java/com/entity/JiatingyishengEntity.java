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
 * 家庭医生
 *
 * @author 
 * @email
 */
@TableName("jiatingyisheng")
public class JiatingyishengEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiatingyishengEntity() {

	}

	public JiatingyishengEntity(T t) {
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
     * 账户
     */
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @TableField(value = "password")

    private String password;


    /**
     * 家庭医生负责人姓名
     */
    @TableField(value = "qiantai_name")

    private String qiantaiName;


    /**
     * 头像
     */
    @TableField(value = "qiantai_photo")

    private String qiantaiPhoto;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 联系方式
     */
    @TableField(value = "qiantai_phone")

    private String qiantaiPhone;


    /**
     * 邮箱
     */
    @TableField(value = "qiantai_email")

    private String qiantaiEmail;


    /**
     * 假删
     */
    @TableField(value = "qiantai_delete")

    private Integer qiantaiDelete;


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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：家庭医生负责人姓名
	 */
    public String getQiantaiName() {
        return qiantaiName;
    }


    /**
	 * 获取：家庭医生负责人姓名
	 */

    public void setQiantaiName(String qiantaiName) {
        this.qiantaiName = qiantaiName;
    }
    /**
	 * 设置：头像
	 */
    public String getQiantaiPhoto() {
        return qiantaiPhoto;
    }


    /**
	 * 获取：头像
	 */

    public void setQiantaiPhoto(String qiantaiPhoto) {
        this.qiantaiPhoto = qiantaiPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：联系方式
	 */
    public String getQiantaiPhone() {
        return qiantaiPhone;
    }


    /**
	 * 获取：联系方式
	 */

    public void setQiantaiPhone(String qiantaiPhone) {
        this.qiantaiPhone = qiantaiPhone;
    }
    /**
	 * 设置：邮箱
	 */
    public String getQiantaiEmail() {
        return qiantaiEmail;
    }


    /**
	 * 获取：邮箱
	 */

    public void setQiantaiEmail(String qiantaiEmail) {
        this.qiantaiEmail = qiantaiEmail;
    }
    /**
	 * 设置：假删
	 */
    public Integer getQiantaiDelete() {
        return qiantaiDelete;
    }


    /**
	 * 获取：假删
	 */

    public void setQiantaiDelete(Integer qiantaiDelete) {
        this.qiantaiDelete = qiantaiDelete;
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
        return "Jiatingyisheng{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", qiantaiName=" + qiantaiName +
            ", qiantaiPhoto=" + qiantaiPhoto +
            ", sexTypes=" + sexTypes +
            ", qiantaiPhone=" + qiantaiPhone +
            ", qiantaiEmail=" + qiantaiEmail +
            ", qiantaiDelete=" + qiantaiDelete +
            ", createTime=" + createTime +
        "}";
    }
}
