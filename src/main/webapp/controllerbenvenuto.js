/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var urlSaveFile = "http://localhost:8080/ProgettoEsameCiac/ciaccloud/userstore/aggiungifile";

angular.module("benvenuto", []).controller("controller", function ($scope, $http) {


    (function datipersonali() {

        var user = JSON.parse(localStorage.getItem("user"));

        $scope.Nome = user.nome;
        console.log(user.nome);
        $scope.Cognome = user.cognome;

        if (user.fileassociati != null) {
            $scope.fileassociati = user.fileAssociati;
        }

    })();





    $scope.fileupload = function () {

        var user = JSON.parse(localStorage.getItem("user"));

        var identificativo = document.getElementById("File").value.toString().substring(document.getElementById("File").value.toString().lastIndexOf("\\") + 1);

        console.log(identificativo);


        var filecloud;
        var file = document.getElementById("File");
       
        var data  = file.files[0];

        console.dir(data);

            filecloud = {
                "file": data,//array,
                "identificativo": identificativo,
                "user": user
            };

       


        var richiesta = {
            "user": user,
            "filecloud": filecloud,
            "username": "",
            "password": "",
            "id": ""};

        var richiestas = JSON.stringify(richiesta);


        $http({

            method: "POST",
            url: urlSaveFile,
            data: richiestas,

        }).then(function (response) {

            alert("upload completato")

        }), function (response) {

            alert(response.status)

        }
    

    }


    function filedownload() {


    }


});




