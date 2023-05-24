
function Validator(options){

    function validate(inputElement, rule) {
        var errMessage = rule.test(inputElement.value);
        var errElement = inputElement.parentElement.querySelector(options.errorSelector);

        if(errMessage) {
            errElement.innerText = errMessage;
            inputElement.parentElement.classList.add('invalid');
        } else {
            errElement.innerText = "";
            inputElement.parentElement.classList.remove('invalid');
        }
    }

    var formElement = document.querySelector(options.form);

    if(formElement) {
        formElement.onsubmit = function(e) {
            e.preventDefault();

            options.rules.forEach(function (rule) {
                var inputElement = formElement.querySelector(rule.selector);
                validate(inputElement, rule);
            });
        }
    }



    if(formElement) {
        options.rules.forEach(function (rule) {
            var inputElement = formElement.querySelector(rule.selector);
            if(inputElement) {

                inputElement.onblur = function() {
                    validate(inputElement, rule);
                }
                inputElement.oninput = function() {
                    var errElement = inputElement.parentElement.querySelector(options.errorSelector);
                    errElement.innerText = "";
                }
            }
        });

    }
}

Validator.isRequired = function(selector) {
    return {
        selector:selector,
        test: function(value) {
            return value.trim() ? undefined : 'Vui lòng nhập đầy đủ thông tin'

        }
    };
}

Validator.isEmail = function(selector) {
    return {
        selector:selector,
        test: function(value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.com)+$/;
            return regex.test(value) ? undefined : 'Vui lòng nhập đúng định dạng email'
        }
    };
}

Validator.isPassword = function(selector,min) {
    return {
        selector:selector,
        test: function(value) {
            return value.length == min ? undefined : 'Vui lòng nhập mật khẩu gồm 8 kí tự'

        }
    };
}


var ad = document.getElementById('admin');
ad.oninvalid = function(event) {
    event.target.setCustomValidity('Username should only contain lowercase letters. e.g. john');
}

var nm = document.getElementById('name');
nm.oninvalid = function(event) {
    event.target.setCustomValidity('Username should only contain lowercase letters. e.g. john');
}

var pn = document.getElementById('phoneNumber');
pn.oninvalid = function(event) {
    event.target.setCustomValidity('Username should only contain lowercase letters. e.g. john');
}