** 这个项目是我们软件工程作业，用SSM搭建的，实现了秒杀功能，shrio登录和solr搜索功能，下单功能等等 **
### 前端 
1. 秒杀功能前端使用bookstrap,引入bookstrap的js、和css就能编写出好看，响应式的界面 
2. 其他模块均使用vue.js,并且采用了element-ui作为组件编写，使用前先好好读一下官方文档，比看多少博客都管用，哪里不懂再百度解决 
 * [vue官方文档](https://cn.vuejs.org/v2/guide/) 
 * [element-ui官方文档](http://element-cn.eleme.io/#/zh-CN/component/layout) 
### 后端 
1.后端主要使用SSM框架编写，学习SSM需要： 
* 自己动手找教程搭建框架，网上有很多教程，动手做出来，并看懂代码 
* 学习简单的管理系统，可以看一下我的github的项目 
[学生管理系统](https://github.com/HannahLihui/StudentManager-SSM) 
* 自己编写新的模块
2.  Redis在秒杀系统中应用，做缓存技术。可以在虚拟机或者window上安装，
* 启用方法：命令行进入redis目录，redis-server.exe & 启动服务 
3. solr需要配置，我直接用的github上的[github solr](https://github.com/TyCoding/solr-tomcat)，
  使用方法可以看github的READMD.md。
* bin目录start.bat启用服务，浏览器访问端口localhost:8080/solr/index.html 
* solr的学习使用可以看我的博客 solr的使用 
4. shiro权限登录实现
 * [权限登录（一）](https://www.jianshu.com/p/438d1785275b) 
 * [权限登录（二）](https://www.jianshu.com/p/88edbf11786a) 
### 前后端分离 
* [前后端分离（一）](https://www.jianshu.com/p/4397d3f2bb9c) 
* [前后端分离（二）](https://www.jianshu.com/p/2085ef373d77)
### 有问题欢迎联系qq:3303594461
