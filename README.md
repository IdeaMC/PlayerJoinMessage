# PlayerJoinMessage

by: xiantiao

> 1代支持 {player} 变量  
> 设置关闭加入消息  
> 首次与非首次加入服务器的消息区别

<details>
<summary>1代教程(点我打开)</summary>

>命令
> /playerjoinmessage reload - 重载

![1711261427100.png](https://img.fastmirror.net/s/2024/03/24/65ffc6ee93803.png)

>配置文件
```yaml
# 配置文件版本
ver: 2

# 是否取消发送默认的加入消息
notSendJoinMessage: true

# 第一次加入全服播报
firstJoinMessage:
  - "[新玩家加入] &b {player}"

# 第二次及之后加入播报
JoinMessage:
  - "你好&b&l{player}&r 欢迎来到服务器"
```

</details>

![新玩家](https://img.fastmirror.net/s/2024/03/24/65ffc65c98f50.png)
![老玩家](https://img.fastmirror.net/s/2024/03/24/65ffc65d22294.png)