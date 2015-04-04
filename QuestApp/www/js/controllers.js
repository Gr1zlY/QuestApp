angular.module('quest.controllers', [])

.controller('QuestSelectCtrl', ['$scope','$rootScope', '$http', '$ionicPopup', function($scope, $rootScope, $http, $ionicPopup) {

    $scope.quests = [];
    
    $scope.refreshdata = function(){

        $http.get("http://192.168.20.38:8087/getQuests").then(
            function(responce){
                $scope.quests = responce.data;
            },
            function(error){
                console.log(error);

                var confirmPopup = $ionicPopup.confirm({
                    title: 'Network Error',
                    template: 'Try again?'
                });

                confirmPopup.then(function(res){
                    if(res){
                        $scope.refreshdata();
                    }
                });
            }
        );
    }

    $scope.refreshdata();
}])

.controller('TeamSelectCtrl', ['$scope','$state', '$stateParams', '$http','$ionicPopup',function($scope, $state, $stateParams, $http, $ionicPopup) {

    var questId = $stateParams.questId;

    $scope.teams = [];

    $scope.refreshdata = function(){

        $http.get("http://192.168.20.38:8087/getTeams", {params: {"questId":questId}}).then(
            function(responce){
                console.log(responce);
                $scope.teams = responce.data;
            },
            function(error){
                console.log(error);

                var confirmPopup = $ionicPopup.confirm({
                    title: 'Network Error',
                    template: 'Try again?'
                });

                confirmPopup.then(function(res){
                    if(res){
                        $scope.refreshdata();
                    }
                });
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

        $http.get("http://192.168.20.38:8087/joinTeam", {params: {"teamId":teamId, "deviceId": deviceId}}).then(
            function(responce){
                $state.go("questinfo", {});
            },
            function(error){
                console.log(error);
            }
        );
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
                        } else {
                            $http({
                                url: "http://192.168.20.38:8087/createTeam",
                                type: "GET",
                                params: {"questId":questId, "deviceId" : deviceId, "name": $scope.team.name}
                            }).then(
                                function(responce){
                                    console.log(responce);
                                    if(responce.data.status.localeCompare("OK") == 0){
                                        $state.go("questinfo", {});
                                    }
                                },
                                function(error){
                                    console.log(error);
                                    e.preventDefault();
                                }
                            );
                        }
                    }
                }
            ]
        });
    }
}])

.controller('QuestInfoCtrl', ['$scope','$rootScope', '$http', '$ionicPopup', function($scope, $rootScope, $http, $ionicPopup) {

    //TODO: creanup
    var deviceId = '';
    if(typeof device !== 'undefined'){
        deviceId = device.uuid;
    } else {
        deviceId = 'browser';
    }

    $scope.quest = {};

    $scope.quest.tasks = [
        {taskId: 1, name: "mastrubate", status: true},
        {taskId: 2, name: "mastrubate more", status: false}
    ];

    $scope.refreshdata = function(){
        $http.get("http://192.168.20.38:8087/getQuestByDeviceId", {params: {"deviceId":deviceId}}).then(
            function(responce){
                $scope.quest = responce.data;
            },
            function(error){
                console.log(error);
            }
        );
    }

    $scope.refreshdata();
}])

.controller('TaskInfoCtrl', ['$scope', '$stateParams', '$ionicLoading', '$ionicPopup', function($scope, $stateParams, $ionicLoading, $ionicPopup) {

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
        //$scope.loading();

        /*$http.get("http://192.168.20.38:8087/getQuestByDeviceId", {params: {"deviceId":deviceId}}).then(
            function(responce){
                $scope.quest = responce.data;
            },
            function(error){
                console.log(error);
            }
        );*/
    }

    $scope.getlocation = function(){
        navigator.geolocation.getCurrentPosition(
            function(result){
                submit({type: "geo", data: result});
            },
            function(error){
                alert(error.code+ error.message + ' check your location settings');
            }
        );
    }
    
    $scope.scanqrcode = function(){
        cordova.plugins.barcodeScanner.scan(
                function(result){
                    submit({type: "qr", data: result});
                }, 
                function(error){
                    alert("no code");
                }
            );    
    }

    $scope.str.solution = {};
    $scope.enterstring = function(){
        var solutionPopup = $ionicPopup.show({
            template: '<input type="text" ng-model="str.solution" />',
            title: 'Enter tast solution',
            scope: $scope,
            buttons: [
                {text: 'Cancel'},
                {text: '<b>Check</b>',
                    type: 'button-positive',
                    onTap: function(e){
                        if(!$scope.str.solution){
                            e.preventDefault();
                        } else {
                            submit({type: "string", data: $scope.str.solution});
                        }
                    }
                }
            ]
        });
    
    }

    $scope.refreshdata();

}]);
