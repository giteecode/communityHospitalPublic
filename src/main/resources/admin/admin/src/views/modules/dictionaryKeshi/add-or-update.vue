<template>
    <div class="addEdit-block">
        <el-form
                class="detail-form-content"
                ref="ruleForm"
                :model="ruleForm"
                :rules="rules"
                label-width="80px"
                :style="{backgroundColor:addEditForm.addEditBoxColor}"
        >
            <el-row>
                <el-col :span="12">
                    <el-form-item class="input" v-if="type!='info'"  label="科室编码" prop="codeIndex">
                        <el-input v-model="ruleForm.codeIndex"
                                  placeholder="科室编码" clearable  :readonly="ro.codeIndex"></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="科室编码" prop="codeIndex">
                            <el-input v-model="ruleForm.codeIndex"
                                      placeholder="科室编码" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12">
                    <el-form-item class="input" v-if="type!='info'"  label="科室" prop="indexName">
                        <el-input v-model="ruleForm.indexName"
                                  placeholder="科室" clearable  :readonly="ro.indexName"></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="科室" prop="indexName">
                            <el-input v-model="ruleForm.indexName"
                                      placeholder="科室" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
            </el-row>
            <el-form-item class="btn">
                <el-button v-if="type!='info'" type="primary" class="btn-success" @click="onSubmit">提交</el-button>
                <el-button v-if="type!='info'" class="btn-close" @click="back()">取消</el-button>
                <el-button v-if="type=='info'" class="btn-close" @click="back()">返回</el-button>
            </el-form-item>
        </el-form>


    </div>
