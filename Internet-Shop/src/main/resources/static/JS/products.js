async function getResponse() {

    let response=await fetch('http://localhost:8080/products')
    let content=await response.json()
    let list=document.getElementById('products')
    for(key in content){
        list.innerHTML += `
                <tr id="${content[key].id}">
                <td>${content[key].name}</td>
                <td>${content[key].price}</td>
                <td><img src="/img/${content[key].url}" width="150" height="200"></td>
                <td>
                <button>Купить</button>
                <a onclick="update(${content[key].id},${content[key].id})"><img src="img/Круг.png" width="50" height="50"></a>
                </td>
                </tr>
                
`
    }
}
setTimeout(getResponse,100);

function update(idd,del){
    url='http://localhost:8080/product/del'
    let head={
        'Content-Type':'application/json'
    }
    let data = {
        id:idd
    }
    document.getElementById(del).style. display = 'none'
    return fetch(url,{
        method:"post",
        body:JSON.stringify(data),
        headers:head
    })
}







