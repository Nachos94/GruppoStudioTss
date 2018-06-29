/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var urlSaveFile = "http://localhost:8080/ProgettoEsameCiac/ciaccloud/userstore/aggiungifile";
var urlLog = "http://localhost:8080/ProgettoEsameCiac/ciaccloud/userstore/caricaprofilo";
var urlGet = "http://localhost:8080/ProgettoEsameCiac/ciaccloud/userstore/get";

angular.module("benvenuto", []).controller("controller", function ($scope, $http) {

    
    var user;
    (function inizializza() {

        user = JSON.parse(localStorage.getItem("user"));
        
        $scope.Nome = user.nome;
        $scope.Cognome = user.cognome;
              
      
         
        // {"data" : "20/06/1994" , "identificativo" : "andrea"}
    
    })();
    
    

    function datipersonali() {


        var richiesta = {"user": {
                "id": user.id,
                "username": user.username,
                "password": user.password,
                "email": user.email,
                "nome": user.nome,
                "cognome": user.cognome,
                "token": user.token,
                "tokenend": user.tokenend,
                "fileassociati": user.fileassociati
            },
            "filecloud": "",
            "username": "",
            "password": "",
            "id": ""};


        var richiestas = JSON.stringify(richiesta);


        $http({
            method: "POST",
            url: urlGet,
            data: richiestas,

        }).then(function (response) {


        localStorage.setItem("user", angular.toJson(response.data));
            
        user = JSON.parse(localStorage.getItem("user"));
        
          $scope.files = {};
        
        
        
        
       

        }, function (response) {

            alert(response.header);


        });



    }









    $scope.fileupload = function () {

        var user = JSON.parse(localStorage.getItem("user"));
        var identificativo = document.getElementById("File").value.toString().substring(document.getElementById("File").value.toString().lastIndexOf("\\") + 1);
        var file = document.getElementById("File");

        var data = new FormData()
        data.append("file", file.files[0]);
        data.append("identificativo", identificativo);
        data.append("userid", user.id);
        data.append("token", user.token);
        data.append("tokenend", user.tokenend);





        fetch(urlSaveFile,
                {
                    method: "POST",
                    body: data

                }).then(response => {


            datipersonali();
        })






    }
})    