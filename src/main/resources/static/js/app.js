document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll("[data-confirm-delete]").forEach((form) => {
        form.addEventListener("submit", (event) => {
            if (!window.confirm("ნამდვილად გსურს ამ ჩანაწერის წაშლა?")) {
                event.preventDefault();
            }
        });
    });
});
