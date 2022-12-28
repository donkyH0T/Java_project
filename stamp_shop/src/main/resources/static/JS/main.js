let form;
let action;
function findElements() {
    form = document.querySelector('form');
    ({ action } = form);
}

function subscribe() {
    form.addEventListener('submit', onSubmit);
}
function init() {
    findElements();
    subscribe();

}
function onSubmit(event) {
    event.preventDefault();

    const { currentTarget } = event;
    sendForm(currentTarget)
        .then(response => onSuccess(response, currentTarget));
    document.getElementById('name').value = '';
    document.getElementById('text').value = '';
    update()

}
function onSuccess(response) {
    return response.json();
}
function sendForm(currentForm) {
    return fetch(action, setOptions(currentForm));
}
function setOptions(currentForm) {
    return {
        method: 'post',
        body: collectData(currentForm),
    };

}
function collectData(currentForm) {
    const data = new FormData(currentForm);
    return data;

}


init();

function update(){
    let list=document.querySelector('.comment')
    list.innerHTML=''
    setTimeout(getResponse,500)
}

async function getResponse() {

    let response=await fetch('http://localhost:8080/comments')
    let content=await response.json()
    let list=document.querySelector('.comment')
    for(key in content.reverse()){
        list.innerHTML += `
            <div class="otziv">
                <p><span>${content[key].name}</span></p>
                <p>${content[key].text}</p>
             </div>   `
    }
}
getResponse()


