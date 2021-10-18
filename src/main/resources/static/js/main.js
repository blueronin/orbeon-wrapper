$(document).ready(function () {
    $("#accordion").accordion({
        collapsible: true,
        heightStyle: "content"
    });

    const orbeonSummaryTableSelector = ".orbeon div.fr-mode-summary div.fr-summary-table-div table tbody";
    let interval;

    const observeDOM = (function(){
        const MutationObserver = window.MutationObserver || window.WebKitMutationObserver;

        return function( obj, callback ){
            if( !obj || obj.nodeType !== 1 ) return;

            if(MutationObserver){
                // define a new observer
                const mutationObserver = new MutationObserver(callback)
                // have the observer observe foo for changes in children
                mutationObserver.observe(obj, { childList:true, subtree:true })
                return mutationObserver
            }
            // browser support fallback
            else if(window.addEventListener){
                obj.addEventListener('DOMNodeInserted', callback, false)
                obj.addEventListener('DOMNodeRemoved', callback, false)
            }
        }
    })()

    const rewriteOrbeonSummaryPageUrls = (element) => {
        if (!$(element)) {
            clearInterval(interval);
            return;
        }
        // A hack to get around orbeon properties file where we are supposed to override the URLs not working,
        $(`${orbeonSummaryTableSelector} tr[class^=fr-summary-row] td span a[href^=\\/fr\\/]`).each(function () {
            let href = $(this).attr('href');
            href = href.replace('/fr/', `${contextPath}/forms/`);
            $(this).attr('href', href);
        });

        $(orbeonSummaryTableSelector).each(function () {
            // Observe a specific DOM element:
            observeDOM(this, (m) => rewriteOrbeonSummaryPageUrls(this));
        });
    }

    const tableBodyElements = $(orbeonSummaryTableSelector);

    if (tableBodyElements && tableBodyElements[0]) {
        rewriteOrbeonSummaryPageUrls(tableBodyElements[0]);
        // 1 second might be too small and can cause browser freezing,
        // might want to give it some higher value in case it causes trouble,
        // the interval was setup to solve the builder summary not working after search,
        // even though the fr summary page was working
        interval = setInterval(rewriteOrbeonSummaryPageUrls, 1000, tableBodyElements[0])
    }
});