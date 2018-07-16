## 当前进度
- 挑选合适的事件分发工具
	- redis
		- 没有消息确认机制
		- 有spring-data-redis实现，可以直接使用
	- disque
		- redis原作者antirez开发
		- 专门从redis衍生用于事件队列模型
		- 有对应的java实现jedisque
		- 分布式乱序队列
		- 只是一个纯粹的队列实现，支持持久化
		- 支持消息ACK和重试机制
		- OK就决定是你了
 	- spring-redis-mq
		- 个人开发，挑选优先级很低
	- **ActiveMQ**
	    - 原本感觉太大没打算用，但是SpringBoot自带了不用白不用23333  

        