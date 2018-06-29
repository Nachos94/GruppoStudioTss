/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//funzione che nasconde la pag di login o di sigin


var urlLog = "http://localhost:8080/ProgettoEsameCiac/ciaccloud/userstore/caricaprofilo";
var urlSig = "http://localhost:8080/ProgettoEsameCiac/ciaccloud/userstore/saveuser";


angular.module("application", []).controller("controller", function ($scope, $http) {

    $scope.jUser = {};
    $scope.login = false;
    $scope.showS = function () {
        $scope.login = false;
    };
    $scope.showL = function () {
        $scope.login = true;
    };
    //funzione che effettua chiamata ajax per login o sigin 


    var richiesta;

    $scope.logga = function () {
        richiesta =
                {"user": {

                        "username": document.getElementById("nomeutente").value,
                        "password": document.getElementById("secret").value

                    },
                    "filecloud": "",
                    "username": "",
                    "password": "",
                    "id": ""};


        var richiestas = JSON.stringify(richiesta);

        $http({
            method: "POST",
            url: urlLog,
            data: richiestas,

        }).then(function (response) {

            window.location = "benvenuto.html";

            localStorage.setItem("user", angular.toJson(response.data));


        }, function (response) {

            alert(response.header);


        });
    }

    $scope.registrati = function () {

        richiesta =
                {"user": {

                        "username": document.getElementById("username").value,
                        "password": document.getElementById("password").value,
                        "email": document.getElementById("email").value,
                        "nome": document.getElementById("nome").value,
                        "cognome": document.getElementById("cognome").value
                    },
                    "filecloud": "",
                    "username": "",
                    "password": "",
                    "id": ""};

        var richiestas = JSON.stringify(richiesta);

        $http({
            method: "POST",
            url: urlSig,
            data: richiestas

        }).then(function () {
            //conferma di avvenuta iscrizione
            alert("UTENTE REGISTRATO");
        },
                function (response) {

                    alert(response.header.causa);
                });



    };

})      