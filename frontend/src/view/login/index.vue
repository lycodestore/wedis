<template>
  <div id="login">
    <div class="login-top">
      <img src="@/assets/login/redis.png" alt="">
    </div>
    <div class="login-form">
      <Form ref="formInline" >
        <FormItem prop="user">
          <Input v-model="host" placeholder="host" />
        </FormItem>
        <FormItem prop="user">
          <Input v-model="port" placeholder="port" />
        </FormItem>
        <FormItem prop="user">
          <Button type="primary" style="width: 100%;" @click="connect">连接</Button>
        </FormItem>
      </Form>
    </div>
  </div>
</template>
<script>
import Cookies from 'js-cookie'

export default {
  data() {
    return {
      host: 'localhost',
      port: '6379'
    }
  },
  methods: {
    connect() {
      this.$http.post('/connect', {
        host: this.host,
        port: this.port
      }).then(res => {
        if (res.code === 20000) {
          const token = this.host + ':' + this.port
          Cookies.set('token', token)
          this.$router.push('/main')
        } else {
          this.$Message.error(res.message)
        }
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>
<style scoped>
#login {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background: #2c3e50;
}
.login-top {
  text-align: center;
  padding-top: 100px;
}
.login-form {
  width: 300px;
  margin: 30px auto;
}
</style>
