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



function basket(el,name) {
    let head={
        'Content-Type':'application/json'
    }
    let data = {
        elem:el
    }
    let nam= name.split(',')
    document.getElementById(el).innerHTML='в корзине'
    document.getElementById(el).style.backgroundColor = 'green'
    document.getElementById("name").innerText='Товар: '+nam[0]
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

