angular.module('quest.controllers', [])

.controller('QuestSelectCtrl', ['$scope','$rootScope', '$http', function($scope, $rootScope, $http) {

    $scope.quests = [];
        //{questId: 1, name: "ugly", description: "ugly", photo: null},
        //{questId: 2, name: "ugly 2", description: "ugly 2", photo: null}
    
    $scope.refreshdata = function(){

        $http.get("http://192.168.20.38:8087/getQuests").then(
            function(responce){
                //console.log(responce);
                $scope.quests = responce.data;
            },
            function(error){
                console.log(error);
                //TODO: arr retry
            }
        );
    }

    $scope.refreshdata();
}])

.controller('TeamSelectCtrl', ['$scope','$state', '$stateParams', '$http','$ionicPopup',function($scope, $state, $stateParams, $http, $ionicPopup) {

    var questId = $stateParams.questId;

    $scope.teams = [];
        //{teamId: 1, name: "ugly"},
        //{teamId: 2, name: "ugly 2"}


    $scope.refreshdata = function(){
        //pull data from server
        $http.get("http://192.168.20.38:8087/getTeams", {params: {"questId":questId}}).then(
            function(responce){
                console.log(responce);
                $scope.teams = responce.data;
            },
            function(error){
                console.log(error);
            }
        );
    }

    $scope.refreshdata();

    //TODO: creanup
    var deviceId = '';
    if(typeof device !== 'undefined'){
        deviceId = device.uuid;
    } else {
        deviceId = 'browser';
    }

    $scope.teamselected = function(teamId){
        //send get to save
        //send with device id
        console.log(teamId);
        $state.go("questinfo", {});
    }

    $scope.team = {};

    $scope.createteam = function(){
        var teamPopup = $ionicPopup.show({
            template: '<input type="text" ng-model="team.name" />',
            title: 'Enter team name',
            scope: $scope,
            buttons: [
                {text: 'Cancel'},
                {text: '<b>Create</b>',
                    type: 'button-positive',
                    onTap: function(e){
                        if(!$scope.team.name){
                            e.preventDefault();
                            //TODO: cleanup
                        } else {
                            return $scope.team.name;
                        }
                    }
                }
            ]
        });
        teamPopup.then(function(result){
            if(result !== null){
                $http.get("http://192.168.20.38:8087/createTeam", {params: {"questId":questId, "deviceId" : deviceId, "name": $scope.team.name}}).then(
                    function(responce){
                        console.log(responce);
                        //$scope.teams = responce.data;
                    },
                    function(error){
                        console.log(error);
                    }
                );
            }
        });
    
    }

}])

.controller('QuestInfoCtrl', ['$scope','$rootScope', function($scope, $rootScope) {

    $scope.quest = {questId: 1, name: "Blah quest", description: "desc", photo: null};

    $scope.quest.tasks = [
        {taskId: 1, name: "mastrubate", status: true},
        {taskId: 2, name: "mastrubate more", status: false}
    ];

    $scope.refreshdata = function(){
    
    }

    $scope.refreshdata();
}])

.controller('TaskInfoCtrl', ['$scope', '$stateParams', '$ionicLoading', function($scope, $stateParams, $ionicLoading) {

    console.log($stateParams.taskId);

    $scope.task = {taskId: 2, name: 'Task 1', description: 'asdfasdfasdf', photo: null, status: false};

    $scope.refreshdata = function(){
    
    }

    $scope.loading = function(){
        $ionicLoading.show({
            template: '<ion-spinner></ion-spinner>'
        });
    }

    $scope.endloading = function(){
        $ionicLoading.hide();
    }
    
    var submit = function(value){
        $scope.loading();
        setTimeout($scope.endloading(), 10000);
    }

    $scope.getlocation = function(){
        //$scope.loading();

        navigator.geolocation.getCurrentPosition(
                function(result){
                    submit({type: "geo", data: result});
                },
                function(error){
                    alert(error.code+ error.message + ' check your location settings');
                });
    }
    
    $scope.scanqrcode = function(){
        alert('blah');
        cordova.plugins.barcodeScanner.scan(
                function(result){
                    //alert(result.text);
                    //send result
                    submit({type: "qr", data: result});
                }, 
                function(error){
                    alert("no code");
                }
            );    
    }

    $scope.enterstring = function(){
    
    }

    $scope.refreshdata();

}]);
