
const embedForm = () => {
    const elem = document.getElementById("form-container");
    if (model) {
        ORBEON.fr.API.embedForm(
            elem,
            "/orbeon",
            model['app'],
            model['form'],
            model['action'],
            undefined,
            "Authorization='OAuth " + authorizationToken + "'"
        );
    }
}
