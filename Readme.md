

# 项目功能说明

本项目是一个基于 Spring Boot 3 + OpenFeign 的钉钉开放平台对接服务，面向“自动建群+群消息触达”场景，提供以下能力：

## 核心能力

1. 获取企业内部应用 AccessToken（应用身份鉴权）。
2. 根据手机号查询钉钉用户 `userId`。
3. 创建场景群（支持直接传 `userId`，或传手机号后自动转换）。
4. 发送群助手消息（文本模板），支持 `@指定成员` 和 `@所有人`。

## 已实现接口

### 业务接口

- `POST /api/dingtalk/group/scene/createByPhone`
  - 入参：群主手机号、成员手机号列表、群标题。
  - 逻辑：自动将手机号转换为 `userId` 后创建场景群。

### 调试/测试接口

- `GET /api/dingtalk/user/access-token`：获取 AccessToken。
- `POST /api/dingtalk/user/get-by-mobile`：手机号换取 `userId`。
- `POST /api/dingtalk/user/scene/create`：按 `userId` 参数直接创建场景群。
- `POST /api/dingtalk/user/scene/message/send`：发送群助手文本消息。

## 典型业务流程（自动建群）

1. 通过应用凭证获取 AccessToken（由拦截器统一透传到钉钉接口）。
2. 根据群主手机号和成员手机号查询对应 `userId`。
3. 组装建群请求（自动补充 `uuid`、`templateId`）并调用钉钉建群接口。
4. 发送群助手消息通知群成员（支持 `@all`）。

# 自动建群流程

## 成为开发者

https://open.dingtalk.com/document/dingstart/dingtalk-developer

## 创建 H5 微应用

+ 点击委托服务商开发 -> 
  + 应用类型：H5微应用
  + 开发方式：企业自主开发
  + 命名不能有空格

![img.png](file/image/img.png)

https://open.dingtalk.com/document/orgapp/create-an-h5-micro-application

## 应用内添加机器人

https://open.dingtalk.com/document/development/development-robot-overview?spm=a2q3p.21071111.0.0.4929TxbCTxbCAT

## 创建群模板

+ 可选应用，选择创建的 H5 微应用
![img_1.png](file/image/img_1.png)

https://open-dev.dingtalk.com/fe/im?spm=ding_open_doc.document.0.0.432827e2jKgyS3#/group/list

## 开通权限

### 获取应用凭证

https://open.dingtalk.com/document/development/obtain-the-access-token-of-an-internal-app?spm=ding_open_doc.document.0.0.15d327e2IajId6

> 注意：新版本， 要使用 Client ID 和 Client Secret

### 创建群机器人

https://open.dingtalk.com/document/development/development-robot-overview?spm=a2q3p.21071111.0.0.34a1IlBcIlBcwn

> 用于发送群助手消息：提醒xxx客户到院，并@所有人

### 根据手机号获取成员基本信息权限

[qyapi_get_member_by_mobile]，点击链接申请并开通即可：https://open-dev.dingtalk.com/appscope/apply?content=cliowdul0snq9dnukx6%23qyapi_get_member_by_mobile,

### 钉钉群基础信息管理权限

[qyapi_chat_manage]，点击链接申请并开通即可：https://open-dev.dingtalk.com/appscope/apply?content=dingzmdx4lslyfmm3olj%23qyapi_chat_manage

## 相关接口

### 获取 AccessToken

https://open.dingtalk.com/document/development/obtain-the-access-token-of-an-internal-app?spm=ding_open_doc.document.0.0.15d327e2IajId6

> 注意： 本项目仅作为测试， AccessToken 有效期为 2h，不能频繁调用获取
>
> 生产环境获取 AccessToken 后，应存入缓存或缓存队列

### 根据用户手机号查询 userId

https://open.dingtalk.com/document/development/query-users-by-phone-number

> 注意：
>
> 1. CRM 员工手机号应与钉钉保持一致
> 2. 要处理接口调用失败/用户未找到等相关异常

### 根据 userId 创建群聊，并关联机器人

> 需要业务入库，让同一客户在同一个群里，不用每次都建新群

https://open.dingtalk.com/document/development/create-a-scene-group-v2

### 发送群助手消息，并@所有人

https://open.dingtalk.com/document/development/group-template-robot-message
