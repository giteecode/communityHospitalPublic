<template>
    <div>
        <div class="container">
            <div class="login-form" style="backgroundColor:rgba(183, 174, 174, 0.5);borderRadius:22px">
                <h1 class="h1" style="color:#000;fontSize:28px;">社区医院管理系统注册</h1>
                <el-form class="rgs-form" label-width="120px">
                        <el-form-item label="账号" class="input">
                            <el-input v-model="ruleForm.username" autocomplete="off" placeholder="账号"  />
                        </el-form-item>
                        <el-form-item label="密码" class="input">
                            <el-input type="password" v-model="ruleForm.password" autocomplete="off" show-password/>
                        </el-form-item>
                        <el-form-item label="重复密码" class="input">
                            <el-input type="password" v-model="ruleForm.repetitionPassword" autocomplete="off" show-password/>
                        </el-form-item>
                        <el-form-item label="前台姓名" class="input" v-if="tableName=='qiantai'">
                            <el-input v-model="ruleForm.qiantaiName" autocomplete="off" placeholder="前台姓名"  />
                        </el-form-item>
                        <el-form-item label="联系方式" class="input" v-if="tableName=='qiantai'">
                            <el-input v-model="ruleForm.qiantaiPhone" autocomplete="off" placeholder="联系方式"  />
                        </el-form-item>
                        <el-form-item label="邮箱" class="input" v-if="tableName=='qiantai'">
                            <el-input v-model="ruleForm.qiantaiEmail" autocomplete="off" placeholder="邮箱"  />
                        </el-form-item>
                        <el-form-item label="医生姓名" class="input" v-if="tableName=='yisheng'">
                            <el-input v-model="ruleForm.yishengName" autocomplete="off" placeholder="医生姓名"  />
                        </el-form-item>
                        <el-form-item label="联系方式" class="input" v-if="tableName=='yisheng'">
                            <el-input v-model="ruleForm.yishengPhone" autocomplete="off" placeholder="联系方式"  />
                        </el-form-item>
                        <el-form-item label="医生身份证号" class="input" v-if="tableName=='yisheng'">
                            <el-input v-model="ruleForm.yishengIdNumber" autocomplete="off" placeholder="医生身份证号"  />
                        </el-form-item>
                        <el-form-item label="邮箱" class="input" v-if="tableName=='yisheng'">
                            <el-input v-model="ruleForm.yishengEmail" autocomplete="off" placeholder="邮箱"  />
                        </el-form-item>
                        <el-form-item label="用户姓名" class="input" v-if="tableName=='yonghu'">
                            <el-input v-model="ruleForm.yonghuName" autocomplete="off" placeholder="用户姓名"  />
                        </el-form-item>
                        <el-form-item label="联系方式" class="input" v-if="tableName=='yonghu'">
                            <el-input v-model="ruleForm.yonghuPhone" autocomplete="off" placeholder="联系方式"  />
                        </el-form-item>
                        <el-form-item label="用户身份证号" class="input" v-if="tableName=='yonghu'">
                            <el-input v-model="ruleForm.yonghuIdNumber" autocomplete="off" placeholder="用户身份证号"  />
                        </el-form-item>
                        <el-form-item label="邮箱" class="input" v-if="tableName=='yonghu'">
                            <el-input v-model="ruleForm.yonghuEmail" autocomplete="off" placeholder="邮箱"  />
                        </el-form-item>
                        <div style="display: flex;flex-wrap: wrap;width: 100%;justify-content: center;">
                            <el-button class="btn" type="primary" @click="login()">注册</el-button>
                            <el-button class="btn close" type="primary" @click="close()">取消</el-button>
                        </div>
                </el-form>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                ruleForm: {
                },
                tableName:"",
                rules: {},
                sexTypesOptions : [],
            };
        },
        mounted(){
            let table = this.$storage.get("loginTable");
            this.tableName = table;

            //级联表的下拉框查询

        },
        methods: {
            // 获取uuid
            getUUID () {
                return new Date().getTime();
            },
            close(){
                this.$router.push({ path: "/login" });
            },
            // 注册
            login() {

                            if((!this.ruleForm.username)){
                                this.$message.error('账号不能为空');
                                return
                            }
                            if((!this.ruleForm.password)){
                                this.$message.error('密码不能为空');
                                return
                            }
                            if((!this.ruleForm.repetitionPassword)){
                                this.$message.error('重复密码不能为空');
                                return
                            }
                            if(this.ruleForm.repetitionPassword != this.ruleForm.password){
                                this.$message.error('密码和重复密码不一致');
                                return
                            }
                            if((!this.ruleForm.qiantaiName)&& 'qiantai'==this.tableName){
                                this.$message.error('前台姓名不能为空');
                                return
                            }
                             if('qiantai' == this.tableName && this.ruleForm.qiantaiPhone&&(!this.$validate.isMobile(this.ruleForm.qiantaiPhone))){
                                 this.$message.error('手机应输入手机格式');
                                 return
                             }
                            if('qiantai' == this.tableName && this.ruleForm.qiantaiEmail&&(!this.$validate.isEmail(this.ruleForm.qiantaiEmail))){
                                this.$message.error("邮箱应输入邮件格式");
                                return
                            }
                            if((!this.ruleForm.yishengName)&& 'yisheng'==this.tableName){
                                this.$message.error('医生姓名不能为空');
                                return
                            }
                             if('yisheng' == this.tableName && this.ruleForm.yishengPhone&&(!this.$validate.isMobile(this.ruleForm.yishengPhone))){
                                 this.$message.error('手机应输入手机格式');
                                 return
                             }
                            if((!this.ruleForm.yishengIdNumber)&& 'yisheng'==this.tableName){
                                this.$message.error('医生身份证号不能为空');
                                return
                            }
                            if('yisheng' == this.tableName && this.ruleForm.yishengEmail&&(!this.$validate.isEmail(this.ruleForm.yishengEmail))){
                                this.$message.error("邮箱应输入邮件格式");
                                return
                            }
                            if((!this.ruleForm.yonghuName)&& 'yonghu'==this.tableName){
                                this.$message.error('用户姓名不能为空');
                                return
                            }
                             if('yonghu' == this.tableName && this.ruleForm.yonghuPhone&&(!this.$validate.isMobile(this.ruleForm.yonghuPhone))){
                                 this.$message.error('手机应输入手机格式');
                                 return
                             }
                            if((!this.ruleForm.yonghuIdNumber)&& 'yonghu'==this.tableName){
                                this.$message.error('用户身份证号不能为空');
                                return
                            }
                            if('yonghu' == this.tableName && this.ruleForm.yonghuEmail&&(!this.$validate.isEmail(this.ruleForm.yonghuEmail))){
                                this.$message.error("邮箱应输入邮件格式");
                                return
                            }
                this.$http({
                    url: `${this.tableName}/register`,
                    method: "post",
                    data:this.ruleForm
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                    this.$message({
                        message: "注册成功,请登录后在个人中心页面补充个人数据",
                        type: "success",
                        duration: 1500,
                        onClose: () => {
                        this.$router.replace({ path: "/login" });
                }
                });
                } else {
                    this.$message.error(data.msg);
                }
            });
            }
        }
    };
