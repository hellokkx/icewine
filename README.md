# icewine

## 代码所需环境配置说明和执行方式：
1. 需要本地机器有MYSQL的基本配置
2. 先通过wine_management.sql文件在本地创建相应数据库，连接数据库
3. 注意在wine_management/Springboot/src/main/resourses/application.yml配置文件下，用户名username和密码password需要和本地数据库设置的用户名和密码保持一致。
4. 本地机器需下载配置node.js；Vue CLI需要Node.js v8.9或更高版本。

### 前端：
5. 终端进入wine_management/vue下（有可能是需要管理员权限的）：
npm install --registry=https://registry.npm.taobao.org
或
npm install -g cnpm --registry=https://registry.npm.taobao.org
cnpm install

6. 输入npm run serve
7. 在本地网址输入localhost:8080即可进入到页面中了（端口号根据分配端口号进行输入）

### 后端：
8. 需要SpringBoot基本配置，建议用idea等编译器打开项目
9. Build and Run com.example.springboot.SpringbootApplication, 后台端口号分配在resourses/application.yml配置文件，可自行更改