</template>
<script>
    import styleJs from "../../../utils/style.js";
    // 数字，邮件，手机，url，身份证校验
    import { isNumber,isIntNumer,isEmail,isPhone, isMobile,isURL,checkIdCard } from "@/utils/validate";
    export default {
        data() {
            let self = this
            return {
                addEditForm:null,
                id: '',
                type: '',
                ro:{
                    codeIndex : true,
                    indexName : false,
                    superId : false,
                    beizhu : false,
                },
                ruleForm: {
                    codeIndex: '',
                    indexName: '',
                    superId : '',
                    beizhu : '',
                },
                rules: {
                }
            };
        },
        props: ["parent"],
        computed: {
        },
        created() {
            this.addEditForm = styleJs.addStyle();
            this.addEditStyleChange()
            this.addEditUploadStyleChange()
        },
        methods: {
            // 初始化
            init(id,type) {
                if (id) {
                    this.id = id;
                    this.type = type;
                }
                if(this.type=='info'||this.type=='else'){
                    this.info(id);
                }else{
                    //查询最大值 start
                    this.$http({
                        url: `dictionary/maxCodeIndex`,
                        method: "post",
                        data: {"dicCode":"keshi_types"}
                    }).then(({ data }) => {
                        if (data && data.code === 0) {
                            this.ruleForm.codeIndex = data.maxCodeIndex;
                        } else {
                            this.$message.error(data.msg);
                        }
                    });
                    //查询最大值 end
                }
            },
            // 多级联动参数
            info(id) {
                this.$http({
                    url: `dictionary/info/${id}`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                    this.ruleForm = data.data;
                    //解决前台上传图片后台不显示的问题
                    let reg=new RegExp('../../../upload','g')//g代表全部
                } else {
                    this.$message.error(data.msg);
                }
            });
            },
            // 提交
            onSubmit() {
                if((!this.ruleForm.indexName)){
                    this.$message.error('科室不能为空');
                    return
                }
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        let ruleForm = this.ruleForm;
                        ruleForm["dicCode"]="keshi_types";
                        ruleForm["dicName"]="科室";
                        this.$http({
                            url: `dictionary/${!this.ruleForm.id ? "save" : "update"}`,
                            method: "post",
                            data: ruleForm
                        }).then(({ data }) => {
                            if (data && data.code === 0) {
                                this.$message({
                                    message: "操作成功",
                                    type: "success",
                                    duration: 1500,
                                    onClose: () => {
                                        this.parent.showFlag = true;
                                        this.parent.addOrUpdateFlag = false;
                                        this.parent.dictionaryCrossAddOrUpdateFlag = false;
                                        this.parent.search();
                                        this.parent.contentStyleChange();

                                    }
                                });
                            } else {
                                this.$message.error(data.msg);
                            }
                        });
                    }
                });
            },
            // 返回
            back() {
                this.parent.showFlag = true;
                this.parent.addOrUpdateFlag = false;
                this.parent.dictionaryCrossAddOrUpdateFlag = false;
                this.parent.contentStyleChange();
            },
            addEditStyleChange() {
                this.$nextTick(()=>{
                    // input
                    document.querySelectorAll('.addEdit-block .input .el-input__inner').forEach(el=>{
                    el.style.height = this.addEditForm.inputHeight
                el.style.color = this.addEditForm.inputFontColor
                el.style.fontSize = this.addEditForm.inputFontSize
                el.style.borderWidth = this.addEditForm.inputBorderWidth
                el.style.borderStyle = this.addEditForm.inputBorderStyle
                el.style.borderColor = this.addEditForm.inputBorderColor
                el.style.borderRadius = this.addEditForm.inputBorderRadius
                el.style.backgroundColor = this.addEditForm.inputBgColor
            })
                document.querySelectorAll('.addEdit-block .input .el-form-item__label').forEach(el=>{
                    el.style.lineHeight = this.addEditForm.inputHeight
                el.style.color = this.addEditForm.inputLableColor
                el.style.fontSize = this.addEditForm.inputLableFontSize
            })
                // select
                document.querySelectorAll('.addEdit-block .select .el-input__inner').forEach(el=>{
                    el.style.height = this.addEditForm.selectHeight
                el.style.color = this.addEditForm.selectFontColor
                el.style.fontSize = this.addEditForm.selectFontSize
                el.style.borderWidth = this.addEditForm.selectBorderWidth
                el.style.borderStyle = this.addEditForm.selectBorderStyle
                el.style.borderColor = this.addEditForm.selectBorderColor
                el.style.borderRadius = this.addEditForm.selectBorderRadius
                el.style.backgroundColor = this.addEditForm.selectBgColor
            })
                document.querySelectorAll('.addEdit-block .select .el-form-item__label').forEach(el=>{
                    el.style.lineHeight = this.addEditForm.selectHeight
                el.style.color = this.addEditForm.selectLableColor
                el.style.fontSize = this.addEditForm.selectLableFontSize
            })
                document.querySelectorAll('.addEdit-block .select .el-select__caret').forEach(el=>{
                    el.style.color = this.addEditForm.selectIconFontColor
                el.style.fontSize = this.addEditForm.selectIconFontSize
            })
                // date
                document.querySelectorAll('.addEdit-block .date .el-input__inner').forEach(el=>{
                    el.style.height = this.addEditForm.dateHeight
                el.style.color = this.addEditForm.dateFontColor
                el.style.fontSize = this.addEditForm.dateFontSize
                el.style.borderWidth = this.addEditForm.dateBorderWidth
                el.style.borderStyle = this.addEditForm.dateBorderStyle
                el.style.borderColor = this.addEditForm.dateBorderColor
                el.style.borderRadius = this.addEditForm.dateBorderRadius
                el.style.backgroundColor = this.addEditForm.dateBgColor
            })
                document.querySelectorAll('.addEdit-block .date .el-form-item__label').forEach(el=>{
                    el.style.lineHeight = this.addEditForm.dateHeight
                el.style.color = this.addEditForm.dateLableColor
                el.style.fontSize = this.addEditForm.dateLableFontSize
            })
                document.querySelectorAll('.addEdit-block .date .el-input__icon').forEach(el=>{
                    el.style.color = this.addEditForm.dateIconFontColor
                el.style.fontSize = this.addEditForm.dateIconFontSize
                el.style.lineHeight = this.addEditForm.dateHeight
            })
                // upload
                let iconLineHeight = parseInt(this.addEditForm.uploadHeight) - parseInt(this.addEditForm.uploadBorderWidth) * 2 + 'px'
                document.querySelectorAll('.addEdit-block .upload .el-upload--picture-card').forEach(el=>{
                    el.style.width = this.addEditForm.uploadHeight
                el.style.height = this.addEditForm.uploadHeight
                el.style.borderWidth = this.addEditForm.uploadBorderWidth
                el.style.borderStyle = this.addEditForm.uploadBorderStyle
                el.style.borderColor = this.addEditForm.uploadBorderColor
                el.style.borderRadius = this.addEditForm.uploadBorderRadius
                el.style.backgroundColor = this.addEditForm.uploadBgColor
            })
                document.querySelectorAll('.addEdit-block .upload .el-form-item__label').forEach(el=>{
                    el.style.lineHeight = this.addEditForm.uploadHeight
                el.style.color = this.addEditForm.uploadLableColor
                el.style.fontSize = this.addEditForm.uploadLableFontSize
            })
                document.querySelectorAll('.addEdit-block .upload .el-icon-plus').forEach(el=>{
                    el.style.color = this.addEditForm.uploadIconFontColor
                el.style.fontSize = this.addEditForm.uploadIconFontSize
                el.style.lineHeight = iconLineHeight
                el.style.display = 'block'
            })
                // 多文本输入框
                document.querySelectorAll('.addEdit-block .textarea .el-textarea__inner').forEach(el=>{
                    el.style.height = this.addEditForm.textareaHeight
                el.style.color = this.addEditForm.textareaFontColor
                el.style.fontSize = this.addEditForm.textareaFontSize
                el.style.borderWidth = this.addEditForm.textareaBorderWidth
                el.style.borderStyle = this.addEditForm.textareaBorderStyle
                el.style.borderColor = this.addEditForm.textareaBorderColor
                el.style.borderRadius = this.addEditForm.textareaBorderRadius
                el.style.backgroundColor = this.addEditForm.textareaBgColor
            })
                document.querySelectorAll('.addEdit-block .textarea .el-form-item__label').forEach(el=>{
                    // el.style.lineHeight = this.addEditForm.textareaHeight
                    el.style.color = this.addEditForm.textareaLableColor
                el.style.fontSize = this.addEditForm.textareaLableFontSize
            })
                // 保存
                document.querySelectorAll('.addEdit-block .btn .btn-success').forEach(el=>{
                    el.style.width = this.addEditForm.btnSaveWidth
                el.style.height = this.addEditForm.btnSaveHeight
                el.style.color = this.addEditForm.btnSaveFontColor
                el.style.fontSize = this.addEditForm.btnSaveFontSize
                el.style.borderWidth = this.addEditForm.btnSaveBorderWidth
                el.style.borderStyle = this.addEditForm.btnSaveBorderStyle
                el.style.borderColor = this.addEditForm.btnSaveBorderColor
                el.style.borderRadius = this.addEditForm.btnSaveBorderRadius
                el.style.backgroundColor = this.addEditForm.btnSaveBgColor
            })
                // 返回
                document.querySelectorAll('.addEdit-block .btn .btn-close').forEach(el=>{
                    el.style.width = this.addEditForm.btnCancelWidth
                el.style.height = this.addEditForm.btnCancelHeight
                el.style.color = this.addEditForm.btnCancelFontColor
                el.style.fontSize = this.addEditForm.btnCancelFontSize
                el.style.borderWidth = this.addEditForm.btnCancelBorderWidth
                el.style.borderStyle = this.addEditForm.btnCancelBorderStyle
                el.style.borderColor = this.addEditForm.btnCancelBorderColor
                el.style.borderRadius = this.addEditForm.btnCancelBorderRadius
                el.style.backgroundColor = this.addEditForm.btnCancelBgColor
            })
            })
            },
            addEditUploadStyleChange() {
                this.$nextTick(()=>{
                    document.querySelectorAll('.addEdit-block .upload .el-upload-list--picture-card .el-upload-list__item').forEach(el=>{
                    el.style.width = this.addEditForm.uploadHeight
                el.style.height = this.addEditForm.uploadHeight
                el.style.borderWidth = this.addEditForm.uploadBorderWidth
                el.style.borderStyle = this.addEditForm.uploadBorderStyle
                el.style.borderColor = this.addEditForm.uploadBorderColor
                el.style.borderRadius = this.addEditForm.uploadBorderRadius
                el.style.backgroundColor = this.addEditForm.uploadBgColor
            })
            })
            },
        }
    };
</script>
<style lang="scss">
.editor{
        height: 500px;

    & /deep/ .ql-container {
          height: 310px;
      }
    }
    .amap-wrapper {
        width: 100%;
        height: 500px;
    }
    .search-box {
        position: absolute;
    }
    .addEdit-block {
        margin: -10px;
    }
    .detail-form-content {
        padding: 12px;
    }
    .btn .el-button {
        padding: 0;
    }</style>
