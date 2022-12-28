url = "http://localhost:8080/basket/add";
let modal = document.getElementById('myModal');
let span = document.getElementsByClassName("close")[0];


span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}



function basket(id,name) {
    let head={
        'Content-Type':'application/json'
    }
    let data = {
        id:id
    }
    let nam= name.split('#')
    document.getElementById(id).innerHTML='В корзине'
    document.getElementById(id).style.backgroundColor = '#00FF7F'
    document.getElementById("name").innerText='Марка: '+nam[0]
    document.getElementById("price").innerText='Цена: '+nam[1]+'₽'
    modal.style.display = "block";
    modal.style.left='363px';
    modal.style.right='33px';
    modal.style.top='50px';
    return fetch(url,{
        method:"post",
        body:JSON.stringify(data),
        headers:head
    })
}

function f() {
    modal.style.display ='none';
}

