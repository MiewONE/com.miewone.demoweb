var index= {
    init : function ()
    {
        var _this = this;
        $('#btn-save').on('click',function(){
            _this.save();
        });
        $('#btn-update').on('click',function(){
            _this.update();
        });
        $('#btn-delete').on('click',function(){
            _this.delete();
        });
    },
    save : function ()
    {
        var data={
            name:$('#name').val(),
            date:$('#date').val(),
            phNo:$('#phNo').val(),
            email:$('#email').val(),
            personID:$('#personID').val(),
            sex:$('#sex').val(),
            zip:$('#zip').val(),
            address:$('#address').val(),
            detailAddress:$('#detailAddress').val(),
            password:$('#password').val(),
            position:$('#position').val(),
            jobType:$('#jobType').val()
        };
        $.ajax({
            type:'POST',
            url:'/api/v1/posts/post',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data)
        }).done(function()
        {
            alert('글이 등록되었습니다.');
            window.location.href='/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    update : function ()
    {
        var data= {
            name:$('#name').val(),
            date:$('#date').val(),
            phNo:$('#phNo').val(),
            email:$('#email').val(),
            personID:$('#personID').val(),
            sex:$('#sex').val(),
            zip:$('#zip').val(),
            address:$('#address').val(),
            detailAddress:$('#detailAddress').val(),
            password:$('#password').val(),
            position:$('#position').val(),
            jobType:$('#jobType').val()
        };
        var id = $('#id').val();

        $.ajax({
            type:'PUT',
            url:'/api/v1/posts/'+id,
            dataType:'json',
            contentType: 'application/json;charset=utf-8',
            data:JSON.stringify(data)
        }).done(function()
        {
            alert('글이 수정되었습니다.');
            window.location.href='/';
        }).fail(function ()
        {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
index.init();