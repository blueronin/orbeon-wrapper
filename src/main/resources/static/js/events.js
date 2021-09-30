window.addEventListener('load', function () {
    if (window.parent) {
        // If in an iframe, notify parent that we have loaded, they can send us
        // any data they need to send
        window.parent.postMessage('orbeonFrameLoaded', '*');
    }
});

window.addEventListener('message', event => {
    // If we are in an iframe and parent sends a message, check if they sent
    // an auth token, then store that in cookies to be used with subsequent
    // requests
    const {accessToken, setToken} = event.data;

    if (setToken !== undefined && typeof setToken === "boolean" && getCookie(authCookieName) !== accessToken) {
        if (setToken === true && !!accessToken) {
            fetch(`${contextPath}/api/token/verify`, {
                headers: {
                    Authorization: `OAuth ${accessToken}`
                }
            })
                .then(r => r.json())
                .then(response => {
                    location.href = contextPath;
                })
                .catch(err => {
                    if (!location.pathname.includes(`${contextPath}/require-auth-token`)) {
                        location.href = `${contextPath}/require-auth-token`
                    }
                });
        } else {
            document.cookie = `${authCookieName}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=${contextPath}`

            if (!location.pathname.includes(`${contextPath}/require-auth-token`)) {
                location.href = `${contextPath}/require-auth-token`;
            }
        }
    }
});
