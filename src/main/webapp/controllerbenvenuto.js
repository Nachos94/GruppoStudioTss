/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module("benvenuto", []).controller("controller", function ($scope) {


    (function datipersonali() {

        var user = localStorage.getItem("user");

        $scope.Nome = user.Nome;
        console.log(user.Nome);
        $scope.Cognome = user.Cognome;

        if (user.fileassociati != null) {
            $scope.fileassociati = user.fileAssociati;
        }

    })();


    function fileupload() {


    }


    function filedownload() {


    }


})




