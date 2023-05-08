import axios from 'axios'

const request = axios.create({
    baseURL: 'http://localhost:8888',
    timeout: 60000
})

axios.interceptors.request.use(
    config => {
        if (localStorage.getItem('token')) {
            config.headers.token = localStorage.getItem('token')
        }
        return config
    },
    error => {
        return Promise.reject(error)
    })

export default request
