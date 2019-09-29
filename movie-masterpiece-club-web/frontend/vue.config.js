
const path = require('path');

function resolve(dir) {
    return path.join(__dirname, dir)
}

module.exports = {

    devServer: {
        port: 8899,
        proxy: {
            [process.env.VUE_APP_BASE_API]: {
                target: `http://localhost:8080`
            }
        }
    },

    configureWebpack: {
        resolve: {
            alias: {
                '@': resolve('src'),
                'icons': path.resolve(__dirname, 'node_modules/vue-material-design-icons')
            },
            extensions: [
                '.vue'
            ]
        },
    },
}