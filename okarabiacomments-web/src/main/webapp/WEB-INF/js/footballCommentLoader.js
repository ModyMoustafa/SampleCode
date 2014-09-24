var iframe = document.createElement("iframe");

iframe.width = IFRAME_WIDTH + "px";
iframe.style.width = IFRAME_WIDTH + "px";
iframe.style.overflow = "hidden";

iframe.src = IFRAME_SRC;

iframe.frameborder = 0;

var container = document.getElementById(IFRAME_CONTAINER_ID);
if (container) {
	container.appendChild(iframe);
}

function sendParentPageUrl(event) {
	if (!isNaN(event.data)) {
		iframe.height = event.data + "px";
		iframe.style.height = event.data + "px";
	} else if (event.data == "getParentUrl") {

		var href = document.location.href;

		if (href.indexOf("?") != -1) {
			var basrUrl = href.split("&containerid");
			var targetUrl = basrUrl[0] + "&containerid=" + IFRAME_CONTAINER_ID + "&hashCode="
			+ HashCode + "&category=" + Category + "&leagueId="
			+ LeagueId + "&teamAId=" + TeamAId + "&teamBId=" + TeamBId
			+ "&key=" + Key ;
			iframe.contentWindow.postMessage(targetUrl, "*");
		} else {
			var basrUrl = href.split("?containerid");
			var targetUrl = basrUrl[0] + "?containerid=" + IFRAME_CONTAINER_ID + "&hashCode="
			+ HashCode + "&category=" + Category + "&leagueId="
			+ LeagueId + "&teamAId=" + TeamAId + "&teamBId=" + TeamBId
			+ "&key=" + Key ;
			iframe.contentWindow.postMessage(targetUrl, "*");
		}

	} else {
		// perform redirection here
		document.location = event.data;
	}
}

if (window['addEventListener']) {
	window.addEventListener("message", sendParentPageUrl, false);
} else {
	window.attachEvent("onmessage", sendParentPageUrl);
}
