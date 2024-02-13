function banUser() {
    var userId = document.getElementById("userId").value;
    console.log("userId= {}", userId);

    $.ajax({
        url: '/admin/users/' + userId + '/ban',
        type: 'POST',
        success: function(response) {
            alert('사용자가 제한되었습니다.');
            window.location.replace('/admin/users');
        },
        error: function(xhr, status, error) {
            alert('오류가 발생했습니다: ' + error);
        }
    });
}
