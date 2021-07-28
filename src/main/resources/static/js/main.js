
const embedForm = (form) => {
    const elem = document.getElementById("my-form");

    ORBEON.fr.API.embedForm(
        elem,
        "/orbeon",
        "orbeon",
        selectedForm,
        "new",
        undefined,
        "Authorization='OAuth " + authorizationToken + "'"
    );
}