<template>
  <div class="main">
    <cube-form :model="model" @submit="submitHandler">
      
      <cube-form-group>
          <!--名称-->
        <cube-form-item :field="fields[0]"></cube-form-item>
        <!--手机号-->
        <cube-form-item :field="fields[1]"></cube-form-item>
        <!--密码-->
        <cube-form-item :field="fields[2]"></cube-form-item>
      </cube-form-group>


      <cube-form-group>
        <cube-button type="submit">注册</cube-button>
      </cube-form-group>


    </cube-form>
    <router-link to="/login" class="reg">登录</router-link> 
  </div>
</template>

<script>
//注册接口
import { registerApi } from "@/api/getData.js";
export default {
  // cube-ui中的Form组件
  data() {
    return {
      model: {
        phoneValue: "",
        pwdValue: "",
        nameValue: ""
      },
      fields: [ 
        {
          type: "input",
          modelKey: "nameValue",
          label: "名称",
          props: {
            placeholder: "请输入名称"
          },
          rules: {
            required: true
          },
          messages: {
            required: "名称不能为空"
          }
        },
        {
          type: "input",
          modelKey: "phoneValue",
          label: "手机号",
          props: {
            placeholder: "请输入手机"
          },
          rules: {
            required: true
          },
          messages: {
            required: "手机号不能为空"
          }
        },
        {
          type: "input",
          modelKey: "pwdValue",
          label: "密码",
          props: {
            placeholder: "请输入密码",
            // type为password时，可以附加eye属性，密码是否可见
            type: "password",
            eye: {
              open: false
            }
          },
          rules: {
            required: true
          },
          messages: {
            required: "密码不能为空"
          }
        }
       
      ]
    };
  },
  methods: {
    submitHandler(e, model) {
      //固定写法，防止误提交
      e.preventDefault();
      //调用注册接口；.then是取回反应结果
      registerApi(model.phoneValue, model.pwdValue, model.nameValue).then(
        res => {
          if (res.data.code === 0) {
            //cube-ui toast组件（弹出）
            const toast = this.$createToast({
              txt: "注册成功",
              type: "correct",
              time: 1500
            });
            toast.show();
          }
        }
      );
    }
  }
};
</script>


<style lang="scss" scoped>
.main {
  padding: 50px 5% 0;
  text-align: center;
}
//注册
.cube-btn {
  margin-top: 20px;
}
// 登录
.reg {
  display: inline-block;
  margin-top: 30px;
  font-size: 18px;
}
</style>