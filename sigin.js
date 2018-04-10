
function log() {

    let username = document.getElementById("InputUsername").value
    console.log(username)
    let email = document.getElementById("InputEmail").value
    console.log(email)
    let password = document.getElementById("InputPassword").value
    console.log(password)

    let sigin = {
        "nome": username,
        "email": email,
        "password": password
    }

    fetch("http://localhost:8080/resources/user/autentica",
        {
            method: "POST",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(sigin)

        }).then(response => {
            return response.json()
        })
        .then(json => {
            console.log(json.token)
            localStorage.setItem("usertoken", json.token);
            document.getElementsByTagName('body').item(0).innerHTML = profilobody

        })

}

var profilobody = " <figure id='imgprofilo' class='figure'> "
    + " <img src='https://i.kinja-img.com/gawker-media/image/upload/s--o7h8bBex--/c_scale,fl_progressive,q_80,w_800/1344155503227793766.jpg' class='figure-img img-fluid rounded' alt='A generic square placeholder image with rounded corners in a figure.'> "
    + "<figcaption class='figure-caption text-right'>immagine del profilo</figcaption>"
    + " </figure>  <div> "
    + "<h4>Benvenuto all'interno del tuo profilo</h4>"
    + "</div>"
    + "<button type='button' class='btn btn-primary' onclick='Nuovo();'>Nuovo</button>"
    + "<button type='button' class='btn btn-primary' onclick='mostraOggi();'>Inseriti Oggi</button>"
    + "<button type='button' class='btn btn-primary' onclick='mostratutto();'>Tutti</button> "
    + " <div class='list-group' id='listatodo'> "
    + " <a href='#' class='list-group-item list-group-item-action active'></a>"
    + "</div>"
    + "<form id='input'>"
    + "<div class='form-group'>"
    + "<label for='InputTitolo'>ToDo</label>"
    + "<input type='text' class='form-control' id='titolo' aria-describedby='emailHelp' placeholder='titolo del tuo ToDo'>"
    + "<small id='TitoloHelp' class='form-text text-muted'>inserisci il titolo del tuo ToDo</small>"
    + "<label for='InputTesto'>Testo</label>"
    + "<input type='text' class='form-control' id='testo' placeholder='Testo'>"
    + "<label for='InputData'>Il</label>"
    + "<input type='text' class='form-control' id='il' placeholder='data'>"
    + "</div>"
    + "<button type='button' class='btn btn-primary' onclick='inserisciNuovo();'>Submit</button>"
    + "</form>"

function sig() {

    let username = document.getElementById("InputUsername").value
    console.log(username)
    let email = document.getElementById("InputEmail").value
    console.log(email)
    let password = document.getElementById("InputPassword").value
    console.log(password)

    let sigin = {
        "nome": username,
        "email": email,
        "password": password
    }

    fetch("http://localhost:8080/resources/user",
        {
            method: "POST",
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(sigin)

        })
}

function mostratutto() {

    document.getElementById("input").style.opacity = "0";
    //dentro a list-group deve andare scritto "<a href="#" class="list-group-item list-group-item-action active"></a>"
    //al cui interno va aggiunto il titolo del ToDo

    let container = document.getElementById("listatodo");
    container.innerHTML = "";

    fetch("http://localhost:8080/resources/todo",
        {
            method: "GET",
                headers: {
                   'Authorization' : 'bearer ' + localStorage.getItem("usertoken")
           } 
        })
        .then(response => {
            console.log(response)
            return response.json();

        }).then(contenuto => {
            var elemento = `${contenuto.map(obj => `<a href="#" id="${obj.id}"class="list-group-item list-group-item-action" onclick="mostradati()"> ${obj.titolo} </a>`).join("")}`
            container.innerHTML = elemento;
        })

}


function Nuovo() {
    document.getElementById("input").style.opacity = "1";

}

function inserisciNuovo() {

    let titolo = document.getElementById("titolo").value
    let testo = document.getElementById("testo").value
    let il = document.getElementById("il").value

    let jfilex = {

        "titolo": titolo,
        "testo": testo,
        "il": il,

    }

    let data = new URLSearchParams()
    data.append("titolo", titolo);
    data.append("testo", testo);
    data.append("il", il);

    console.log(data)
    fetch("http://localhost:8080/resources/todo",
        {
            method: "POST",
            body: data
        }).then(

            alert(data)
        )


}