# Parser

命令解释器

从doc中读入命令的格式，构造正则表达式，解析输入的命令

constructor(File file):指定doc
init():初始化
parse(String s):解析命令s，若非法则抛出异常，否则返回其参数列表

doc格式：
命令名称 特定字符串

list
get tinyFileName
help
