# 分页获取企业下的服务记录信息

通过本接口分页获取企业下的服务记录信息

## 权限

要调用此API，需要以下权限之一。

| 应用类型       | 是否支持 | 权限                         | API Explorer调试                                             |
| -------------- | -------- | ---------------------------- | ------------------------------------------------------------ |
| 企业内部应用   | 支持     | 钉钉AI销售管理服务数据读权限 | [API Explorer](https://open-dev.dingtalk.com/apiExplorer#/?devType=org&api=dvi_1.0%23ListServiceRecord) |
| 第三方企业应用 | 暂不支持 | 暂不支持                     | 暂不支持                                                     |
| 第三方个人应用 | 暂不支持 | 暂不支持                     | 暂不支持                                                     |

## 请求方法

```
GET /v1.0/dvi/service-records?maxResults=Integer&nextToken=String&endTime=Long&startTime=Long&userId=String&teamCode=String HTTP/1.1
x-acs-dingtalk-access-token:String
Content-Type:application/json
```

## Header参数

| 名称                        | 类型   | 是否必填 | 描述                                                         |
| --------------------------- | ------ | -------- | ------------------------------------------------------------ |
| x-acs-dingtalk-access-token | String | 是       | 调用该接口的访问凭证。如何获取不同应用的凭证请参考[访问凭证概述](https://developers.dingtalk.com/document/app/authorization-overview)。 |

## Query参数

| 名称       | 类型    | 是否必填 | 描述                            |
| ---------- | ------- | -------- | ------------------------------- |
| maxResults | Integer | 否       | 每页返回的数据量，最多20条      |
| nextToken  | String  | 否       | 下一页的数据token，首次查询可空 |
| endTime    | Long    | 否       | 服务结束时间                    |
| startTime  | Long    | 否       | 服务开始时间                    |
| userId     | String  | 否       | 员工ID                          |
| teamCode   | String  | 否       | 团队编码                        |

## 返回参数

| 名称           | 类型    | 描述                                   |
| -------------- | ------- | -------------------------------------- |
| nextToken      | String  | 下一页查询的token,有下页数据时候返回。 |
| totalCount     | Integer | 总量                                   |
| result         | Array   | 服务记录列表                           |
| recordId       | String  | 服务记录ID                             |
| user           | Object  | 服务用户 员工离职后为空                |
| deviceSn       | String  | 服务设备SN                             |
| startTimestamp | Long    | 服务开始时间戳 单位毫秒                |
| endTimestamp   | Long    | 服务结束时间戳 单位毫秒                |
| duration       | String  | 服务持续时长 单位毫秒                  |
| customerId     | String  | 客户ID：绑定过客户时返回               |
| team           | Object  | 服务所属团队、门店信息                 |

## 示例

**请求示例**

```
GET /v1.0/dvi/service-records?maxResults=10&nextToken=d45309d81673333b&endTime=123453&startTime=432123&userId=3243ad&teamCode=1234 HTTP/1.1
Host:api.dingtalk.com
x-acs-dingtalk-access-token:11c3588e697234f4ac04c0cf56884012
Content-Type:application/json
```

**返回示例**

```
HTTP/1.1 200 OK
Content-Type:application/json

{
  "nextToken" : "003122acf7fc49e2b0463aa21cb0c3c7",
  "totalCount" : 30,
  "result" : [ {
    "recordId" : "3dea-94e2-4cfa-bd5e-5fd0",
    "user" : {
      "name" : "rui",
      "userId" : "092165"
    },
    "deviceSn" : "T-C4474303",
    "startTimestamp" : 1765171448000,
    "endTimestamp" : 1765171452000,
    "duration" : "180000",
    "customerId" : "1234",
    "team" : {
      "name" : "文一西路店",
      "code" : "f4c82713-f2bd-4226-80df-f135fe"
    }
  } ]
}
```

# 获取服务记录转写文本



![AI 智能摘要](https://gw.alicdn.com/imgextra/i4/O1CN01Zx4PED22rkUkmRe1L_!!6000000007174-2-tps-226-40.png)![>](https://gw.alicdn.com/imgextra/i2/O1CN01UwOv1C27lrTuGFj2D_!!6000000007838-2-tps-17-30.png)

更新于 2025-12-08通过本接口获取AI销售管理中的服务记录转写文本信息



## 权限

要调用此API，需要以下权限之一。

| 应用类型       | 是否支持 | 权限                       | API Explorer调试                                             |
| -------------- | -------- | -------------------------- | ------------------------------------------------------------ |
| 企业内部应用   | 支持     | 钉钉语音智能分析结果读权限 | [API Explorer](https://open-dev.dingtalk.com/apiExplorer#/?devType=org&api=dvi_1.0%23GetServiceRecordTranscript) |
| 第三方企业应用 | 暂不支持 | 暂不支持                   | 暂不支持                                                     |
| 第三方个人应用 | 暂不支持 | 暂不支持                   | 暂不支持                                                     |

## 请求方法

```
GET /v1.0/dvi/service/transcript?id=String HTTP/1.1
x-acs-dingtalk-access-token:String
Content-Type:application/json
```





## Header参数

| 名称                        | 类型   | 是否必填 | 描述                                                         |
| --------------------------- | ------ | -------- | ------------------------------------------------------------ |
| x-acs-dingtalk-access-token | String | 是       | 调用该接口的访问凭证。如何获取不同应用的凭证请参考[访问凭证概述](https://developers.dingtalk.com/document/app/authorization-overview)。 |

## Query参数

| 名称 | 类型   | 是否必填 | 描述   |
| ---- | ------ | -------- | ------ |
| id   | String | 是       | 文件ID |

## 返回参数

| 名称   | 类型   | 描述     |
| ------ | ------ | -------- |
| result | Object | 转写结果 |

## 示例

**请求示例**

``````
GET /v1.0/dvi/service/transcript?id=A81C5670-04BE-18F3-8081-0E640FF7810D HTTP/1.1
Host:api.dingtalk.com
x-acs-dingtalk-access-token:abfe08769d45309d81673333bbb
Content-Type:application/json
``````



**返回示例**

```
HTTP/1.1 200 OK
Content-Type:application/json

{
  "result" : {
    "audionText" : {
      "status" : "PROCESSING",
      "dataList" : [ {
        "channel" : "1",
        "startTime" : "3598",
        "endTime" : "34987",
        "text" : "你好"
      } ]
    },
    "speaker" : {
      "status" : "COMPLETED",
      "dataList" : [ {
        "channel" : "1",
        "role" : "saler"
      } ]
    }
  }
}
```





1. 在 dingding/feign 下完成 新的 feign, 参考 DingdingRequestInterceptor 完成新的 interceptor,   完成以上两个接口
2. 获取服务记录转写文本, 根据 channel 将 role 回写到对应的文本中, 生成 `...销售: text1\n客户:text2\n...` 这样的完整文本
3. 在ApiTestController中完成单个测试接口, 
4. 在BusinessController完成一个新的接口,  `获取近7天转写文本`
   + 轮询查询近7天所有服务记录
   + 根据服务id获取所有文本
   + 将每条文本保存为` {user::name}_{recordId}.md`, 到 ` file/output` 目录下

# 二.查询用户详情

更新于 2025-09-10调用本接口获取指定用户的详细信息。



**说明**

调用本接口可以实现查询普通账号用户详情或查询企业账号用户详情。由于在调用时参数使用有较多区别，为便于开发者查看，按照新用户的账号类型进行拆分优化：

- 查询普通账号用户详情，接口说明文档请查看本文介绍。
- 查询企业账号用户详情，接口说明文档请参见[查询企业账号用户详情](https://open.dingtalk.com/document/development/queries-the-details-of-a-dedicated-account#)。



![img](https://help-static-aliyun-doc.aliyuncs.com/assets/img/zh-CN/2215042461/p385751.png)



## 权限

### 添加接口权限

服务端API是以应用维度授权的，在调用接口前，确保已经为应用添加了接口权限。

| 应用类型       | 是否支持调用 | 权限申请方式   | API Explorer调试                                             |
| -------------- | ------------ | -------------- | ------------------------------------------------------------ |
| 企业内部应用   | 是           | 成员信息读权限 | [调试](https://open-dev.dingtalk.com/apiExplorer#/?devType=org&api=dingtalk.oapi.v2.user.get) |
| 第三方企业应用 | 是           | 成员信息读权限 | [调试](https://open-dev.dingtalk.com/apiExplorer#/?devType=isv&api=dingtalk.oapi.v2.user.get) |
| 第三方个人应用 | 否           | —              | —                                                            |

### 添加获取手机号和邮箱权限

如果需要获取手机号和邮箱等权限，不同的应用类型获取方式不同。

- 企业内部应用

  企业内部应用如需获取用户的手机号、邮箱信息，需要添加获取通讯录中手机号和邮箱的权限。添加方法如下：

  登录[钉钉开发者后台](https://open-dev.dingtalk.com/#/corpeapp) > **企业内部开发** > **找到需添加权限的应用** > **权限管理** > **通讯录管理**页面，勾选**企业员工手机号信息**和**邮箱等个人信息**，单击申请权限。

  ![img](https://help-static-aliyun-doc.aliyuncs.com/assets/img/zh-CN/3187780561/p433431.gif)

  

- 第三方企业应用

  第三方企业应用如需获取用户手机号、邮箱信息，可以使用[钉钉统一授权套件](https://open.dingtalk.com/document/development/function-description#)方式获取。

## 基本信息

**请求方式**：POST

**请求地址**：`https://oapi.dingtalk.com/topapi/v2/user/get`

## Query参数

| 名称         | 类型   | 是否必填 | 示例值   | 描述                                                         |
| ------------ | ------ | -------- | -------- | ------------------------------------------------------------ |
| access_token | String | 是       | be3Fxxxx | 调用该API的应用凭证。企业内部应用，通过[获取企业内部应用的access_token](https://open.dingtalk.com/document/development/obtain-orgapp-token#)接口获取。第三方企业应用，通过[获取第三方企业的access_token](https://open.dingtalk.com/document/development/obtain-isvapp-token#)接口获取。 |

## Body参数

| 名称     | 类型   | 是否必填 | 示例值      | 描述                                                 |
| -------- | ------ | -------- | ----------- | ---------------------------------------------------- |
| userid   | String | 是       | manager4220 | 用户的userId。                                       |
| language | String | 否       | zh_CN       | 通讯录语言。**zh_CN**：中文（默认值）**en_US**：英文 |

## 返回参数

| 名称               | 类型           | 示例值                                                   | 描述                                                         |
| ------------------ | -------------- | -------------------------------------------------------- | ------------------------------------------------------------ |
| request_id         | String         | 4e7exhl6pm0t                                             | 请求ID。                                                     |
| errcode            | Number         | 0                                                        | 返回码。                                                     |
| errmsg             | String         | ok                                                       | 返回码描述。                                                 |
| result             | Object         |                                                          | 返回结果。                                                   |
| userid             | String         | zhangsan                                                 | 员工的userId。                                               |
| unionid            | String         | z21HjQliSzpw0YWCNxmii6u2Os62cZ62iSZ                      | 员工在当前开发者企业账号范围内的唯一标识。                   |
| name               | String         | 张三                                                     | 员工姓名。                                                   |
| avatar             | String         | xxx                                                      | 头像。**说明**员工使用默认头像，不返回该字段，手动设置头像会返回。 |
| state_code         | String         | 86                                                       | 国际电话区号。**说明**第三方企业应用不返回该字段；如需获取state_code，可以使用[钉钉统一授权套件](https://open.dingtalk.com/document/development/function-description#)方式获取。 |
| manager_userid     | String         | user01                                                   | 员工的直属主管。**说明**员工在企业管理后台个人信息面板中，**直属主管**内有值，才会返回该字段。 |
| mobile             | String         | 18513027676                                              | 手机号码。**说明**企业内部应用，只有应用开通通讯录**企业员工手机号信息**权限，才会返回该字段。![img](https://help-static-aliyun-doc.aliyuncs.com/assets/img/zh-CN/9757964471/p942695.png)第三方企业应用不返回该字段，如需获取mobile，可以使用[钉钉统一授权套件](https://open.dingtalk.com/document/development/function-description#)方式获取。 |
| hide_mobile        | Boolean        | false                                                    | 是否号码隐藏：**true**：隐藏**false**：不隐藏**说明**隐藏手机号后，手机号在个人资料页隐藏，但仍可对其发DING、发起钉钉免费商务电话。 |
| telephone          | String         | 010-86123456-2345                                        | 分机号。**说明**第三方企业应用不返回该参数。                 |
| job_number         | String         | 4                                                        | 员工工号。                                                   |
| title              | String         | 技术总监                                                 | 职位。                                                       |
| email              | String         | test@xxx.com                                             | 员工邮箱。**说明**企业内部应用，只有应用开通通讯录**邮箱等个人信息**权限，才会返回该字段。第三方企业应用，不返回该参数；如需获取email，可以使用[钉钉统一授权套件](https://open.dingtalk.com/document/development/function-description#)方式获取。 |
| work_place         | String         | 未来park                                                 | 办公地点。**说明**员工信息面板中该字段必须有值，才正常返回。如果无值，则不返回该字段。企业内部应用，只有应用开通通讯录**邮箱等个人信息**权限，才会返回该字段。第三方企业应用，不返回该参数。 |
| remark             | String         | 备注备注                                                 | 备注。**说明**员工信息面板中该字段必须有值，才正常返回。如果无值，则不返回该字段。企业内部应用，只有应用开通通讯录**邮箱等个人信息**权限，才会返回该字段。第三方企业应用，不返回该参数。 |
| exclusive_account  | Boolean        | false                                                    | 是否为企业账号：true：是false：不是                          |
| org_email          | String         | test@xxx.com                                             | 员工的企业邮箱。如果员工的企业邮箱没有开通，返回信息中不包含该数据。**说明**第三方企业应用不返回该参数。 |
| dept_id_list       | Number[]       | [2,3,4]                                                  | 所属部门id列表。                                             |
| dept_order_list    | Object[]       |                                                          | 员工在对应的部门中的排序。                                   |
| extension          | String         | {"爱好":"旅游","年龄":"24"}                              | 扩展属性，最大长度2000个字符。**说明**员工信息面板中添加的拓展字段内有值才返回。企业内部应用，只有应用开通通讯录**邮箱等个人信息**权限，才会返回该字段。第三方企业应用，不返回该字段。**重要提示**：直接添加新属性会覆盖原有属性值，需要先获取现有属性，然后将新属性追加到已有属性上，再进行整体更新。 |
| hired_date         | Number         | 1597573616828                                            | 入职时间，Unix时间戳，单位毫秒。**说明**信息面板中**入职时间**字段内有值才返回。第三方企业应用，不返回该参数。 |
| active             | Boolean        | true                                                     | 是否激活了钉钉：**true**：已激活**false**：未激活            |
| real_authed        | Boolean        | true                                                     | 是否完成了实名认证：**true**：已认证**false**：未认证        |
| senior             | Boolean        | true                                                     | 是否为企业的高管：**true**：是**false**：不是                |
| admin              | Boolean        | true                                                     | 是否为企业的管理员：**true**：是**false**：不是              |
| boss               | Boolean        | true                                                     | 是否为企业的老板：**true**：是**false**：不是                |
| leader_in_dept     | Object[]       |                                                          | 员工所在部门信息及是否是领导：员工所在部门的部门ID。员工在对应的部门中是否是领导：true：是false：不是 |
| role_list          | Object[]       |                                                          | 角色列表。                                                   |
| union_emp_ext      | Object         |                                                          | 当用户来自于关联组织时的关联信息。**说明**用户所在企业存在关联关系的企业，返回该字段。 |
| dept_position_list | DeptPosition[] |                                                          | 部门内任职信息。                                             |
| extension_i18n     | Json           | {"爱好":{"zh_ CN":"旅游","en_ US":"travel","aJP":"旅行"} |                                                              |

```
{
        "errcode":"0",
        "result":{
                "extension":"{\"爱好\":\"旅游\",\"年龄\":\"24\"}",
                "unionid":"z21HjQliSzpw0YWxxxxx",
                "boss":"true",
                "role_list":{
                        "group_name":"职务",
                        "name":"总监",
                        "id":"100"
                },
                "exclusive_account":false,
                "manager_userid":"manager240",
                "admin":"true",
                "remark":"备注备注",
                "title":"技术总监",
                "hired_date":"1597573616828",
                "userid":"zhangsan",
                "work_place":"未来park",
                "dept_order_list":{
                        "dept_id":"2",
                        "order":"1"
                },
                "real_authed":"true",
                "dept_id_list":"[2,3,4]",
                "job_number":"4",
                "email":"test@xxx.com",
                "leader_in_dept":{
                        "leader":"true",
                        "dept_id":"2"
                },
                "mobile":"18513027676",
                "active":"true",
                "org_email":"test@xxx.com",
                "telephone":"010-86123456-2345",
                "avatar":"xxx",
                "hide_mobile":"false",
                "senior":"true",
                "name":"张三",
                "union_emp_ext":{
                        "union_emp_map_list":{
                                "userid":"5000",
                                "corp_id":"dingxxx"
                        },
                        "userid":"500",
                        "corp_id":"dingxxx"
                },
                "state_code":"86"
        },
        "errmsg":"ok"
}
```

在  RestDingClient 中新增本接口 
