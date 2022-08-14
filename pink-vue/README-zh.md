# vue-admin-template

> 这是一个极简的 vue admin 管理后台。它只包含了 Element UI & axios & iconfont & permission control & lint，这些搭建后台必要的东西。

[国内访问](https://panjiachen.gitee.io/vue-admin-template)

# 安装依赖

npm install --registry=<https://registry.npm.taobao.org>

# 程序修改

本程序在原程序上做以下修改：

(1) 修改vue.config.js文件，添加使用后端api的代码(42-51行)
(2) 添加了tagsView功能，贴合MIS类系统交互需求。（主要修改了src/store及src/layout代码）
(3) 修改了mock及utils/request.js中成功code为默认的200
