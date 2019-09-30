
const path = require('path');

function resolve(dir) {
    return path.join(__dirname, dir)
}

module.exports = {

    /**
     * 웹팩 빌드 안되는 문제에 대한 해결.
     * https://github.com/vuejs/vue-cli/issues/4572
     */
    productionSourceMap: false,
    css: {
        sourceMap: true
    },

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

    publicPath: '/',
    outputDir: '../src/main/resources/',
    assetsDir: 'static',
    pages: {
        'login': {
            entry: './src/pages/login/main.js',
            template: 'public/index.html',
            filename: './templates/login.ftl'
        },
        'masterpiece': {
            entry: './src/pages/article/main.js',
            template: 'public/index.html',
            filename: './templates/masterpiece.ftl'
        }
    }
};