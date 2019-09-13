
import axios from 'axios'

const instance = axios.create({
    baseURL: `${process.env.VUE_APP_BASE_API}/v1.0`,
    timeout: 10000,
})

export default instance
