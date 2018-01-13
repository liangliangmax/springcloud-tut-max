### 关于引入tkmybatis

注意：micro-common中都是entity，本项目中引用了tk-mybatis，
使用该工具包可以像hibernate一样，调用一些共用的方法，
该工具使mybatis拥有了jpa相同的注解，由于jpa的注解在Persistence Api这个jar包中，在pom中只需要添加api这个pom文件即可，不需要引入tk-mybatis的pom文件，
如果引用了tk-mybatis的pom文件，则在启动类上需要加上@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})，
将DataSource类排除在外，否则项目启动时候会提示没有定义数据源，如果使用Persistence Api这个pom文件，则启动类上不需要加任何配置注解

----------------------------------

添加用户的时候是user-provider接受参数，然后传递给micro-service，provider中传输的时候需要
@RequestMapping(value = "/api/student/add",method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
表示将对象转成json字符串进行传输，service那边则需要以public int add(@RequestBody User user)这种方式接受


--------------------------------
### 关于怎么将单独的一个模块中的mapper文件扫描到本项目中

本示例中模拟了mybatis的夸jar包扫描mapper.xml文件
micro-service为微服务，product-mapper模拟别的jar包中的mapper.xml，product-provider为给页面提供数据的层
关系为product-provider调用micro-service，micro-service中注入product-mapper

想要在micro-service中注入product-mapper，需要在micro-service的的启动类上添加以下两个注解
@MapperScan({"com.example.microservice.mapper","com.example.product.service.mapper"})
@ComponentScan({"com.example.product.service","com.example.microservice"})
就是将自己包下的mapper文件扫描进去，然后把product里面的mapper也扫描进去，如果不扫描则注入失败。

在product-mapper中是不需要配置文件的，只需要放入mapper接口和实现xml即可。

在micro-service的DataSourceConfig文件中需要注意一个地方
bean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
一定要写成classpath*，否则可能找不到别的jar包的mapper.xml文件


* * *
### 关于filter的执行顺序

两种filter，MyZuulFilter extends ZuulFilter 和 JwtAuthenticationTokenFilter extends OncePerRequestFilter
zuul中，如果同时配置了这两种filter的话，先执行OncePerRequestFilter，后执行ZuulFilter


- - - 

### 关于超时
超时有ribbon超时和hystrix超时
```
ribbon:
  ConnectTimeout: 5000   #请求连接的超时时间
  ReadTimeout: 5000      #请求处理的超时时间


hystrix:
  command:
    default:
      execution:
        isolation:
          strategy: THREAD    #execution.isolation.strategy= THREAD|SEMAPHORE,两种线程隔离机制
        timeout:
          enabled: false
      isolation:
        thread:
          timeoutInMilliseconds: 10000 #断路器的超时时间需要大于ribbon的超时时间，不然不会触发重试。

```
超时时间按照这三个时间中最小的时间计算。
如果要设置整个springcloud的超时时间，则大致需要修改两处，一处是zuul，另一处是feign，因为zuul和feign默认都使用了ribbon，都使用了hystrix
即总共需要修改6个时间，才能保证整体的超时时间

---------------------------------------------

### 关于重试机制

springcloud有重试机制，即向某个服务发送请求，如果超时，则可以请求另一个服务，不要一超时就给用户返回一个404

```
spring:
    cloud:
        loadbalancer:
          retry:
            enabled: true #该参数用来开启重试机制，它默认是关闭的
            
ribbon:
  OkToRetryOnAllOperations: true    #对所有操作请求都进行重试
  MaxAutoRetriesNextServer: 3       #切换实例的重试次数
  MaxAutoRetries: 1       #对当前实例的重试次数

```

--------------------------------------------------
### 关于懒加载
```
ribbon:
    eager-load:             #饿加载模式
        enabled: true
        clients: micro-serivce
```

feignclient在初始化的时候默认不初始化，即懒加载，所以第一次启动完成之后请求不光是请求的时间，还得包含创建对象的时间，如果zuul中没有设置超时的时间，则默认时间为1s，则很容易超时
所以应该在启动的时候就应该进行初始化，避免之后的请求超时，正常的开启饿加载时候需要制定对应的service，但是zuul是没有对应的service的，因为他需要路由
所以zuul中需要添加
```
zuul:
  ignored-services: '*'  #如果一个服务匹配到了要忽略的列表, 但是它也明确的配置在路由列表中, 将不会被忽略,意思是zuul将忽略所有的路由，但是下面几个还是要路由的

```
然后再配置路由表，这样就可以了