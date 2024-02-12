module.exports = {
  configureWebpack: {
    devServer: {
      host: '0.0.0.0',
      disableHostCheck: true,
      port: 8000,
      // open: true,
      proxy: {
        '/api': {
          target: 'http://localhost:8080',
          ws: true,
          changeOrigin: true,
          pathRewrite:{
            '^/api': '/'
          }
        }
      }
    }
  }
};
