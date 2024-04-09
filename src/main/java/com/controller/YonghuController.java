
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
 * 用户
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yonghu")
public class YonghuController {
    private static final Logger logger = LoggerFactory.getLogger(YonghuController.class);

    @Autowired
    private YonghuService yonghuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

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
        params.put("yonghuDeleteStart",1);params.put("yonghuDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = yonghuService.queryPage(params);

        //字典表数据转换
        List<YonghuView> list =(List<YonghuView>)page.getList();
        for(YonghuView c:list){
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
        YonghuEntity yonghu = yonghuService.selectById(id);
        if(yonghu !=null){
            //entity转view
            YonghuView view = new YonghuView();
            BeanUtils.copyProperties( yonghu , view );//把实体数据重构到view中

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
    public R save(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yonghu:{}",this.getClass().getName(),yonghu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YonghuEntity> queryWrapper = new EntityWrapper<YonghuEntity>()
            .eq("username", yonghu.getUsername())
            .or()
            .eq("yonghu_phone", yonghu.getYonghuPhone())
            .or()
            .eq("yonghu_id_number", yonghu.getYonghuIdNumber())
            .andNew()
            .eq("yonghu_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuEntity yonghuEntity = yonghuService.selectOne(queryWrapper);
        if(yonghuEntity==null){
            yonghu.setYonghuDelete(1);
            yonghu.setCreateTime(new Date());
            yonghu.setPassword("123456");
            yonghuService.insert(yonghu);
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式或者用户身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YonghuEntity yonghu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yonghu:{}",this.getClass().getName(),yonghu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<YonghuEntity> queryWrapper = new EntityWrapper<YonghuEntity>()
            .notIn("id",yonghu.getId())
            .andNew()
            .eq("username", yonghu.getUsername())
            .or()
            .eq("yonghu_phone", yonghu.getYonghuPhone())
            .or()
            .eq("yonghu_id_number", yonghu.getYonghuIdNumber())
            .andNew()
            .eq("yonghu_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuEntity yonghuEntity = yonghuService.selectOne(queryWrapper);
        if("".equals(yonghu.getYonghuPhoto()) || "null".equals(yonghu.getYonghuPhoto())){
                yonghu.setYonghuPhoto(null);
        }
        if(yonghuEntity==null){
            yonghuService.updateById(yonghu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者联系方式或者用户身份证号已经被使用");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<YonghuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            YonghuEntity yonghuEntity = new YonghuEntity();
            yonghuEntity.setId(id);
            yonghuEntity.setYonghuDelete(2);
            list.add(yonghuEntity);
        }
        if(list != null && list.size() >0){
            yonghuService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<YonghuEntity> yonghuList = new ArrayList<>();//上传的东西
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
                            YonghuEntity yonghuEntity = new YonghuEntity();
//                            yonghuEntity.setUsername(data.get(0));                    //账户 要改的
//                            //yonghuEntity.setPassword("123456");//密码
//                            yonghuEntity.setYonghuName(data.get(0));                    //用户姓名 要改的
//                            yonghuEntity.setYonghuPhoto("");//照片
//                            yonghuEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            yonghuEntity.setYonghuPhone(data.get(0));                    //联系方式 要改的
//                            yonghuEntity.setYonghuIdNumber(data.get(0));                    //用户身份证号 要改的
//                            yonghuEntity.setYonghuEmail(data.get(0));                    //邮箱 要改的
//                            yonghuEntity.setYonghuDelete(1);//逻辑删除字段
//                            yonghuEntity.setCreateTime(date);//时间
                            yonghuList.add(yonghuEntity);


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
                                //联系方式
                                if(seachFields.containsKey("yonghuPhone")){
                                    List<String> yonghuPhone = seachFields.get("yonghuPhone");
                                    yonghuPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> yonghuPhone = new ArrayList<>();
                                    yonghuPhone.add(data.get(0));//要改的
                                    seachFields.put("yonghuPhone",yonghuPhone);
                                }
                                //用户身份证号
                                if(seachFields.containsKey("yonghuIdNumber")){
                                    List<String> yonghuIdNumber = seachFields.get("yonghuIdNumber");
                                    yonghuIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yonghuIdNumber = new ArrayList<>();
                                    yonghuIdNumber.add(data.get(0));//要改的
                                    seachFields.put("yonghuIdNumber",yonghuIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<YonghuEntity> yonghuEntities_username = yonghuService.selectList(new EntityWrapper<YonghuEntity>().in("username", seachFields.get("username")).eq("yonghu_delete", 1));
                        if(yonghuEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuEntity s:yonghuEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //联系方式
                        List<YonghuEntity> yonghuEntities_yonghuPhone = yonghuService.selectList(new EntityWrapper<YonghuEntity>().in("yonghu_phone", seachFields.get("yonghuPhone")).eq("yonghu_delete", 1));
                        if(yonghuEntities_yonghuPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuEntity s:yonghuEntities_yonghuPhone){
                                repeatFields.add(s.getYonghuPhone());
                            }
                            return R.error(511,"数据库的该表中的 [联系方式] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //用户身份证号
                        List<YonghuEntity> yonghuEntities_yonghuIdNumber = yonghuService.selectList(new EntityWrapper<YonghuEntity>().in("yonghu_id_number", seachFields.get("yonghuIdNumber")).eq("yonghu_delete", 1));
                        if(yonghuEntities_yonghuIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuEntity s:yonghuEntities_yonghuIdNumber){
                                repeatFields.add(s.getYonghuIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [用户身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yonghuService.insertBatch(yonghuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        YonghuEntity yonghu = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("username", username));
        if(yonghu==null || !yonghu.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(yonghu.getYonghuDelete() != 1)
            return R.error("账户已被删除");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(yonghu.getId(),username, "yonghu", "用户");
        R r = R.ok();
        r.put("token", token);
        r.put("role","用户");
        r.put("username",yonghu.getYonghuName());
        r.put("tableName","yonghu");
        r.put("userId",yonghu.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody YonghuEntity yonghu){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<YonghuEntity> queryWrapper = new EntityWrapper<YonghuEntity>()
            .eq("username", yonghu.getUsername())
            .or()
            .eq("yonghu_phone", yonghu.getYonghuPhone())
            .or()
            .eq("yonghu_id_number", yonghu.getYonghuIdNumber())
            .andNew()
            .eq("yonghu_delete", 1)
            ;
        YonghuEntity yonghuEntity = yonghuService.selectOne(queryWrapper);
        if(yonghuEntity != null)
            return R.error("账户或者联系方式或者用户身份证号已经被使用");
        yonghu.setYonghuDelete(1);
        yonghu.setCreateTime(new Date());
        yonghuService.insert(yonghu);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        YonghuEntity yonghu = new YonghuEntity();
        yonghu.setPassword("123456");
        yonghu.setId(id);
        yonghuService.updateById(yonghu);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        YonghuEntity yonghu = yonghuService.selectOne(new EntityWrapper<YonghuEntity>().eq("username", username));
        if(yonghu!=null){
            yonghu.setPassword("123456");
            boolean b = yonghuService.updateById(yonghu);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrYonghu(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        YonghuEntity yonghu = yonghuService.selectById(id);
        if(yonghu !=null){
            //entity转view
            YonghuView view = new YonghuView();
            BeanUtils.copyProperties( yonghu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }





}
