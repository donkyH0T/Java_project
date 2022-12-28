async function getResponse() {

    let response=await fetch('http://localhost:8080/basketStamps')
    let content=await response.json()
    let list=document.getElementById('products')
    for(key in content){
        list.innerHTML += `
                <tr id="${content[key].id}">
                <td>${content[key].name}</td>
                <td>${content[key].price}</td>
                <td><img src="/img/${content[key].url}" width="250" height="200"></td>
                <td>
                <button style="margin: 10px 0px;">Купить</button>
                
                <a onclick="update(${content[key].id},${content[key].id})"><img src="img/Круг.png" width="50" height="50"></a>
                </td>
                </tr>
                
`
    }
}
setTimeout(getResponse,100);

function update(id,del){
    url='http://localhost:8080/basket/del'
    let head={
        'Content-Type':'application/json'
    }
    let data = {
        id:id
    }
    document.getElementById(del).style. display = 'none'
    return fetch(url,{
        method:"post",
        body:JSON.stringify(data),
        headers:head
    })
}







