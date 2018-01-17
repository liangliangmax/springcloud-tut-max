### 要做一个jquery的单页面web应用，使用jquery.pjax插件
- 浏览器兼容性https://caniuse.com/#search=pushstate

- 第一步，引入jquery，然后引入pjax
```
<link href="../bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<script src="../js/jquery-2.1.1.min.js" type="text/javascript"></script>

<script src="../bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<script src="../js/jquery.pjax.min.js" type="text/javascript"></script>
```

然后在页面上定义了两个按钮，和一个主渲染div，以后请求的页面内容都将在这个主div中渲染，相当于点击按钮时候请求的是页面的结构，请求完成页面之后由页面的ajax自己去请求数据
```
<div class="container-fluid" id="container">
    <button class="btn btn-default" type="button" id="button1">Button1</button>
    <button class="btn btn-default" type="button" id="button2">Button2</button>


    <div id="main">


    </div>
</div>

```

打开这个页面时候页面会触发请求加载首页该显示的内容，请求main页面还得是ajax进行请求
```
//加载首页
$(document).ready(function () {
    $.ajax({
        cache: false,
        async: true,
        url:"/ui/index/main",
        type:"get",

        success:function(result){

            $("#main").html(result);
        },

    });
});

```

然后给两个按钮绑定事件
```
        $("#button1").on("click",function () {
//            $.ajax({
//                cache: false,
//                async: true,
//                url:"/ui/index/button1",
//                type:"get",
//
//                success:function(result){
//
//                    $("#main").html(result);
//                },
//
//            });

            $.pjax({
                url: '/ui/index/button1',
                container: '#main'
            });
        });
        
        $("#button2").on("click",function () {

            $.pjax({
                url: '/ui/index/button2',
                container: '#main'
            });
        });
```
以前按钮请求页面的方法是发送ajax，浏览器不能前进后退，现在改成了pjax请求，点击按钮时候浏览器的前进回退功能就可以正常使用了；
加载完成页面之后页面可以用ajax写自己的页面加载完成事件，可以与后台进行交互进行数据请求，数据会进行渲染。


那么问题来了，点前进后退没问题了，但是如果直接在浏览器上点刷新，就挂掉了
问题原因是：
- 正常请求的路径是http://localhost:8765/ui/index/student，打开的页面是index页面，当页面加载时候回触发加载主显示区main的ajax请求，渲染主显示区，现在的地址栏信息是http://localhost:8765/ui/index/student
- 当点击button1时候在主显示区中加载button1的页面，但是现在地址栏变成了http://localhost:8765/ui/index/button1，如果此时刷新的话则相当于直接请求了主显示区的页面，但是这个页面中并不包含各种样式，所以导致页面直接没样式