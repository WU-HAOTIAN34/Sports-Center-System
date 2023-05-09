import axios from 'axios'

const request = axios.create({
    baseURL: 'http://localhost:8888',
    timeout: 60000
})

export default request
