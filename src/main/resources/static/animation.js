function animateElement(animatedElement, className) {
    animatedElement.classList.add(className);
    animatedElement.addEventListener("animationend", event => {
        event.target.classList.remove(className);
    });
}
