# 财务管理系统

## 技术栈

Socket+Druid+dbutils

jdk版本：17

最好使用jdk17，其他版本可能会出现错误

## 需求

![image-20220620114453694](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620114453694.png)

## 快速开始

1. 解压压缩包
2. 从idea里面导入，按照如图所示点击

![image-20220620225109837](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620225109837.png)

2. 找到你解压的目录，然后点击OK

![image-20220620225253208](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620225253208.png)

3. **一直点击next直到finish**

![image-20220620225317825](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620225317825.png)

4. 然后This window 或者new window都可以，进入如下界面

![image-20220620225507180](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620225507180.png)

5. 再如图所示点击

![image-20220620225532382](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620225532382.png)

6. 导入对应依赖

![image-20220620225559503](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620225559503.png)

7. 选中目录下的lib文件夹

![image-20220620225620854](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620225620854.png)

8. 点击OK

![image-20220620225644457](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620225644457.png)

9. 在src/config目录下修改对应的配置

![image-20220620225739579](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620225739579.png)

数据库配置`druid.properties`

```properties
#url，如果你的数据库端口不是3306就需要改一下，然后数据库不是叫financial-management也需要改成相对应的
url=jdbc:mysql://localhost:3306/financial-management?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=TRUE
#用户名
username=你的用户名
#密码
password=密码
```

Socket配置,更改下面的两个常量

```java
public class Transport {
    /**
     * 服务器主机
     * 注意，服务端IP不能是127.0.0.1或者localhost，而是服务器的公网ip
     */
    public static final String SERVER_HOST = "192.168.1.5";

    /**
     * 服务器端口
     */
    public static final Integer SERVER_PORT = 8080;
}
```

注意，如果不知道服务端公网IP可以`win+R`然后输入`cmd`打开控制台，输入`ipconfig`查看ipv4地址

![image-20220620230304101](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620230304101.png)

如果还是连接失败，请在服务端运行测试如下代码得到ip地址

```java
public class GetHost {
    public static void main(String[] args) throws UnknownHostException {
        String host = InetAddress.getLocalHost().getHostAddress();
        System.out.println(host);
    }
}

```

10. 运行sql文件

先创建一个`financial-management`的数据库，然后在导入目录下的sql脚本即可

11. 启动服务端

![image-20220620230804037](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620230804037.png)

12. 启动客户端

![image-20220620230820885](https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/image-20220620230820885.png)

注：因为本项目用到了很多封装以及工具类，所以即便是服务端以及客户端要分开，也建议分别在服务器和客户端两台电脑导入完整的项目，然后再分别启动服务端主启动类和客户端主启动类

**另外，关于文件的读写，idea的显示会有一定延迟，所以下载了文件后用此电脑打开对应的目录就可以看到了，或者等idea刷新目录**

## 目录结构

```txt
C:\Users\Admin\JavaProjects\financial-management>tree
卷 系统 的文件夹 PATH 列表
卷序列号为 D5EC-3327
C:.
├─.idea #idea的文件，不用管
│  ├─dataSources
│  │  └─d708f458-114f-4d55-afb0-6927899d8707
│  │      └─storage_v2
│  │          └─_src_
│  │              └─schema
│  └─inspectionProfiles
├─client #客户端导出的所有文件将存放在这个目录下
├─download #客户端下载的所有文件将会保存至这个文件夹
├─lib #存放jar包
├─out #编译后的输出目录
│  └─production
│      └─financial-management
│          ├─config
│          └─cuit
│              └─pymjl
│                  ├─constant
│                  ├─dao
│                  │  └─impl
│                  ├─entity
│                  ├─factory
│                  ├─remote
│                  ├─service
│                  │  └─impl
│                  ├─test
│                  └─utils
├─server #客户端上传的文件将会保存在这里，根据用户名分文件夹管理
│  └─qiweikai
└─src #Java源代码
    ├─config #配制文件
    └─cuit
        └─pymjl
            ├─constant #存放常量
            ├─dao #数据库操作的接口
            │  └─impl
            ├─entity #实体类
            ├─factory #工厂类，里面存放了单例工厂
            ├─remote #Socket传输的核心代码
            ├─service #业务逻辑处理
            │  └─impl
            ├─test #写了一些测试，怕麻烦就没用单元测试
            └─utils #一些工具类的封装

```



