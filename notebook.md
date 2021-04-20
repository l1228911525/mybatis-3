# mybatis的调用过程

1.接口的代理类（proxy）调用session的方法

2.session方法调用Executor内的方法

3.Executor调用StatementHandler的方法

4.StatementHandler生成JDBC的Statement来运行sql