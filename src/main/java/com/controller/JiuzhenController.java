
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 就诊信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiuzhen")
public class JiuzhenController {
    private static final Logger logger = LoggerFactory.getLogger(JiuzhenController.class);

    @Autowired
    private JiuzhenService jiuzhenService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;

    @Autowired
    private QiantaiService qiantaiService;
    @Autowired
    private YishengService yishengService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("前台".equals(role))
            params.put("qiantaiId",request.getSession().getAttribute("userId"));
        else if("医生".equals(role))
            params.put("yishengId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = jiuzhenService.queryPage(params);

        //字典表数据转换
        List<JiuzhenView> list =(List<JiuzhenView>)page.getList();
        for(JiuzhenView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiuzhenEntity jiuzhen = jiuzhenService.selectById(id);
        if(jiuzhen !=null){
            //entity转view
            JiuzhenView view = new JiuzhenView();
            BeanUtils.copyProperties( jiuzhen , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(jiuzhen.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JiuzhenEntity jiuzhen, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiuzhen:{}",this.getClass().getName(),jiuzhen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jiuzhen.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JiuzhenEntity> queryWrapper = new EntityWrapper<JiuzhenEntity>()
            .eq("yonghu_id", jiuzhen.getYonghuId())
            .eq("jiuzhen_jiuzhen", jiuzhen.getJiuzhenJiuzhen())
            .eq("jiuzhen_feiyong", jiuzhen.getJiuzhenFeiyong())
            .eq("keshi_types", jiuzhen.getKeshiTypes())
            .eq("jiuzhen_text", jiuzhen.getJiuzhenText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiuzhenEntity jiuzhenEntity = jiuzhenService.selectOne(queryWrapper);
        if(jiuzhenEntity==null){
            jiuzhen.setInsertTime(new Date());
            jiuzhen.setCreateTime(new Date());
            jiuzhenService.insert(jiuzhen);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiuzhenEntity jiuzhen, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiuzhen:{}",this.getClass().getName(),jiuzhen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jiuzhen.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JiuzhenEntity> queryWrapper = new EntityWrapper<JiuzhenEntity>()
            .notIn("id",jiuzhen.getId())
            .andNew()
            .eq("yonghu_id", jiuzhen.getYonghuId())
            .eq("jiuzhen_jiuzhen", jiuzhen.getJiuzhenJiuzhen())
            .eq("jiuzhen_feiyong", jiuzhen.getJiuzhenFeiyong())
            .eq("keshi_types", jiuzhen.getKeshiTypes())
            .eq("jiuzhen_text", jiuzhen.getJiuzhenText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiuzhenEntity jiuzhenEntity = jiuzhenService.selectOne(queryWrapper);
        if(jiuzhenEntity==null){
            jiuzhenService.updateById(jiuzhen);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jiuzhenService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JiuzhenEntity> jiuzhenList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JiuzhenEntity jiuzhenEntity = new JiuzhenEntity();
//                            jiuzhenEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jiuzhenEntity.setJiuzhenJiuzhen(data.get(0));                    //就诊号 要改的
//                            jiuzhenEntity.setJiuzhenFeiyong(data.get(0));                    //就诊费用 要改的
//                            jiuzhenEntity.setKeshiTypes(Integer.valueOf(data.get(0)));   //科室 要改的
//                            jiuzhenEntity.setInsertTime(date);//时间
//                            jiuzhenEntity.setJiuzhenText(data.get(0));                    //挂号备注 要改的
//                            jiuzhenEntity.setJiuzhenContent("");//照片
//                            jiuzhenEntity.setCreateTime(date);//时间
                            jiuzhenList.add(jiuzhenEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jiuzhenService.insertBatch(jiuzhenList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
