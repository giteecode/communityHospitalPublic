package com.entity.vo;

import com.entity.JiuzhenEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 就诊信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jiuzhen")
public class JiuzhenVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 就诊号
     */

    @TableField(value = "jiuzhen_jiuzhen")
    private String jiuzhenJiuzhen;


    /**
     * 就诊费用
     */

    @TableField(value = "jiuzhen_feiyong")
    private String jiuzhenFeiyong;


    /**
     * 科室
     */

    @TableField(value = "keshi_types")
    private Integer keshiTypes;


    /**
     * 日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 挂号备注
     */

    @TableField(value = "jiuzhen_text")
    private String jiuzhenText;


    /**
     * 挂号详情
     */

    @TableField(value = "jiuzhen_content")
    private String jiuzhenContent;


    /**
     * 创建时间 show1 show2 photoShow
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
	 * 设置：就诊号
	 */
    public String getJiuzhenJiuzhen() {
        return jiuzhenJiuzhen;
    }


    /**
	 * 获取：就诊号
	 */

    public void setJiuzhenJiuzhen(String jiuzhenJiuzhen) {
        this.jiuzhenJiuzhen = jiuzhenJiuzhen;
    }
    /**
	 * 设置：就诊费用
	 */
    public String getJiuzhenFeiyong() {
        return jiuzhenFeiyong;
    }


    /**
	 * 获取：就诊费用
	 */

    public void setJiuzhenFeiyong(String jiuzhenFeiyong) {
        this.jiuzhenFeiyong = jiuzhenFeiyong;
    }
    /**
	 * 设置：科室
	 */
    public Integer getKeshiTypes() {
        return keshiTypes;
    }


    /**
	 * 获取：科室
	 */

    public void setKeshiTypes(Integer keshiTypes) {
        this.keshiTypes = keshiTypes;
    }
    /**
	 * 设置：日期
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：日期
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：挂号备注
	 */
    public String getJiuzhenText() {
        return jiuzhenText;
    }


    /**
	 * 获取：挂号备注
	 */

    public void setJiuzhenText(String jiuzhenText) {
        this.jiuzhenText = jiuzhenText;
    }
    /**
	 * 设置：挂号详情
	 */
    public String getJiuzhenContent() {
        return jiuzhenContent;
    }


    /**
	 * 获取：挂号详情
	 */

    public void setJiuzhenContent(String jiuzhenContent) {
        this.jiuzhenContent = jiuzhenContent;
    }
    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
