# Telegram与QQ连通计划
## 概述
实现Telegram和QQ的消息互通，目标是能够在Telegram上无障碍地收发QQ消息和对QQ进行简单的操作，即通过Telegram操作指定QQ

## 功能要求
- [] 能够接收来自QQ的私聊文本消息并支持回复
- [] 支持黑名单功能，不接收来自指定私聊/群组/讨论组消息
- [] 支持指定Telegram群组绑定接收来自QQ指定私聊/群组/讨论组的消息
- [] 能够接收来自QQ的群聊文本消息并支持回复
- [] 能够接收来自QQ的讨论组文本消息并支持回复
- [] 支持接收来自QQ的私聊图片消息
- [] 支持接收来自QQ的群聊和讨论组图片消息
- [] 支持从Telegram发送sticker表情至QQ
- [] 支持从Telegram发送图片至QQ
- [] 支持从Telegram发送GIF至QQ
- [] 支持从Telegram发送emoji至QQ
- [] 支持读取QQ群组的文件并下载

## 技术栈
- 使用telegram-bot-api对接telegram
- 使用CoolQ以及CoolQ-http-api对接QQ
- 使用GSON作为json处理工具
- 使用SpringBoot

## 概要设计
    


https://github.com/kurikomoe/QQ2TG
https://github.com/OYMiss/forward-bot
https://github.com/richardchien/coolq-http-api
https://cqhttp.cc/docs/3.4/#/
https://github.com/yangjinhe/maintain-robot/tree/master/src/main/java/com/yjh
https://github.com/rubenlagus/TelegramBots