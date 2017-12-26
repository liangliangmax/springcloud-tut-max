注意：micro-common中都是entity，本项目中引用了tk-mybatis，
使用该工具包可以像hibernate一样，调用一些共用的方法，
该工具使mybatis拥有了jpa相同的注解，由于jpa的注解在Persistence Api这个jar包中，在pom中只需要添加api这个pom文件即可，不需要引入tk-mybatis的pom文件，
如果引用了tk-mybatis的pom文件，则在启动类上需要加上@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})，
将DataSource类排除在外，否则项目启动时候会提示没有定义数据源，如果使用Persistence Api这个pom文件，则启动类上不需要加任何配置注解




添加用户的时候是user-provider接受参数，然后传递给micro-service，provider中传输的时候需要
@RequestMapping(value = "/api/student/add",method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
表示将对象转成json字符串进行传输，service那边则需要以public int add(@RequestBody User user)这种方式接受