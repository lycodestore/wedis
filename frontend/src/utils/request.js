import axios from 'axios'
import Cookies from 'js-cookie'

const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // api 的 base_url
  withCredentials: true, // 跨域请求时发送 cookies
  timeout: 5000 // request timeout
})

service.interceptors.request.use(
  config => {
    // Do something before request is sent
    if (Cookies.get('token')) {
      // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
      config.headers['token'] = Cookies.get('token')
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(

  response => {
    const res = response.data
    return res
  },
  error => {
    return Promise.reject(error)
  }
)

export default service
