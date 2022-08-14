<template>
  <div class="register-container">
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">注册</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="registerForm.username"
          placeholder="用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
        <span class="svg-container" v-show="userPass">
          <svg-icon icon-class="pass" />
        </span>
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType1"
          ref="password"
          v-model="registerForm.password"
          :type="passwordType1"
          placeholder="密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleRegister"
        />
        <span class="show-pwd" @click="showPwd1">
          <svg-icon :icon-class="passwordType1 === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-form-item prop="passwordConfirm">
        <span class="svg-container">
          <svg-icon icon-class="password"/>
        </span>
        <el-input
          :key="passwordType2"
          ref="passwordConfirm"
          v-model="registerForm.passwordConfirm"
          :type="passwordType2"
          placeholder="确认密码"
          name="passwordConfirm"
          tabindex="2"
          auto-complete="on"
        />
        <span class="show-pwd" @click="showPwd2">
          <svg-icon :icon-class="passwordType2 === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-form-item prop="phone" >
        <span class="svg-container">
          <svg-icon icon-class="role" />
        </span>
        <el-input
          ref="phone"
          v-model="registerForm.phone"
          placeholder="联系方式"
          name="phone"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-row>
        <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleRegister">注册</el-button>
      </el-row>
      <el-row>
        <el-button :loading="loading" style="width:100%;margin-bottom:30px;background-color: #b3d4fc;color: #131a23" @click.native.prevent="handleToLogin">去登录</el-button>
      </el-row>

    </el-form>
  </div>
</template>

<script>
  import {registerNew, userRules} from "@/api/register";

  export default {
    name: 'Register',
    data() {
      var checkName = (rule, value, callback) => {
        if (value != "") {
          if(this.nameRules()){
            callback(new Error("用户名已存在，请重新输入"))
          }else{
            callback()
          }

        }
        callback();
      }

      var validatePass = (rule, value, callback) => {
        if (value == "") {
          callback(new Error("请输入密码"));
        } else {
          if (this.registerForm.passwordConfirm !== "") {
            this.$refs.registerForm.validateField("passwordConfirm");
          }
          callback();
        }
      };

      var validatePass2 = (rule, value, callback) => {
        if (value == "") {
          callback(new Error("请再次输入密码确认"));
        } else if (value !== this.registerForm.password) {
          callback(new Error("两次输入密码不一致!"));
        } else {
          callback();
        }
      }

      var validatePhone = (rule, value, callback) => {
        const reg = /^[1][3-9][0-9]{9}$/;
        if (value == '' || value == undefined || value == null) {
          callback();
        } else {
          if ((!reg.test(value)) && value != '') {
            callback(new Error('请输入正确的电话号码'));
          } else {
            callback();
          }
        }
      }

      return {
        userPass: false,

        // 注册表单数据
        registerForm: {
          username: undefined,
          password: undefined,
          phone: undefined
        },

        registerRules: {
          username: [
            { required: true, trigger: "blur" },
            {
              min: 3,
              max: 10,
              message: "长度在 3 到 10 个字符之间",
              trigger: "blur",
            },
            { validator: checkName, trigger: "blur" },
          ],
          password: [
            { required: true, trigger: 'blur' },
            {
              min: 6,
              max: 16,
              message: "长度在 6 到 16 个字符之间",
              trigger: "blur",
            },
            { validator: validatePass, trigger: "blur" },
          ],
          passwordConfirm: [
            { required: true, trigger: 'blur', validator: validatePass2}
            ],
          phone: [
            { required: true, trigger: 'blur' },
            { validator: validatePhone, trigger: "blur" },
          ]
        },
        loading: false,
        passwordType1: 'password',
        passwordType2: 'password',
        redirect: undefined
      }
    },
    watch: {
      $route: {
        handler: function(route) {
          this.redirect = route.query && route.query.redirect
        },
        immediate: true
      }
    },
    methods: {
      showPwd1() {
        if (this.passwordType1 === 'password') {
          this.passwordType1 = ''
        } else {
          this.passwordType1 = 'password'
        }
        this.$nextTick(() => {
          this.$refs.password.focus()
        })
      },
      showPwd2() {
        if (this.passwordType2 === 'password') {
          this.passwordType2 = ''
        } else {
          this.passwordType2 = 'password'
        }
        this.$nextTick(() => {
          this.$refs.passwordConfirm.focus()
        })
      },
      handleToLogin() {
        this.$store.dispatch('user/logout')
        this.$router.push(`/login`)
      },
      handleRegister() {
        this.$refs["registerForm"].validate((valid) => {
          if (valid) {
            console.log(this.registerForm.username)
            console.log(this.registerForm.password)
            console.log(this.registerForm.phone)
            registerNew(this.registerForm).then((response) => {
              this.resetForm();
              if (response.msg == "注册成功") {
                this.$message.success("注册成功");
              } else{
                this.$message.error("注册失败，请输入正确的电话号码");
              }

            });
          }else{
            this.$message.error("注册失败");
          }
        });
      },

      /** 数据编辑表单清空 */
      resetForm() {
        this.registerForm = {
          username: undefined,
          password: undefined,
          phone: undefined
        };
      },
      //验证用户名是否存在
      nameRules() {
        let params = {
          name: this.registerForm.username,
        };
        userRules(this.registerForm.username).then((response) => {
          console.log("useRules")
          if(response.msg == '用户名可用') {
            this.userPass = true
            return false
          } else {
            this.$message.error("用户名已存在，请重新输入!")
            this.userPass = false
            this.registerForm.name = ""
          }
        })
      },
    }
  }
</script>

<style lang="scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

  $bg:#283443;
  $light_gray:#fff;
  $cursor: #fff;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .register-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .register-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;

      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;

        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
</style>

<style lang="scss" scoped>
  $bg:#2d3a4b;
  $dark_gray:#889aa4;
  $light_gray:#eee;

  .register-container {
    min-height: 100%;
    width: 100%;
    background-color: $bg;
    overflow: hidden;

    .register-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 110px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }
</style>
