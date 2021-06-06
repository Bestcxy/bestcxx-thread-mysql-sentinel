# bestcxx-thread-mysql-sentinel
基于 spring boot+mysql+sentinel


# 数据库
数据库：test
用户名:root
密码:root

根据 建表文件建表-看项目中的建表语句.sql

# sentinel 模块
[https://github.com/alibaba/Sentinel/wiki/%E6%96%B0%E6%89%8B%E6%8C%87%E5%8D%97#%E5%85%AC%E7%BD%91-demo]
【启动 sentinel-dashboard-1.7.2.jar】java -Dserver.port=8081 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.7.2.jar
sentinel 控制台 http://localhost:8081/#/dashboard/home
# 在sentinel控制台查看本项目 sentinel 资源相关内容
[在 sentinel 控制台启动后 启动本项目，增加VM 参数,可以在sentinel控制台看到项目中的限流资源相关内容并进行管理 -Dcsp.sentinel.dashboard.server=localhost:8081]


# 项目运行和访问
项目配置项为 application.yml
运行 App.class main 方法启动程序
启动后访问 http://127.0.0.1:8080/thymeleaf/index 会在表以多线程形式新增 100万条数据
启动后访问 http://127.0.0.1:8080/thymeleaf/indexNoDb 仅仅简单访问，无数据库操作-体验 sentinel 限流
清表命令:TRUNCATE TABLE index_stu
观察druid http://127.0.0.1:8080/thymeleaf/druid
