<template>
  <Layout>
    <Header>
      <a-row justify="space-around" align="middle">
        <a-col :span="12">
          <img src="src/assets/Logo.png" style="right: 125px"/>
        </a-col>
        <a-col :span="12">
          <a-typography-title style="right: 370px" class="title">Sports Facilities Usage System</a-typography-title>
        </a-col>
      </a-row>
    </Header>
    <Content>
      <a-form
          :model="formState"
          name="basic"
          :label-col="{ span: 8 }"
          :wrapper-col="{ span: 16 }"
          autocomplete="off"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
      >
      <a-form-item
          label="Username"
          name="username"
          :rules="[{ required: true, message: 'Please input your username!' }]"
      >
        <a-input v-model:value="formState.username" />
      </a-form-item>

        <a-form-item
            label="Password"
            name="password"
            :rules="[{ required: true, message: 'Please input your password!' }]"
        >
          <a-input-password v-model:value="formState.password" />
        </a-form-item>

        <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
          <a-checkbox v-model:checked="formState.remember">Remember me</a-checkbox>
        </a-form-item>

        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit" @click="handleSubmit()">Submit</a-button>
        </a-form-item>
      </a-form>
    </Content>
    <Footer style="bottom: -150px">
      Designed by
      Team 2023.15
      Zexiang CHEN, Ke GAO, Ziyan JIANG,
      Zhangyi MA, Zi WANG, Qi YOU
    </Footer>
  </Layout>
</template>

<script>
import { defineComponent, reactive } from 'vue';
import {addUser} from "@/api/api";
import {Modal} from "ant-design-vue";
export default defineComponent({
  setup() {

    const link = "http://localhost:5173/#/index";

    const formState = reactive({
      username: '',
      password: '',
      remember: true,
    });
    const onFinish = values => {
      console.log('Success:', values);
    };
    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo);
    };

    const handleSubmit =() =>{

      console.log("click submit")

      let param = {
        userName: formState.username,
        password: formState.password
      }

      addUser(param).then(res =>{
          console.log((res.data))
        })

      if(formState.username === "" && formState.password === ""){
        Modal.warning({
          title: 'This is a warning message',
          content: 'UserName or Password cannot be null',
        });
      }
      if(formState.username === "admin" && formState.password === "123456"){
        window.open(link)
      }
      if(((formState.username !== "admin")&&(formState.username !== "")) || ((formState.password !== "") && (formState.password !== "123456"))) {
        Modal.error({
          title: 'This is an error message',
          content: 'You are not the manager',
        });
      }

    }

    return {
      formState,
      onFinish,
      onFinishFailed,
      handleSubmit
    };
  },
});
</script>

<style scoped>
  header{
    top: 0px;
  }
  content{
    display: inline-block;
    position: center;
    top: 100px;
    left: -30px;
  }
  .title{
    color: #096dd9;
  }
</style>