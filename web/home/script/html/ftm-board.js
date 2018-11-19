

function addBorderMsg() {
    let $ftmBoardMsg = $('#ftm-board-msg');

    let $message = $ftmBoardMsg.val();

    //创造文章节点
    let $li = $("#BORDER_MSG_TEMPLATE").html();
    $("#ftm-board-box").append($li);
    $(".ftm-border-msg:last").text($message);

    //清空
    $ftmBoardMsg.val('');
}