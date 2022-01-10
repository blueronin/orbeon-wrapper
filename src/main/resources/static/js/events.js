window.addEventListener('load', function () {
    messageParent({ loaded: true })
});

window.addEventListener('message', event => {
    // If we are in an iframe and parent sends a message, check if they sent
    // an auth token, then store that in cookies to be used with subsequent
    // requests, can be any data
});

const messageParent = function (message) {
    if (window.parent) {
        window.parent.postMessage({
                orbeon: true,
                childLocation: {
                    hash: location.hash,
                    host: location.host,
                    hostname: location.hostname,
                    href: location.href,
                    origin: location.origin,
                    pathname: location.pathname,
                    port: location.port,
                    protocol: location.protocol,
                    search: location.search || "?project=" + projectId,
                },
                message
            }, "*")
    }
}