function onDepartmentUpdate(cell) {
    let newName = cell.textContent;
    let departmentId = cell.getAttribute("data-dep-id");
    let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
    let csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');

    fetch(new Request('api/departments'), {
        method: 'POST',
        body: JSON.stringify({
            id: departmentId, name: newName,
        }),
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken,
        },
    }).then(response => {
        let animatedElement = cell.parentElement;
        animateElement(animatedElement, "change-successful");
    });
}

function onDepartmentDelete(button) {
    if (!confirm("Действительно хотите удалить департамент?"))
        return;

    let departmentId = button.getAttribute("data-department-id");
    let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
    let csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');

    fetch(new Request('/api/departments/' + departmentId), {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken,
        },
    }).then(response => {
        let tableRow = document.querySelector(`tr[data-department-id="${departmentId}"]`);
        if (!response.ok) {
            console.error("Ошибка при удалении департамента", response);
            animateElement(tableRow, "change-failed")
        } else {
            tableRow.remove();
        }
    });
}