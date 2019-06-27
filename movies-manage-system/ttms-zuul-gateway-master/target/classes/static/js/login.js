window.onload = function(){
    let userPhone = $("#userPhone").val();
    let userPass = $("#userPass").val();
    let user_pass = $("#user_pass").val();
    let userName = $("#userName").val();

    // $("#register").click(Register(userPhone,userPass,userName));
    $("#register").click( function(){
        Register($("#userPhone").val(),$("#userPass").val(),$("#userName").val());
    
    });

    //登录函数
    function Register(phone,pass,name){
        jQuery.support.cors=true;
        console.log('准备注册');
        $.ajax({
            url: 'http://www.ttms.com/user/register',
            tyep: 'post',
            contentType:'application/json;charset=utf-8',
            method: 'post',
            // xhrFields: {
            //     withCredentials: true
            // },
            dataType: 'JSON',
            data:JSON.stringify({
                "userPhone" : phone,
                "userPassword" : pass,
                "userName" : name
            }),
            success: function(res){
                console.log('success...');
                console.log('--------------');
                console.log(res);
            },
            error: function(xhr) {
                // 出错
                
                alert('error:' + JSON.stringify(xhr));
            }
          });
    }

}