## 基于Java+Springboot+Vue的社区医院管理系统(源代码+数据库+万字论文)134

## 一、系统介绍
本项目前后端分离，分为管理员、用户、医生、前台四种角色

### 1、用户：
- 注册、登录、个人病历、家庭医生、健康档案、就诊信息、个人信息
### 2、前台：
- 注册、登录、病历信息管理、健康档案管理、就诊信息管理、药品管理、个人信息
### 3、医生：
和前台功能一样，多了病历新增
### 4、管理员：
- 用户管理、管理员管理、病历管理、基础数据管理、家庭医生管理、健康档案管理、就诊信息管理、前台管理、药品管理、医生管理、用户管理

## 二、所用技术

后端技术栈：

- Springboot
- MybatisPlus
- Mysql
- Maven

前端技术栈：

- Vue 
- Vue-router 
- axios 
- element-ui

## 三、环境介绍

基础环境:IDEA/eclipse, JDK1.8, Mysql5.7及以上, Maven3.6, node14, navicat

所有项目以及源代码本人均调试运行无问题 可支持远程调试运行

## 四、页面截图
文档截图
![contents](./picture/picture0.png)
### 1、前台
![contents](./picture/picture1.png)
![contents](./picture/picture2.png)
![contents](./picture/picture3.png)
![contents](./picture/picture4.png)
![contents](./picture/picture5.png)
![contents](./picture/picture6.png)
![contents](./picture/picture7.png)
### 2、用户
![contents](./picture/picture8.png)
![contents](./picture/picture9.png)
![contents](./picture/picture10.png)
![contents](./picture/picture11.png)
### 3、医生
![contents](./picture/picture12.png)
![contents](./picture/picture13.png)
![contents](./picture/picture14.png)
![contents](./picture/picture15.png)
### 4、管理员：
![contents](./picture/picture16.png)
![contents](./picture/picture17.png)
![contents](./picture/picture18.png)
![contents](./picture/picture36.png)
![contents](./picture/picture19.png)
![contents](./picture/picture20.png)
![contents](./picture/picture21.png)
![contents](./picture/picture22.png)
![contents](./picture/picture23.png)
![contents](./picture/picture24.png)
![contents](./picture/picture25.png)
![contents](./picture/picture26.png)

## 五、浏览地址

后台登录页面: http://localhost:8081

- 管理员账号/密码：admin/admin
- 用户账号/密码：a1/123456
- 医生账号/密码：a1/123456
- 前台账号/密码：a1/123456

## 六、部署教程

1. 使用Navicat或者其它工具，在mysql中创建对应名称的数据库，并执行项目的sql

2. 使用IDEA/Eclipse导入shequyiyuan项目，导入时，若为maven项目请选择maven; 等待依赖下载完成

3. 修改resources目录下面application.yml里面的数据库配置

4. src/main/java/com/shequyiyuanApplication.java启动后端

5. vscode或idea打开admin项目

6. 在编译器中打开terminal，执行npm install 依赖下载完成后执行 npm run serve,执行成功后会显示后台管理访问地址

