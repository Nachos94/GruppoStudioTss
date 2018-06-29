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
                var file = document.getElementById("File");
                              
                    
                
                var data = new FormData()
                data.append("file", file.files[0]);
                data.append("identificativo", identificativo);
                data.append("username", user.username);
                data.append("token", user.token);
                data.append("tokenend", user.tokenend);
                event.preventDefault()

                $http({
                    
                url:urlSaveFile,   
                method: "POST",
                body: data

              }).then(response => {
                 console.log(response.status)
              
                datipersonali();
                
            })



        }
    })    