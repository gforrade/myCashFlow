function formatMessages() {
	var $msgsBox = jQuery('.msgs-box.no-style');
	if (!$msgsBox.length)
		return;
	var $msgs = $msgsBox.find('.msgs-item').wrapInner('<p />').append('<a class="close"></a>').children('.close').click(function() {
		$(this).parent().fadeOut();
	});
	$msgsBox.removeClass('no-style').show();
	if (!$msgs.length)
		return;
	var msgsBoxTop = $msgsBox.offset().top;
	var $body = jQuery('html, body');
	$body.each(function() {
		if ($(this).scrollTop() > msgsBoxTop) {
			$body.animate({ scrollTop: msgsBoxTop }, 'fast');
			return false;
		}
	})
};