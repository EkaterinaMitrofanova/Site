function checkEmail() {
    var email = $('#email'),
        pattern = /^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/i;
    if (pattern.test(email.val()) == false) {
        email.css('border','2px solid red');
        return false;
    }
    else {
        email.css('border','1px solid #d77206');
        return true;
    }
}

function checkRepass() {
    var pass = $('#pass'),
        repass = $('#repass');
    if (pass.val() != repass.val()) {
        repass.css('border','2px solid red');
        return false;
    }
    else {
        repass.css('border','1px solid #d77206');
        return true;
    }
}

$(function(){

    var field = ["nickname", "email", "pass", "repass", "sex"];

    $("form").submit(function() {
        var error=0;
        $("form").find(":input").each(function() {
            for(var i=0;i<field.length;i++){
                if($(this).attr("name")==field[i]){

                    if(!$(this).val()){
                        $(this).css('border', 'red 1px solid');
                        error=1;

                    }
                    else{
                        $(this).css('border', 'gray 1px solid');
                    }
                }
            }
        });
        var mes = $(".messenger");
        var err_text;
        if (!checkEmail()){
            error=2;
        }
        if (!checkRepass()){
            error = 3;
        }
        if(error==0){
            var form = $('.form').serialize();
            //noinspection JSUnresolvedFunction
            $.ajax({
            type: "post",
            url: "/registration",
            data: form,
            success: function (result) {
                if (result == "success") {
                    location.href = "/myPage"
                } else {
                    mes.html("There is already a user with this email.");
                    mes.fadeIn("slow");
                }
            },
            error: function () {
            }
        });
            return false;
        } else {
            if (error == 1) {
                err_text = "Not all required fields are filled!";
            }
            if (error == 2) {
                err_text = "Incorrect e-mail!";
            }
            if (error == 3) {
                err_text = "Wrong RePassword!"
            }

            mes.html(err_text);
            mes.fadeIn("slow");
            return false;
        }
    })
});