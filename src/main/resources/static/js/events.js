window.addEventListener('load', function () {
    if (window.parent) {
        // If in an iframe, notify parent that we have loaded, they can send us
        // any auth/cookie data they need to send
        window.parent.postMessage('orbeonFrameLoaded', '*');
    }
});

window.addEventListener('message', event => {
    // If we are in an iframe and parent sends a message, check if they sent
    // an auth token, then store that in cookies to be used with subsequent
    // requests
    const {accessToken, setToken} = event.data;

    if (setToken !== undefined && typeof setToken === "boolean" && authorizationToken !== accessToken) {
        if (setToken === true && !!accessToken) {
            fetch(`${contextPath}/api/token/verify`, {
                headers: {
                    Authorization: `OAuth ${accessToken}`
                }
            })
                .then(r => r.json())
                .then(response => {
                    location.href = `${contextPath}/forms?project=${projectId}`;
                })
                .catch(err => {
                    if (!location.pathname.includes(`${contextPath}/require-auth-token`)) {
                        location.href = `${contextPath}/require-auth-token?project=${projectId}`
                    }
                });
        } else {
            fetch(`${contextPath}/api/token/clear`)
                .finally(() => {
                    if (!location.pathname.includes(`${contextPath}/require-auth-token`)) {
                        location.href = `${contextPath}/require-auth-token?project=${projectId}`;
                    }
                });
        }
    }
});
