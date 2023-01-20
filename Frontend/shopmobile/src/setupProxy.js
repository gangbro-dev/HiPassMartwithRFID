const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    createProxyMiddleware("/api", {
      target: "http://192.168.30.173:8080",
      changeOrigin: true,
      pathRewrite: {
        '^/api': '' // URL ^/api -> 공백 변경
    }
    })
  );
};
