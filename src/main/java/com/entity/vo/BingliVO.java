package com.entity.vo;

import com.entity.BingliEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 病例信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("bingli")
public class BingliVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 病例编号
     */

    @TableField(value = "bingli_uuid_number")
    private String bingliUuidNumber;


    /**
     * 病例名称
     */

    @TableField(value = "bingli_name")
    private String bingliName;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 病情
     */

    @TableField(value = "bingli_bingqing")
    private String bingliBingqing;


    /**
     * 检查项目
     */

    @TableField(value = "jianchaxiangmu")
    private String jianchaxiangmu;


    /**
     * 检查结果
     */

    @TableField(value = "jianchajieguo")
    private String jianchajieguo;


    /**
     * 药单
     */

    @TableField(value = "yaodan_text")
    private String yaodanText;


    /**
     * 病例附件
     */

    @TableField(value = "bingli_file")
    private String bingliFile;


    /**
     * 日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "bingli_time")
    private Date bingliTime;


    /**
     * 医生
     */

    @TableField(value = "yisheng_id")
    private Integer yishengId;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：病例编号
	 */
    public String getBingliUuidNumber() {
        return bingliUuidNumber;
    }


    /**
	 * 获取：病例编号
	 */

    public void setBingliUuidNumber(String bingliUuidNumber) {
        this.bingliUuidNumber = bingliUuidNumber;
    }
    /**
	 * 设置：病例名称
	 */
    public String getBingliName() {
        return bingliName;
    }


    /**
	 * 获取：病例名称
	 */

    public void setBingliName(String bingliName) {
        this.bingliName = bingliName;
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
	 * 设置：病情
	 */
    public String getBingliBingqing() {
        return bingliBingqing;
    }


    /**
	 * 获取：病情
	 */

    public void setBingliBingqing(String bingliBingqing) {
        this.bingliBingqing = bingliBingqing;
    }
    /**
	 * 设置：检查项目
	 */
    public String getJianchaxiangmu() {
        return jianchaxiangmu;
    }


    /**
	 * 获取：检查项目
	 */

    public void setJianchaxiangmu(String jianchaxiangmu) {
        this.jianchaxiangmu = jianchaxiangmu;
    }
    /**
	 * 设置：检查结果
	 */
    public String getJianchajieguo() {
        return jianchajieguo;
    }


    /**
	 * 获取：检查结果
	 */

    public void setJianchajieguo(String jianchajieguo) {
        this.jianchajieguo = jianchajieguo;
    }
    /**
	 * 设置：药单
	 */
    public String getYaodanText() {
        return yaodanText;
    }


    /**
	 * 获取：药单
	 */

    public void setYaodanText(String yaodanText) {
        this.yaodanText = yaodanText;
    }
    /**
	 * 设置：病例附件
	 */
    public String getBingliFile() {
        return bingliFile;
    }


    /**
	 * 获取：病例附件
	 */

    public void setBingliFile(String bingliFile) {
        this.bingliFile = bingliFile;
    }
    /**
	 * 设置：日期
	 */
    public Date getBingliTime() {
        return bingliTime;
    }


    /**
	 * 获取：日期
	 */

    public void setBingliTime(Date bingliTime) {
        this.bingliTime = bingliTime;
    }
    /**
	 * 设置：医生
	 */
    public Integer getYishengId() {
        return yishengId;
    }


    /**
	 * 获取：医生
	 */

    public void setYishengId(Integer yishengId) {
        this.yishengId = yishengId;
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

}
