<template>
  <div>
    <el-form
      class="detail-form-content"
      ref="ruleForm"
      :model="ruleForm"
      label-width="80px"
    >  
     <el-row>
                    <el-col :span="12">
           <el-form-item v-if="flag=='qiantai'"  label='工号' prop="qiantaiUuidNumber">
               <el-input v-model="ruleForm.qiantaiUuidNumber"  placeholder='工号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='qiantai'"  label='前台姓名' prop="qiantaiName">
               <el-input v-model="ruleForm.qiantaiName"  placeholder='前台姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='qiantai'" label='头像' prop="qiantaiPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.qiantaiPhoto?ruleForm.qiantaiPhoto:''"
                         @change="qiantaiPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='qiantai'"  label='联系方式' prop="qiantaiPhone">
               <el-input v-model="ruleForm.qiantaiPhone"  placeholder='联系方式' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='qiantai'"  label='邮箱' prop="qiantaiEmail">
               <el-input v-model="ruleForm.qiantaiEmail"  placeholder='邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='工号' prop="yishengUuidNumber">
               <el-input v-model="ruleForm.yishengUuidNumber"  placeholder='工号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='医生姓名' prop="yishengName">
               <el-input v-model="ruleForm.yishengName"  placeholder='医生姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='yisheng'" label='头像' prop="yishengPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.yishengPhoto?ruleForm.yishengPhoto:''"
                         @change="yishengPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='联系方式' prop="yishengPhone">
               <el-input v-model="ruleForm.yishengPhone"  placeholder='联系方式' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='医生身份证号' prop="yishengIdNumber">
               <el-input v-model="ruleForm.yishengIdNumber"  placeholder='医生身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yisheng'"  label='邮箱' prop="yishengEmail">
               <el-input v-model="ruleForm.yishengEmail"  placeholder='邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yonghu'"  label='用户姓名' prop="yonghuName">
               <el-input v-model="ruleForm.yonghuName"  placeholder='用户姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='yonghu'" label='头像' prop="yonghuPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.yonghuPhoto?ruleForm.yonghuPhoto:''"
                         @change="yonghuPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='yonghu'"  label='联系方式' prop="yonghuPhone">
               <el-input v-model="ruleForm.yonghuPhone"  placeholder='联系方式' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yonghu'"  label='用户身份证号' prop="yonghuIdNumber">
               <el-input v-model="ruleForm.yonghuIdNumber"  placeholder='用户身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='yonghu'"  label='邮箱' prop="yonghuEmail">
               <el-input v-model="ruleForm.yonghuEmail"  placeholder='邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-form-item v-if="flag=='users'" label="用户名" prop="username">
             <el-input v-model="ruleForm.username"
                       placeholder="用户名"></el-input>
         </el-form-item>
         <el-col :span="12">
             <el-form-item v-if="flag!='users'"  label="性别" prop="sexTypes">
                 <el-select v-model="ruleForm.sexTypes" placeholder="请选择性别">
                     <el-option
                             v-for="(item,index) in sexTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-col :span="24">
             <el-form-item>
                 <el-button type="primary" @click="onUpdateHandler">修 改</el-button>
             </el-form-item>
         </el-col>
     </el-row>
    </el-form>
  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isMobile,isPhone,isURL,checkIdCard } from "@/utils/validate";

export default {
  data() {
    return {
      ruleForm: {},
      flag: '',
      usersFlag: false,
      sexTypesOptions : [],
    };
  },
  mounted() {
    //获取当前登录用户的信息
    var table = this.$storage.get("sessionTable");
    this.sessionTable = this.$storage.get("sessionTable");
    this.role = this.$storage.get("role");
    if (this.role != "管理员"){
    }

    this.flag = table;
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
      } else {
        this.$message.error(data.msg);
      }
    });
      this.$http({
          url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=sex_types`,
          method: "get"
      }).then(({ data }) => {
          if (data && data.code === 0) {
          this.sexTypesOptions = data.data.list;
      } else {
          this.$message.error(data.msg);
      }
  });
  },
  methods: {
    qiantaiPhotoUploadChange(fileUrls) {
        this.ruleForm.qiantaiPhoto = fileUrls;
    },
    yishengPhotoUploadChange(fileUrls) {
        this.ruleForm.yishengPhoto = fileUrls;
    },
    yonghuPhotoUploadChange(fileUrls) {
        this.ruleForm.yonghuPhoto = fileUrls;
    },

    onUpdateHandler() {
                         if((!this.ruleForm.qiantaiUuidNumber)&& 'qiantai'==this.flag){
                             this.$message.error('工号不能为空');
                             return
                         }

                         if((!this.ruleForm.qiantaiName)&& 'qiantai'==this.flag){
                             this.$message.error('前台姓名不能为空');
                             return
                         }

                         if((!this.ruleForm.qiantaiPhoto)&& 'qiantai'==this.flag){
                             this.$message.error('头像不能为空');
                             return
                         }

                         if((!this.ruleForm.qiantaiPhone)&& 'qiantai'==this.flag){
                             this.$message.error('联系方式不能为空');
                             return
                         }

                             if( 'qiantai' ==this.flag && this.ruleForm.qiantaiEmail&&(!isEmail(this.ruleForm.qiantaiEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
                         if((!this.ruleForm.yishengUuidNumber)&& 'yisheng'==this.flag){
                             this.$message.error('工号不能为空');
                             return
                         }

                         if((!this.ruleForm.yishengName)&& 'yisheng'==this.flag){
                             this.$message.error('医生姓名不能为空');
                             return
                         }

                         if((!this.ruleForm.yishengPhoto)&& 'yisheng'==this.flag){
                             this.$message.error('头像不能为空');
                             return
                         }

                         if((!this.ruleForm.yishengPhone)&& 'yisheng'==this.flag){
                             this.$message.error('联系方式不能为空');
                             return
                         }

                         if((!this.ruleForm.yishengIdNumber)&& 'yisheng'==this.flag){
                             this.$message.error('医生身份证号不能为空');
                             return
                         }

                             if( 'yisheng' ==this.flag && this.ruleForm.yishengEmail&&(!isEmail(this.ruleForm.yishengEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
                         if((!this.ruleForm.yonghuName)&& 'yonghu'==this.flag){
                             this.$message.error('用户姓名不能为空');
                             return
                         }

                         if((!this.ruleForm.yonghuPhoto)&& 'yonghu'==this.flag){
                             this.$message.error('头像不能为空');
                             return
                         }

                         if((!this.ruleForm.yonghuPhone)&& 'yonghu'==this.flag){
                             this.$message.error('联系方式不能为空');
                             return
                         }

                         if((!this.ruleForm.yonghuIdNumber)&& 'yonghu'==this.flag){
                             this.$message.error('用户身份证号不能为空');
                             return
                         }

                             if( 'yonghu' ==this.flag && this.ruleForm.yonghuEmail&&(!isEmail(this.ruleForm.yonghuEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
        if((!this.ruleForm.sexTypes)&& this.flag !='users'){
            this.$message.error('性别不能为空');
            return
        }
      if('users'==this.flag && this.ruleForm.username.trim().length<1) {
        this.$message.error(`用户名不能为空`);
        return	
      }
      this.$http({
        url: `${this.$storage.get("sessionTable")}/update`,
        method: "post",
        data: this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "修改信息成功",
            type: "success",
            duration: 1500,
            onClose: () => {
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
</style>
