
## Intensive Command Unit(Utils) 加强命令工具

- 基于spring shell开发的一款命令行工具，用于对复杂命令的自定义。

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
![1631763790(1)](https://user-images.githubusercontent.com/30685788/133545987-600a56dd-d059-4bee-bf8e-d7a8f8e34f45.png)

- 启动

![image](https://user-images.githubusercontent.com/30685788/133546280-35dd0010-9359-4a88-8305-3edd1977ba1a.png)

可以看到生成了两个json文件，并自动进入了命令界面的交互模式

- 查看全部的默认命令 show d all

![image](https://user-images.githubusercontent.com/30685788/133546399-99f9fff4-d1ba-4a7e-a093-8b529d7cf927.png)

- 查看特定命令信息(如n1) show d n1

![image](https://user-images.githubusercontent.com/30685788/133546495-7fc2777c-74f0-4b55-844a-933065692871.png)




### 后续优化

- 支持自定义命令的动态传参
- 预置更多命令
- 支持交互式命令

