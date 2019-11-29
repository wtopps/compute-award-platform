# compute-award-platform 算奖平台
业务中台型平台，及时计算型算奖平台

# 一、背景
该平台定位于业务中台，为业务平台提供计算奖励结果的能力，支持多种算奖算法模型，核心功能定位于红包、积分等奖池瓜分业务场景，例如支付宝春节集五福瓜分现金大奖、淘宝双十一参与互动玩法瓜分购物红包等业务场景。

![系统设计](https://raw.githubusercontent.com/wtopps/compute-award-platform/master/imgs/system_desgin.jpg)

# 二、技术选型

技术框架采用SpringBoot + Mybatis，存储层采用MySQL+HBase，缓存使用Redis。
一期中存储层使用MySQL，二期中计划采用HBase，以支持更大数据量的吞吐计算，并加入MQ，支持异步通知模型，方便与上游业务层进行解耦。

# 三、表结构

# 四、算法模型

# 五、业务流程

# 六、接口设计
