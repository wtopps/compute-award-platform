# compute-award-platform 算奖平台
业务中台型平台，及时计算型算奖平台

# 一、背景
该平台定位于业务中台，为业务平台提供计算奖励结果的能力，支持多种算奖算法模型，核心功能定位于红包、积分等奖池瓜分业务场景，例如支付宝春节集五福瓜分现金大奖、淘宝双十一参与互动玩法瓜分购物红包等业务场景。

![系统设计](https://raw.githubusercontent.com/wtopps/compute-award-platform/master/imgs/system_desgin.jpg)

# 二、技术选型

技术框架采用SpringBoot + Mybatis，存储层采用MySQL+HBase，缓存使用Redis。
一期中存储层使用MySQL，二期中计划采用HBase，以支持更大数据量的吞吐计算，并加入MQ，支持异步通知模型，方便与上游业务层进行解耦。

# 三、表结构

```
CREATE TABLE `activity` (
`id`  bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`biz_id`  tinyint(4) UNSIGNED NOT NULL COMMENT '业务类型' ,
`activity_id`  bigint(20) UNSIGNED NOT NULL COMMENT '活动ID' ,
`answer`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '答案' ,
`compute_type`  tinyint(4) UNSIGNED NOT NULL COMMENT '算法类型' ,
`pool_num`  bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '奖池金额，单位分' ,
`status`  tinyint(4) UNSIGNED NULL DEFAULT 0 COMMENT '活动状态，0——初始化，1——接受明细 2——算奖中 3——已结束' ,
`remain`  bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '奖池余额，除不尽的毛刺' ,
`compute_time`  datetime NULL DEFAULT NULL COMMENT '定时开奖时间' ,
`receive_limit`  int(8) UNSIGNED NULL DEFAULT 0 COMMENT '单用户接收算奖次数上限' ,
`extra_data`  varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '扩展属性字段，格式JSON' ,
`create_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `uk_activity_id` (`activity_id`, `biz_id`) USING BTREE COMMENT '活动ID唯一索引'
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
ROW_FORMAT=DYNAMIC
;
```

```
CREATE TABLE `compute_job` (
`id`  bigint(20) UNSIGNED NOT NULL ,
`biz_id`  tinyint(4) UNSIGNED NOT NULL COMMENT '业务类型' ,
`activity_id`  bigint(20) UNSIGNED NOT NULL COMMENT '活动ID' ,
`average_award`  bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '人均奖励值' ,
`correct_num`  int(10) UNSIGNED NULL DEFAULT 0 COMMENT '中奖用户人数' ,
`task_num`  int(8) UNSIGNED NULL DEFAULT 0 COMMENT '算奖任务数' ,
`finish_num`  int(8) UNSIGNED NULL DEFAULT 0 COMMENT '完成任务数' ,
`status`  tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '任务状态，0——初始化，1——算奖中 2——已完成' ,
`create_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `uk_activity_id` (`activity_id`, `biz_id`) USING BTREE COMMENT '活动ID唯一索引'
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
ROW_FORMAT=DYNAMIC
;
```

# 四、算法模型

# 五、业务流程

# 六、接口设计
