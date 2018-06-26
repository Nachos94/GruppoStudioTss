/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//funzione che nasconde la pag di login o di sigin


var urlLog = "http://localhost:8080/ProgettoEsameCiac/ciaccloud/userstore/caricaprofilo";
var urlSig = "http://localhost:8080/ProgettoEsameCiac/ciaccloud/userstore/saveuser";


angular.module("application", []).controller("controller", function ($scope, $http) {


    $scope.login = false;

    $scope.showS = function () {
        $scope.login = false;
    };

    $scope.showL = function () {
        $scope.login = true;
    };

    //funzione che effettua chiamata ajax per login o sigin 
    $scope.jUser = {};
    
    var richiesta ;
    
    
    $scope.logga = function () {
        
    console.log($scope.jUser);
     
        richiesta = {"user" :  $scope.jUser}
    var richiestas = JSON.stringify(richiesta)
      
    
    $http({
        
        method : "POST",
        url : urlLog,
        body : richiestas 
        
    }).then(function successo(response){
        
    window.location = "benvenuto.html";
    localStorage.setItem("user", response);
    
    }), function fallimento(response){
        
        alert(response.statusText);
        
    }
    
    };

    $scope.registrati = function(){
        
         richiesta = {"user" : $scope.jUser}
         var richiestas = JSON.stringify(richiesta) 
          
    $http({
        method : "POST",
        url : urlSig,
        body : richiestas
               
    }).then(function successo(response){
        
       //conferma di avvenuta iscrizione
       alert("UTENTE REGISTRATO");
       
    }) , function fallimento(response){
        
        alert(response.statusText);
        
    }
            
    }



});

