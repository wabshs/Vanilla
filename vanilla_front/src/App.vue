<template>
  <!--  最外层的盒子-->
  <div class="all">
    <!--    登录框-->
    <div class="login_box">
      <!--      左边放图-->
      <div class="left_img">

      </div>
      <!--      右边放登录按钮等-->
      <div class="right_section">
        <h1 style="color: cadetblue"> 登录 </h1>
        <div class="input-container">
          <input type="text" id="username" name="username" placeholder="USERNAME">
          <input type="password" id="password" name="password" placeholder="PASSWORD">
        </div>
        <div class="input-group">
          <input type="text" id="verifyCode" name="verifyCode" placeholder="验证码">
          <!-- 添加一个用于显示验证码的容器 -->

        </div>

        <!--        登录注册-->
        <div class="login_reg">
          <el-button type="primary" round size="large">登录</el-button>
        </div>
      </div>
    </div>
  </div>

</template>


<style>
/* 为验证码容器添加样式 */
.right_section .input-container {
  display: flex;
  flex-direction: column; /* 垂直方向排列 */
}




body, html {
  height: 100%;
  margin: 0;
}

.all {
  background-image: linear-gradient(to right, #eea2a2 0%, #bbc1bf 19%, #57c6e1 42%, #b49fda 79%, #7ac5d8 100%);
  height: 100vh;
  display: flex;
  justify-content: center; /* 在水平方向上居中 */
  align-items: center; /* 在垂直方向上居中 */
}

.login_box {
  width: 800px;
  height: 400px;
  background-color: #fff;
  border-radius: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  display: flex;
}

.left_img {
  flex: 2;
  background-image: url("assets/login_png.png");
  background-size: cover; /* 自适应 */
  border-radius: 20px 0 0 20px;
}

.right_section {
  flex: 1;
  display: flex;
  flex-direction: column; /* 设置垂直方向排列 */
  align-items: center; /* 居中 */
}

/*3个输入框 */
.right_section input {
  outline: none;
  border: 0;
  padding: 10px;
  border-bottom: 3px solid rgb(80, 80, 170);
  margin-bottom: 40px;
}

.right_section input#verifyCode {
  border-bottom-width: 3px /* 调整下划线的宽度 */
}

.login_reg {
  display: flex;
  justify-content: flex-end;
  margin-left: 150px;

}
</style>
<script setup>
</script>