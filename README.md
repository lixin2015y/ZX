# zx @date long long ago
# 使用springboot2.0.4作为项目的基础骨架
# 使用dubbo作为服务通方式
# 持久层使用mybatis
# 前台使用layui论坛社区模板
# 数据库mysql5.7  redis5.0.2  全部署在阿里云ECS服务器上
# maven3.5.9构建项目
# nginx 1.42 DNS域名解析coderlee.xyz 做反向代理


#模块分析
    1、登录模块
       用户密码使用MD5+salt加密 生成一个token服务器端保存到redis中，
       客户端保存在cookie中有效期为七天（七天免登陆）
       系统对所有请求进行拦截，未登陆的用户会跳转到登录界面
        
