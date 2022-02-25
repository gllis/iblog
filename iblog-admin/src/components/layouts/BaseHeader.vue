<template>
  <div class="ui-header">
    <div class="ui-header-item">iBlog-Admin</div>
    <div class="ui-header-item">
      <el-dropdown>
        <span class="el-dropdown-link">
          {{ username }}
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="showModifyPwd">修改密码</el-dropdown-item>
            <el-dropdown-item @click="exit">退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
    <el-dialog v-model="dialogVisible" title="修改密码" width="30%">
      <el-form ref="ruleFormRef" :model="form" :rules="userRules" :label-width="120">
        <el-form-item prop="oldPwd" label="旧密码">
          <el-input v-model="form.oldPwd"></el-input>
        </el-form-item>
        <el-form-item prop="newPwd" label="新密码">
          <el-input v-model="form.newPwd"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPwd" label="再次输入">
          <el-input v-model="form.confirmPwd"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="modifyPwd(ruleFormRef)">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import type { ElForm } from 'element-plus'
import { ArrowDown } from '@element-plus/icons-vue'
import { useStore } from "vuex"
import router from "~/router";
import Storages from "~/utils/storages"
import md5 from "md5";

const { state, dispatch } = useStore()
const dialogVisible = ref(false)
const username = ref(state.user.userInfo.name);

type FormInstance = InstanceType<typeof ElForm>
const ruleFormRef = ref<FormInstance>()

const form = reactive({
  oldPwd: '',
  newPwd: '',
  confirmPwd: ''
})

const validatePass = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('请再次输入密码'))
  }
  setTimeout(() => {
    if (value !== form.newPwd) {
      callback(new Error('两次输入密码不一致!'))
    } else {
      callback()
    }
  }, 1000)
}

const userRules = reactive({
  oldPwd: [
    { required: true, message: '请输入旧密码', trigger: 'blur' },
  ],
  newPwd: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
  ],
  confirmPwd: [
    { validator: validatePass, trigger: 'blur' },
  ]
})



const showModifyPwd = () => {
  dialogVisible.value = true
}
const modifyPwd = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      let params = {
        userId: state.user.userInfo.id,
        oldPwd: md5(form.oldPwd),
        newPwd: md5(form.newPwd)
      }
      dispatch("user/modifyPwd", params);
    } else {
      console.log('error submit!')
      return false
    }
  })
}
const exit = () => {
  dispatch("user/loginOut");
}
</script>

<style lang="scss"  scoped>
.ui-header {
  display: flex;
  width: 100%;
  height: 100%;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #eeeeee;
  .ui-header-item {
    padding: 0px 10px;
  }
}
</style>
