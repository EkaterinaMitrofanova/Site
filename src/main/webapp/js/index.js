function checkEmail() {
    var email = $('#email'),
        pattern = /^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/i;
    if (pattern.test(email.val()) == false) {
        email.css('border','2px solid red');
    }
    else {
        email.css('border','1px solid #d77206');
    }
}
