<template>
  <div>
    <el-container>
        <el-header>
            <div class="title">
                <nuxt-link to="/admin">iBlog Admin</nuxt-link>
                <div class="ui-drop-menu">
                    <el-dropdown trigger="click">
                    <span class="el-dropdown-link">
                        {{user.name}}<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click.native="showModifyPwd">修改密码</el-dropdown-item>
                        <el-dropdown-item @click.native="exit">退出</el-dropdown-item>
                    </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </div>
        </el-header>
        <el-container class="ui-container">
            <el-aside width="200px">
                <el-menu :default-active="$route.path" @select="selectMenu">
                    <el-menu-item index="/admin/article/publish">
                        <i class="el-icon-edit-outline"></i>
                        <span slot="title">发布文章</span>
                    </el-menu-item>
                    <el-menu-item index="/admin/article/list">
                        <i class="el-icon-document"></i>
                        <span slot="title">文章列表</span>
                    </el-menu-item>
                    <el-menu-item index="/admin/tag">
                        <i class="el-icon-setting"></i>
                        <span slot="title">标签</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                 <nuxt/>
            </el-main>
        </el-container>
        <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
            <el-form ref="userForm" :model="form" :rules="userRules">
                <el-form-item prop="oldPwd" label="旧密码" :label-width="formLabelWidth">
                    <el-input v-model="form.oldPwd"></el-input>
                </el-form-item>
                <el-form-item prop="newPwd" label="新密码" :label-width="formLabelWidth">
                    <el-input v-model="form.newPwd"></el-input>
                </el-form-item>
                <el-form-item prop="confirmPwd" label="再次输入" :label-width="formLabelWidth">
                    <el-input v-model="form.confirmPwd"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="modifyPwd">确 定</el-button>
            </div>
        </el-dialog>
        <el-footer>
            <div class="cr">Copyright © 2019. All rights reserved.</div>
        </el-footer>
    </el-container>
   
  </div>
</template>

<script>
import { MD5 } from 'crypto-js'
export default {
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.form.newPwd) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            dialogFormVisible: false,
            formLabelWidth: '120px',
            name: '',
            form: {},
            user: {},
            userRules: {
                oldPwd: [
                    { required: true, message: '请输入旧密码', trigger: 'blur' },
                ],
                newPwd: [
                    { required: true,  message: '请输入新密码', trigger: 'blur' },
                ],
                confirmPwd: [
                    { validator: validatePass,  trigger: 'blur' },
                ]
            },

        }
    },
    mounted() {
        this.user = sessionStorage.getItem('user');
        if (this.user) {
            this.user = JSON.parse(this.user);
        }
    },
    methods: {
        selectMenu(index) {
            this.$router.push(index);
        },
        showModifyPwd() {
            this.form = {};
            this.dialogFormVisible = true;
        },
        async modifyPwd() {
            let valid = false
            this.$refs.userForm.validate((_valid) => {
                valid = _valid; 
            });
              console.info(this.user)
              console.info(this.user.id)
            if (!valid) {
                return;
            }
          
            let params = {
                userId: this.user.id,
                oldPwd: MD5(this.form.oldPwd).toString(),
                newPwd: MD5(this.form.newPwd).toString()
            }
            console.info(params)
            const res = await this.$axios.$post("/user/modifyPwd", params);
            if (res.errcode == 0) {
                this.dialogFormVisible = false;
            } else {
                this.$notify({
                    title: '操作提示',
                    message: '修改密码失败！',
                    type: 'error'
                });
            }
        },
        exit() {
            sessionStorage.removeItem('user');
            this.$cookies.remove('token');
            this.$router.push('/admin');
        }
    }
}
</script>

<style lang="scss" scoped>
.el-header {
    border-bottom: 1px solid #eee;
    line-height: 60px;
    .title {
        margin: auto;
        width: 1306px;
        line-height: 60px;
        font-weight: bold;
        a {
            color: initial;
            text-decoration: none;
        }
        .ui-drop-menu {
            float: right;
            .el-dropdown-link {
                cursor: pointer;
            }
        }
    }
}

.ui-container {
    margin: auto;
    width: 1306px;
    min-height: 86vh;
    display: flex;
    justify-content: center;
}
.el-menu  {
    height: 100%;
}
.el-footer {
    border-top: 1px solid #eee;
    line-height: 60px;
    .cr {
        width: 1306px;
        margin: auto;
    }
}
</style>