</script>
<style lang="scss" scoped>
    .el-radio__input.is-checked .el-radio__inner {
        border-color: #00c292;
        background: #00c292;
    }

    .el-radio__input.is-checked .el-radio__inner {
        border-color: #00c292;
        background: #00c292;
    }

    .el-radio__input.is-checked .el-radio__inner {
        border-color: #00c292;
        background: #00c292;
    }

    .el-radio__input.is-checked+.el-radio__label {
        color: #00c292;
    }

    .el-radio__input.is-checked+.el-radio__label {
        color: #00c292;
    }

    .el-radio__input.is-checked+.el-radio__label {
        color: #00c292;
    }

    .h1 {
        margin-top: 10px;
    }

    body {
        padding: 0;
        margin: 0;
    }

    // .container {
       //    min-height: 100vh;
       //    text-align: center;
       //    // background-color: #00c292;
       //    padding-top: 20vh;
       //    background-image: url(../assets/img/bg.jpg);
       //    background-size: 100% 100%;
       //    opacity: 0.9;
       //  }

    // .login-form:before {
       // 	vertical-align: middle;
       // 	display: inline-block;
       // }

    // .login-form {
       // 	max-width: 500px;
       // 	padding: 20px 0;
       // 	width: 80%;
       // 	position: relative;
       // 	margin: 0 auto;

    // 	.label {
          // 		min-width: 60px;
          // 	}

    // 	.input-group {
          // 		max-width: 500px;
          // 		padding: 20px 0;
          // 		width: 80%;
          // 		position: relative;
          // 		margin: 0 auto;
          // 		display: flex;
          // 		align-items: center;

    // 		.input-container {
              // 			display: inline-block;
              // 			width: 100%;
              // 			text-align: left;
              // 			margin-left: 10px;
              // 		}

    // 		.icon {
              // 			width: 30px;
              // 			height: 30px;
              // 		}

    // 		.input {
              // 			position: relative;
              // 			z-index: 2;
              // 			float: left;
              // 			width: 100%;
              // 			margin-bottom: 0;
              // 			box-shadow: none;
              // 			border-top: 0px solid #ccc;
              // 			border-left: 0px solid #ccc;
              // 			border-right: 0px solid #ccc;
              // 			border-bottom: 1px solid #ccc;
              // 			padding: 0px;
              // 			resize: none;
              // 			border-radius: 0px;
              // 			display: block;
              // 			width: 100%;
              // 			height: 34px;
              // 			padding: 6px 12px;
              // 			font-size: 14px;
              // 			line-height: 1.42857143;
              // 			color: #555;
              // 			background-color: #fff;
              // 		}

    // 	}
    // }

    .nk-navigation {
        margin-top: 15px;

    a {
        display: inline-block;
        color: #fff;
        background: rgba(255, 255, 255, .2);
        width: 100px;
        height: 50px;
        border-radius: 30px;
        text-align: center;
        display: flex;
        align-items: center;
        margin: 0 auto;
        justify-content: center;
        padding: 0 20px;
    }

    .icon {
        margin-left: 10px;
        width: 30px;
        height: 30px;
    }
    }

    .register-container {
        margin-top: 10px;

    a {
        display: inline-block;
        color: #fff;
        max-width: 500px;
        height: 50px;
        border-radius: 30px;
        text-align: center;
        display: flex;
        align-items: center;
        margin: 0 auto;
        justify-content: center;
        padding: 0 20px;

    div {
        margin-left: 10px;
    }
    }
    }

    .container {
        background-image: url("http://codegen.caihongy.cn/20210413/1043288468a242e083bddea1af7df208.jpg");
        height: 100vh;
        background-position: center center;
        background-size: cover;
        background-repeat: no-repeat;

    .login-form {
        right: 50%;
        top: 50%;
        height: auto;
        transform: translate3d(50%, -50%, 0);
        border-radius: 10px;
        background-color: rgba(255,255,255,.5);
        width: 420px;
        padding: 30px 30px 40px 30px;
        font-size: 14px;
        font-weight: 500;

    .h1 {
        margin: 0;
        text-align: center;
        line-height: 54px;
        font-size: 24px;
        color: #000;
    }

    .rgs-form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

    .input {
        width: 100%;

    & /deep/ .el-form-item__label {
          line-height: 40px;
          color: rgba(17, 16, 16, 1);
          font-size: #606266;
      }

    & /deep/ .el-input__inner {
          height: 40px;
          color: rgba(23, 24, 26, 1);
          font-size: 14px;
          border-width: 1px;
          border-style: solid;
          border-color: #606266;
          border-radius: 22px;
          background-color: #fff;
      }
    }

    .btn {
        margin: 0 10px;
        width: 88px;
        height: 44px;
        color: #fff;
        font-size: 14px;
        border-width: 1px;
        border-style: solid;
        border-color: #409EFF;
        border-radius: 22px;
        background-color: #409EFF;
    }

    .close {
        margin: 0 10px;
        width: 88px;
        height: 44px;
        color: #409EFF;
        font-size: 14px;
        border-width: 1px;
        border-style: solid;
        border-color: #409EFF;
        border-radius: 22px;
        background-color: #FFF;
    }

    }
    }
    }
</style>
