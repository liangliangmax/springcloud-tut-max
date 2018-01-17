$(document).ajaxSend(function (event,request,settings) {

    var token = localStorage.getItem("access-token");

    request.setRequestHeader("access-token",token);

});

$(document).ajaxComplete(function (event,xhr,options) {
    var token = xhr.getResponseHeader("access-token");

    console.log("token= "+token);
    if(token!=null && token != undefined && token !='undefined' && token !=''){
        localStorage.setItem("access-token","Bearar "+token);
    }

    var url = xhr.getResponseHeader("redirecturl");
    if(url!=null && url != undefined && url !='undefined' && url !=''){
        window.location.href = url;
    }
});