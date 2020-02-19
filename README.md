# 简易证券交易数据管理系统
## 模块划分
当前实现的模块有：
+ 包模块
    + 作为模块之间数据传递的基本单位
+ 输入模块
    + 从数据库外输入数据
    + 当前输入途径主要是从.day及.min中读取数据
    + 操作流程
        1. 创建一个Inputer类的实例，利用File类或文件路径将其初始化
        2. 调用getPackage方法，返回一个Storable对象
+ 存储模块
    + 将数据存储到数据库中
    + 操作流程
        1. 创建一个Storer对象
        2. 调用该对象store方法，参数是要存储的Storable对象
+ 读取模块
    + 从数据库中读取数据
    + 包括无条件读取和有条件读取
    + 操作流程
        1. 创建一个Accessable/LimAccessable对象，初始化exchangeName,stockNum以及限制条件
        2. 创建一个Accesser实例，构造函数参数为Accessable对象
        3. 调用accesser方法，返回Accessable对象
    + 该进方案，将Accessable/LimAccessable置为一个super的子类
+ 计算模块
    + 计算
    + Computer接口下的计算
        + 操作流程
            1. 用源Packages对象，输入类型和输出类型初始化Computer实例
            2. 调用compute方法，得到目标Packages对象
    + MComputer接口下的计算
        + 操作流程
            1. 创建一个Mcomputer实例
            2. 将Mcomputable数组初始化
            3. 调用initialize方法，参数为Mcomputable数组
            4. 调用compute方法，得到Mcomputed数组