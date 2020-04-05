const webpack = require('webpack');
const path = require('path');

function resolve(dir) {
    return path.join(__dirname, dir)
}

/** 추후 수행 **/
// const glob = require('glob');
// const pages = {};

// glob.sync('./src/pages/**/main.js').forEach(path => {
// const chunk = path.split('./src/pages/')[1].split('/main.js')[0];
// pages[chunk] = {
//   entry: path,
//   template: 'public/index.html',
//   title: chunk,
//   chunks: ['chunk-vendors', 'chunk-common', chunk]
// };
// ./src/pages/article/main.js
// ./src/pages/login/main.js
// console.debug("============================== path");
// console.debug(path);
// console.debug("============================== chunk");
// console.debug(chunk);
// console.debug("============================== pages[chunk]");
// console.debug(pages[chunk]);
// console.debug("==============================");
// });

module.exports = {
    devServer: {
        open: true,
        openPage: 'login',
        port: 8899,
        proxy: {
            [process.env.VUE_APP_BASE_API]: {
                target: 'http://localhost:8080',
            },
          '/*': {
            /** 해당 내용 사라지면 개발서버에서 개발 시 blank 화면이 나옴. (원인파악 필요) **/
            target: 'http://localhost:8899',
            pathRewrite: {'^/.*': './templates/index.ftl'},
            ws: false
          }
        },
    },
    chainWebpack(config){
      config.module
        .rule('vue')
        .use('vue-loader')
        .loader('vue-loader')
        .tap(options => {
          options.compilerOptions.preserveWhitespace = true;
          return options;
        }).end()
    },
    configureWebpack: {
        resolve: {
            alias: {
                '@': resolve('src'),
            },
            extensions: [
                '.vue'
            ]
        },
        plugins: [
            new webpack.ProvidePlugin({
                'window.Quill': 'quill/dist/quill.js',
                'Quill': 'quill/dist/quill.js'
            }),
        ]
    },
    publicPath: (process.env.NODE_ENV === 'staging'
        || process.env.NODE_ENV === 'production')
        ? process.env.VUE_APP_BASE_URL
        : '/',
    outputDir: '../src/main/resources/static/',
    assetsDir: '',
    pages: {
        index: {
            entry: 'src/pages/masterpiece/main.js',
            template: 'public/index.html',
            title: 'Index',
            filename: './templates/index.ftl',
            chunks: ['chunk-vendors', 'chunk-common', 'index']
        },
        login: {
            entry: 'src/pages/login/main.js',
            template: 'public/index.html',
            title: 'Login',
            filename: './templates/login.ftl',
            chunks: ['chunk-vendors', 'chunk-common', 'login']
        }
    }
    // laboratory: {
    //     entry: './src/pages/laboratory/main.js',
    //     template: 'public/index.html',
    //     title: 'laboratory',
    //     filename: './templates/laboratory.ftl',
    //     chunks: ['chunk-vendors', 'chunk-common', 'laboratory']
    // }
    // }
};
