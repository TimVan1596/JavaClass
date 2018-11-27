<script>
    $(function() {
        $("form :input").each(function() {
            var $required = $("<strong class='high'>&nbsp;&nbsp;*<\/strong>");
            $(this).parent().append($required);
        });
        $('form :input').blur(function() {
            var $parent = $(this).parent();
            $parent.find(".formtips").remove();
            //验证密码
            if( $(this).is("#name")) {
                if( this.value == "" || this.value.length < 6) {
                    var errorMsg ="请输入至少6位密码";
                    $parent.append('<span class="formtips onError" >'+errorMsg+'<\/span>');
                }else{
                    var okMsg = '输入正确！';
                    $parent.append('<span class="formtips onSuccess">'+okMsg+"<\/span>");
                }
            }
            if( $(this).is("#email")) {
                if( this.value=="" || ( this.value!="" && !/.+@.+\.[a-zA-Z]{2,4}$/.test(this.value))) {
                    var errorMsg = '请输入正确的E_MAIL地址.';
                    $parent.append('<span class="formtips onError">'+errorMsg+'<\/span>');
                }else{
                    var okMsg = '输入正确！';
                    $parent.append('<span class="formtips onSuccess">'+okMsg+'<\/span>');
                }
            }
        });
    })
</script>