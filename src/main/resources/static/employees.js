function onEmployeeUpdate(cell) {
    let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
    let csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');
    let tableRowElement = cell.parentElement;
    let employeeId = tableRowElement.getAttribute("data-employee-id");
    let request = {
        name: tableRowElement.querySelector(`td[data-field="name"]`).textContent,
        surname: tableRowElement.querySelector(`td[data-field="surname"]`).textContent,
        birthDate: tableRowElement.querySelector(`td[data-field="birthDate"]`).textContent,
        positionName: tableRowElement.querySelector(`td[data-field="position"]`).textContent,
        remoteWork: tableRowElement.querySelector(`[data-field="remoteWork"]`).checked,
        address: tableRowElement.querySelector(`td[data-field="address"]`).textContent,
        department: tableRowElement.querySelector(`td[data-field="department"]`).textContent
    };
    fetch(new Request(`/api/employees/${employeeId}`), {
        method: 'PATCH',
        body: JSON.stringify(request),
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

function onEmployeeDelete(button) {
    if (!confirm("Действительно хотите удалить сотрудника?"))
        return;

    let employeeId = button.getAttribute("data-employee-id");
    let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
    let csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');

    fetch(new Request('/api/employees/' + employeeId), {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken,
        },
    }).then(response => {
        let tableRow = document.querySelector(`tr[data-employee-id="${employeeId}"]`);
        if (!response.ok) {
            console.error("Ошибка при удалении сотрудника", response);
            animateElement(tableRow, "change-failed")
        } else {
            tableRow.remove();
        }
    });
}