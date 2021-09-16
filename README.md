
## Intensive Command Unit(Utils) 加强命令工具

- 基于spring shell开发的一款命令行工具，用于对复杂命令的自定义。
- 支持以-或者--或者空格的方式传参运行

### 流程

- 启动 java -jar ceshiren-icu.jar

- 启动时默认会在工具所在目录生成command.json文件和conf.json文件

1. command.json：用于工具命令存储，格式如下：

```json
{
	"n1": {
		"desc": "get node version",
		"key": "n1",
		"value": "node -v"
	},
	"pi": {
		"desc": "get pip version",
		"key": "pi",
		"value": "pip -V"
	},
	"g1": {
		"desc": "get git version",
		"key": "g1",
		"value": "git --version"
	},
	"v1": {
		"desc": "get vue version",
		"key": "v1",
		"value": "vue -V"
	}
}

```
> 用户可通过工具的add命令添加，或者直接修改文件内容。为了防止文件结构错误和中文乱码，建议通过add命令添加。

2. conf.json：用于工具的基础配置信息，格式如下：

```json
{
	"output": {
		"desc": "-o --output 控制台输出配置 1 详细输出 2 简单输出",
		"key": "output",
		"value": "1"
	}
}
```
> 暂时用处不大，为系统保留设置文件，不可删除

### 示例

- 新环境中的工具
- 
![image](https://user-images.githubusercontent.com/30685788/133551035-bc0b65c5-7e0d-4881-93a2-4c1f6a08e154.png)

- 启动

![image](https://user-images.githubusercontent.com/30685788/133551126-3f41fab7-3273-4c40-864f-e8f14be2cd3f.png)

可以看到生成了两个json文件，并自动进入了命令界面的交互模式，如果是第二次在原目录中启动，则会自动加载修改后的json文件内容，并以修改后的json文件内容为准。

- 查看全部的命令 show d all

![image](https://user-images.githubusercontent.com/30685788/133551195-a979ba2e-f9aa-410f-8e7f-b8f8259deb1e.png)

- 查看全部的配置 show d all

![image](https://user-images.githubusercontent.com/30685788/133552432-e9311d5e-f6ce-42b1-bb93-22e35edf1ae6.png)


- 查看特定命令信息(如n1) show d n1

![image](https://user-images.githubusercontent.com/30685788/133551275-f6a1a9b1-8a76-43b4-98ce-7d8a4c11eeff.png)

- 运行命令，通过sh command 的形式运行，如 sh n1

![image](https://user-images.githubusercontent.com/30685788/133551375-366375b1-df46-4675-8065-369250851628.png)

- 添加自定义命令 add key vlaue desc，其中，key为自定义的运行命令，value为实际运行的原始命令，desc为描述信息，如 add n2 "node -v" "测试node命令"

![image](https://user-images.githubusercontent.com/30685788/133551598-c08e1dc4-4d59-43ea-a23a-b670f1c2aef1.png)

> 如果新自定义命令的key和原自定义命令的key相同，则以新自定义命令的key的实际命令为准。

- 运行新添加的命令 sh n2

![image](https://user-images.githubusercontent.com/30685788/133551979-d820196e-e3c8-4447-8e51-d74ec4c6a88c.png)

- 如果命令不存在时，直接运行，则会提示运行失败

![image](https://user-images.githubusercontent.com/30685788/133552203-d3941d7d-6183-437b-aa7f-f179c66fa113.png)


- 运行help命令，查看帮助

![image](https://user-images.githubusercontent.com/30685788/133552498-7752e08b-3259-4ade-a5c7-52de21a4a3ee.png)




### 后续优化

- 支持自定义命令的动态传参
- 预置更多命令
- 支持交互式命令
- 完善help命令中的帮助信息

