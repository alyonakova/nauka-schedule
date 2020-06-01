function onEmployeeUpdate() {

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