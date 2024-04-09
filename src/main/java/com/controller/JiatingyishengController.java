
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
 * 家庭医生
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiatingyisheng")
public class JiatingyishengController {
    private static final Logger logger = LoggerFactory.getLogger(JiatingyishengController.class);

    @Autowired
    private JiatingyishengService jiatingyishengService;


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
        PageUtils page = jiatingyishengService.queryPage(params);

        //字典表数据转换
        List<JiatingyishengView> list =(List<JiatingyishengView>)page.getList();
        for(JiatingyishengView c:list){
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
        JiatingyishengEntity jiatingyisheng = jiatingyishengService.selectById(id);
        if(jiatingyisheng !=null){
            //entity转view
            JiatingyishengView view = new JiatingyishengView();
            BeanUtils.copyProperties( jiatingyisheng , view );//把实体数据重构到view中

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
    public R save(@RequestBody JiatingyishengEntity jiatingyisheng, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiatingyisheng:{}",this.getClass().getName(),jiatingyisheng.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JiatingyishengEntity> queryWrapper = new EntityWrapper<JiatingyishengEntity>()
            .eq("username", jiatingyisheng.getUsername())
            .eq("password", jiatingyisheng.getPassword())
            .eq("qiantai_name", jiatingyisheng.getQiantaiName())
            .eq("sex_types", jiatingyisheng.getSexTypes())
            .eq("qiantai_phone", jiatingyisheng.getQiantaiPhone())
            .eq("qiantai_email", jiatingyisheng.getQiantaiEmail())
            .eq("qiantai_delete", jiatingyisheng.getQiantaiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiatingyishengEntity jiatingyishengEntity = jiatingyishengService.selectOne(queryWrapper);
        if(jiatingyishengEntity==null){
            jiatingyisheng.setCreateTime(new Date());
            jiatingyishengService.insert(jiatingyisheng);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiatingyishengEntity jiatingyisheng, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiatingyisheng:{}",this.getClass().getName(),jiatingyisheng.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<JiatingyishengEntity> queryWrapper = new EntityWrapper<JiatingyishengEntity>()
            .notIn("id",jiatingyisheng.getId())
            .andNew()
            .eq("username", jiatingyisheng.getUsername())
            .eq("password", jiatingyisheng.getPassword())
            .eq("qiantai_name", jiatingyisheng.getQiantaiName())
            .eq("sex_types", jiatingyisheng.getSexTypes())
            .eq("qiantai_phone", jiatingyisheng.getQiantaiPhone())
            .eq("qiantai_email", jiatingyisheng.getQiantaiEmail())
            .eq("qiantai_delete", jiatingyisheng.getQiantaiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiatingyishengEntity jiatingyishengEntity = jiatingyishengService.selectOne(queryWrapper);
        if("".equals(jiatingyisheng.getQiantaiPhoto()) || "null".equals(jiatingyisheng.getQiantaiPhoto())){
                jiatingyisheng.setQiantaiPhoto(null);
        }
        if(jiatingyishengEntity==null){
            jiatingyishengService.updateById(jiatingyisheng);//根据id更新
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
        jiatingyishengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JiatingyishengEntity> jiatingyishengList = new ArrayList<>();//上传的东西
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
                            JiatingyishengEntity jiatingyishengEntity = new JiatingyishengEntity();
//                            jiatingyishengEntity.setUsername(data.get(0));                    //账户 要改的
//                            //jiatingyishengEntity.setPassword("123456");//密码
//                            jiatingyishengEntity.setQiantaiName(data.get(0));                    //家庭医生负责人姓名 要改的
//                            jiatingyishengEntity.setQiantaiPhoto("");//照片
//                            jiatingyishengEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            jiatingyishengEntity.setQiantaiPhone(data.get(0));                    //联系方式 要改的
//                            jiatingyishengEntity.setQiantaiEmail(data.get(0));                    //邮箱 要改的
//                            jiatingyishengEntity.setQiantaiDelete(Integer.valueOf(data.get(0)));   //假删 要改的
//                            jiatingyishengEntity.setCreateTime(date);//时间
                            jiatingyishengList.add(jiatingyishengEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<JiatingyishengEntity> jiatingyishengEntities_username = jiatingyishengService.selectList(new EntityWrapper<JiatingyishengEntity>().in("username", seachFields.get("username")));
                        if(jiatingyishengEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiatingyishengEntity s:jiatingyishengEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiatingyishengService.insertBatch(jiatingyishengList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
