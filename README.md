# rkmonitor
爬虫检查软考官网，若2020成绩出来，则发送邮件通知

### application.yml配置如下
```
server:
  port: 8090 #程序启动端口

spring:
  application:
    name: rkmonitor
  mail:
    host: smtp.126.com #发送邮件服务器
    username: ruankaomonitor@126.com #发送邮件的邮箱地址
    password: XXXXXXXXXXXXXXXX #客户端授权码，不是邮箱密码，网易的是自己设置的
    properties.mail.smtp.port: 465 #465或者994
    properties.mail.smtp.starttls.enable: true
    properties.mail.smtp.starttls.required: true
    properties.mail.smtp.ssl.enable: true
    default-encoding: utf-8

logging:
  level:
    com.gargoylesoftware.htmlunit: error

to:
  mails: 123@qq.com, 456@qq.com #可以设置多组邮箱，用英文逗号分隔

schedule: 0 */10 * * * ? #每隔10分钟检查一次
```
### 需要修改的地方有
```
spring.mail.username: ruankaomonitor@126.com #发送邮件的邮箱地址
spring.mail.username: XXXXXXXXXXXXXXXX #客户端授权码，不是邮箱密码，网易的是自己设置的
properties.mail.smtp.port: 465 #465或者994，根据具体邮箱设置中提供的端口来填写，126邮箱的是465
to.mail: 123@qq.com, 456@qq.com #可以设置多组邮箱，用英文逗号分隔
schedule: 0 */10 * * * ? #默认每隔10分钟检查一次，可自行配置
```

### 启动方式
1、编译打包： ```mvn clean package```  
2、将jar包和application.yml放到同一目录，启动命令：  
``` nohup java -jar -Dspring.config.location=application.yml rkmonitor-1.0.jar -Dfile.encoding=utf-8 2>&1 & ```  
ps：启动时注意端口冲突
