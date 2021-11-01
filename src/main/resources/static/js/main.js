$(document).ready(function () {
    // noinspection JSUnresolvedFunction
    $("#accordion").accordion({
        collapsible: true,
        heightStyle: "content"
    });

    const orbeonSummaryTableSelector = ".orbeon div.fr-mode-summary div.fr-summary-table-div table tbody";
    const orbeonSummaryNewBtnSelector = ".orbeon form .fr-view span.fr-new-button button";
    const orbeonPdfBtnSelector = ".fr-pdf-button button#o0xf-776≡xf-1059≡≡c⊙1";
    let interval;

    const observeDOM = (function(){
        // noinspection JSUnresolvedVariable
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

    const hideFormTitleCol = function () {
        const titleCol = $("div.fr-summary-table-div table thead tr");
        if (titleCol && titleCol[0]) {
            const position = $(titleCol[0]).find("th:contains(Form Title)").index();
            if (position !== -1) {
                $(`div.fr-summary-table-div table tr th:nth-child(${position + 1}), div.fr-summary-table-div table tr td:nth-child(${position + 1})`).hide();
            }
        }
    }

    const rewriteOrbeonSummaryPageUrls = (element) => {
        if (!$(element)) {
            clearInterval(interval);
            return;
        }
        // A hack to get around orbeon properties file where we are supposed to override the URLs not working,
        $(`${orbeonSummaryTableSelector} tr[class^=fr-summary-row]`).each(function () {
            let documentId = null;

            $(this).find("td span a[href^=\\/fr\\/]").each(function () {
                let href = $(this).attr('href');
                href = href.replace('/fr/', `${contextPath}/forms/`);
                $(this).attr('href', href);

                const segments = href.split('/');
                const tmp = segments.pop() || segments.pop(); // Handle potential trailing slash
                if (tmp.length >= 40) {
                    // Only way to confirm its the ID.. for now
                    documentId = tmp;
                }
            });

            if (documentId && model['app'] !== "orbeon" && model.form !== "builder") {
                // Dont add this to the builder summary, only edit is available for that.
                const draftText = $(this).find("td:nth-child(2) #o0xf-671≡≡c⊙1").text();

                if (draftText.toLowerCase() === "draft") {
                    $(this).append(`<td class="summary-review-text w-32"></td>`)
                } else {
                    $(this).append(
                        `<td class="summary-review-text w-32">
                            <a href="${contextPath}/forms/${model['app']}/${model.form}/view/${documentId}">
                                <i class="fa fa-eye"></i> Review
                            </a>
                        </td>`
                    )
                }
            }
            hideFormTitleCol();
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

    const orbeonSummaryNewBtn = $(orbeonSummaryNewBtnSelector);
    if (orbeonSummaryNewBtn) {
        orbeonSummaryNewBtn.on('click', function (e) {
            e.preventDefault();
            e.stopPropagation();
            location.href = `${contextPath}/forms/${model['app']}/${model.form}/new`
        })
    }

    // Auto fill the FormTitle with the Form name value so we dont have `Untitled Form` as the value
    const formName = $("#o0dialog-form-settings≡xf-1588≡xf-1800≡xf-1839 input")
    if (formName && formName[0]) {
        const formTitle = $("#o0dialog-form-settings≡xf-1588≡xf-1800≡xf-1845 input")
        $(formName[0]).on('keyup', function (e) {
            if (formTitle && formTitle[0]) {
                if (ORBEON && ORBEON.xforms && ORBEON.fr) {
                    ORBEON.xforms.Document.setValue(formTitle[0], e.target.value)
                }
            }
        })
    }

    const dropdown = $(".dropdown > .dropdown-menu");
    if (dropdown) {
        dropdown.menu()
        $(".dropdown > .dropdown-toggle").on('click', function (e) {
            // First hide and clear all open menus
            $(".dropdown > .dropdown-menu").hide();
            $(".dropdown").removeClass("open")

            // Then open the selected menu
            const activeDataRole = $(e.target).closest("button").attr("data-role")
            $(`.dropdown > .dropdown-menu[data-role="${activeDataRole}"]`).toggle(200, function () {
                $(this).parent().addClass("open")
            })
        });

        $(document).on('click', function(event) {
            // Hide dropdown when clicked outside
            if(!$(event.target).closest('.dropdown').length && !event.target.classList.contains('dropdown')) {
                $(".dropdown > .dropdown-menu").hide();
                $(".dropdown").removeClass("open")
            }
        });
    }

    let pdfBtn = $(orbeonPdfBtnSelector)
    if (pdfBtn && pdfBtn[0]) {
        // First remove all handlers/listeners so we attach custom listener to link to custom href
        $(orbeonPdfBtnSelector).replaceWith(pdfBtn[0].cloneNode(true))

        $(orbeonPdfBtnSelector).on('click', function (e) {
            e.preventDefault();
            e.stopPropagation()

            // Find anchor tag abd get href
            const a = $(".fr-pdf-button a#o0xf-776≡xf-1057⊙1")

            // Replace with orbeon URl to prevent/bypass nginx/spring not passing query params to orbeon
            let href = a.attr("href")
            href = href.split("/orbeon-wrapper/orbeon/o/", 2)[1]
            href = `${orbeonUrl}/${href}`

            const queryParams = {
                authorization: headers['Authorization'],
                project_id: headers['ProjectId'],
                team_id: headers['TeamId']
            }
            const queryString = new URLSearchParams(queryParams).toString()

            if (href.includes("?")) {
                href = `${href}&${queryString}`
            } else {
                href = `${href}?${queryString}`
            }

            if (window !== window.parent) {
                // Its in an iframe, let parent handle this action. Iframe is always blocked
                messageParent({ openLink: true, href })
            } else {
                window.open(href, '_blank', "").focus();
            }
        })
    }

    let hash = localStorage.getItem("hash")
    if (hash === '#form-builder' && !window.location.pathname.includes(`${contextPath}/forms/orbeon/builder`)) {
        window.location.href = `${contextPath}/forms/orbeon/builder`
    } else {
        $(".tabs").tabs({
            active: hash === "#form-builder" ? 1 : 0,
            collapsible: false,
            beforeActivate: function(event, ui) {
                hash = event.currentTarget.hash;
                localStorage.setItem("hash", hash)

                switch (hash) {
                    case '#form-runner':
                        window.location.href = `${contextPath}/forms/`
                        break;
                    case '#form-builder':
                        window.location.href = `${contextPath}/forms/orbeon/builder`
                        break;
                    default:
                        break;
                }
            }
        })
    }

    $("a[data-action=copy]").on("click", function (event) {
        event.preventDefault();
        const href = event.currentTarget.href;
        if (navigator.clipboard) {
            navigator.clipboard.writeText(href);
        } else {
            alert(`Cannot copy the link: ${href} to clipboard in an insecure context.`)
        }
    });

    if (isShared) {
        // Hide Summary and close buttons on shared forms
        $("span#o0xf-437⊙1, span#o0xf-437⊙4").css({ display: "none" })
    }
});
