let ajax_UrlPlusParam = function (url,callback,type) {
    $.ajax({
        url:url,
        type:type,
        success:function (data) {
            callback(data);
        },error:function () {
            console.log('error');
        }
    });
}

let ajax_InsertParamWithUrl = function (data,url,callback,type) {
    $.ajax({
        url:url,
        type:type,
        data:data,
        success:function (data) {
            callback(data);
        },error:function () {
            console.log('error');
        }
    });
}

let ajax_multipartform = function (formData,url,callback,type) {
    $.ajax({
        url:url,
        type:type,
        enctype:'multipart/form-data',
        data:formData,
        processData:false,
        contentType:false,
        success:function(data){
            callback(data);
        },error:function(){
            console.log('error');
        }
    });
}