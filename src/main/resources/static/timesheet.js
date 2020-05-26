function onCellUpdate(cell) {

    let newPresence = cell.textContent;

    let employeeId = cell.getAttribute("data-employee-id");
    let year = cell.getAttribute("data-year");
    let month = cell.getAttribute("data-month");
    let day = cell.getAttribute("data-day");

    let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
    let csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');

    fetch(new Request('/api/timesheet'), {
        method: 'POST',
        body: JSON.stringify({
            employeeId, year, month, day, presence: newPresence,
        }),
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken,
        },
    }).then(response => {
        let animatedElement = cell.parentElement;

        if (!response.ok) {
            console.error("Ошибка при изменении ячейки", response);
            animateElement(animatedElement, "change-failed");
        } else {
            animateElement(animatedElement, "change-successful");
        }
    });
}

function animateElement(animatedElement, className) {
    animatedElement.classList.add(className);
    animatedElement.addEventListener("animationend", event => {
        event.target.classList.remove(className);
    });
}
