
window.addEventListener('message', event => {
    // If we are in an iframe and parent sends a message, check if they sent
    // an auth token, then store that in cookies to be used with subsequent
    // requests
    const { accessToken, setToken } = event.data;

    if (setToken !== undefined && typeof setToken === "boolean") {
        if (setToken === true) {
            document.cookie = `${authCookieName}=${accessToken}; path=${contextPath}`
        } else {
            document.cookie = `${authCookieName}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=${contextPath}`
        }

        if (localStorage.getItem('token') !== accessToken) {
            localStorage.setItem('token', accessToken);
            window.location.href = "/";
        }
    }
});

window.addEventListener('load', function () {
    if (window.parent) {
        // If in an iframe, notify parent that we have loaded, they can send us
        // any data they need to send
        window.parent.postMessage('orbeonFrameLoaded', '*');
    }
});
