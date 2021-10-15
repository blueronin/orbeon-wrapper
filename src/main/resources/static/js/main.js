$(document).ready(function () {
    $("#accordion").accordion({
        collapsible: true,
        heightStyle: "content"
    });

    (function rewriteOrbeonSummaryPageUrls () {
        // A hack to get around orbeon properties file where we are supposed to override the URLs not working,
        const tableBodyElements = $(".orbeon table tbody");
        if (tableBodyElements && tableBodyElements[0]) {
            let tableBodyElement = tableBodyElements[0];
            const clonedElement = tableBodyElement.cloneNode(true);
            $(tableBodyElement).replaceWith(clonedElement);

            $(".orbeon table tbody tr[class^=fr-summary-row] td span a[href^=\\/fr\\/]").each(function () {
                let href = $(this).attr('href');
                href = href.replace('/fr/', `${contextPath}/forms/`);
                $(this).attr('href', href);
            });
        }
    })()
});