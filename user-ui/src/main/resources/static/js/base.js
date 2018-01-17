$(document).ajaxSend(function (event,request,settings) {

    var token = localStorage.getItem("access-token");

    request.setRequestHeader("access-token",token);

    console.log("token = "+token);
    console.log("准备发送ajax请求");
    
});

$(document).ajaxComplete(function (event,xhr,options) {

    if(xhr.status == 401){
        console.log("未授权");
        window.location.href = "/gate/login/student";

    }else{
        var token = xhr.getResponseHeader("access-token");

        console.log("token= "+token);
        if(token!=null && token != undefined && token !='undefined' && token !=''){
            localStorage.setItem("access-token","Bearar "+token);
        }

        var url = xhr.getResponseHeader("redirecturl");
        if(url!=null && url != undefined && url !='undefined' && url !=''){
            window.location.href = url;
        }
    }



});