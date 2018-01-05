现在这一版gate是基于jwt写的，后台不生成session，登录完成之后后台直接返回token。

但是，感觉不是太好，原因有几点：
- 页面每次都得带着token来，如果用get请求的话，地址栏上会暴露token，虽然加密，但是很长，不好看
- 如果使用jquery+html的话，jquery发送ajax每次都得设置headers属性，很是不爽！！！
```javascript
$.ajax({
    cache: false,
    async: true,
    headers:{
        "Authorization":"Bearer "+token
    },
    url:"/ui/index/student",
    type: "post",

    success:function(result){
        
    }
});

```
- 还有一点就是不知道怎么将token设置到request的header里面，如果不设置header，并且不用get方式提交token，
那么以后只要在地址栏上敲回车，立马会被拦截



下面例子是ajax请求登录，然后页面完成跳转的代码，是回调执行完成后构建一个form表单，然后里面设置参数，模拟表单提交，
既可以将参数以post的方式提交，同时还可以页面跳转。真是解决了个千年难题！！！值得记录

``` javascript
$.ajax({
    cache: false,
    async: true,
    url:"/gate/auth/token",
    type:"post",
    dataType:"json",
    data: auth,
    success:function(result){
        var token = result.token;
        console.log(token);

        if(token){

            var form = $("<form></form>");
            form.attr('action', "/ui/index/student");
            form.attr('method', 'post');
            form.attr('target', '_self');

                var input1 = $("<input type='hidden' name='Authorization' />");
                input1.attr('value', token);
                form.append(input1);

            form.appendTo("body");
            form.css('display', 'none');
            form.submit();
            
        }


    }
});


```