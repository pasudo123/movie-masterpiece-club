const path = require('path');

function resolve(dir) {
    return path.join(__dirname, dir)
}

const glob = require('glob');
const pages = {};

glob.sync('./src/pages/**/main.js').forEach(path => {
    const chunk = path.split('./src/pages/')[1].split('/main.js')[0];

    pages[chunk] = {
        entry: path,
        template: 'public/index.html',
        title: chunk,
        chunks: ['chunk-vendors', 'chunk-common', chunk]
    };

    // ./src/pages/article/main.js
    // ./src/pages/login/main.js
    console.debug("============================== path");
    console.debug(path);
    console.debug("============================== chunk");
    console.debug(chunk);
    console.debug("============================== pages[chunk]");
    console.debug(pages[chunk]);
    console.debug("==============================");

});

module.exports = {

    devServer: {

        open: true,
        openPage: 'login',
        port: 8899,

        /** 개발단계 시, context - path 를 넣지 않는다. **/

        proxy: {
            [process.env.VUE_APP_BASE_API]: {
                target: 'http://localhost:8080',
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

    publicPath: process.env.NODE_ENV === 'production'
        ? process.env.VUE_APP_PUBLIC_PATH
        : '/',
    outputDir: '../src/main/resources/static/',
    assetsDir: '',
    pages: {
        login: {
            entry: './src/pages/login/main.js',
            template: 'public/index.html',
            title: 'login',
            filename: './templates/login.ftl',
            chunks: ['chunk-vendors', 'chunk-common', 'login']
        },
        masterpiece: {
            entry: './src/pages/masterpiece/main.js',
            template: 'public/index.html',
            title: 'masterpiece',
            filename: './templates/masterpiece.ftl',
            chunks: ['chunk-vendors', 'chunk-common', 'masterpiece']
        },
        laboratory: {
            entry: './src/pages/laboratory/main.js',
            template: 'public/index.html',
            title: 'laboratory',
            filename: './templates/laboratory.ftl',
            chunks: ['chunk-vendors', 'chunk-common', 'laboratory']
        }
    }
};