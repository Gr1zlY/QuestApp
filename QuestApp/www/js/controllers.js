angular.module('quest.controllers', [])

.controller('QuestSelectCtrl', ['$scope','$rootScope', '$http', '$ionicPopup', '$ionicLoading',function($scope, $rootScope, $http, $ionicPopup, $ionicLoading) {

    $scope.loading = function(){
        $ionicLoading.show({
            template: '<ion-spinner></ion-spinner>'
        });
    }

    $scope.endloading = function(){
        $ionicLoading.hide();
    }

    $scope.quests = [];
    
    $scope.refreshdata = function(){

        $scope.loading();

        $http.get("http://filetransfereasyaspie.com:8087/getQuests").then(
            function(responce){
                $scope.quests = responce.data;
                $scope.endloading();
            },
            function(error){
                console.log(error);
                $scope.endloading();

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

.controller('TeamSelectCtrl', ['$scope','$state', '$stateParams', '$http','$ionicPopup', '$ionicLoading', function($scope, $state, $stateParams, $http, $ionicPopup, $ionicLoading) {

    $scope.loading = function(){
        $ionicLoading.show({
            template: '<ion-spinner></ion-spinner>'
        });
    }

    $scope.endloading = function(){
        $ionicLoading.hide();
    }

    var questId = $stateParams.questId;

    $scope.teams = [];

    $scope.refreshdata = function(){

        $scope.loading();

        $http.get("http://filetransfereasyaspie.com:8087/getTeams", {params: {"questId":questId}}).then(
            function(responce){
                console.log(responce);
                $scope.teams = responce.data;

                $scope.endloading();
            },
            function(error){
                console.log(error);
                $scope.endloading();

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
        deviceId = 'browser1';
    }

    $scope.teamselected = function(teamId){

        $scope.loading();

        $http.get("http://filetransfereasyaspie.com:8087/joinTeam", {params: {"teamId":teamId, "deviceId": deviceId}}).then(
            function(responce){
                $scope.endloading();
                $state.go("questinfo", {});
            },
            function(error){
                $scope.endloading();
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
                            $scope.loading();
                            $http({
                                url: "http://filetransfereasyaspie.com:8087/createTeam",
                                type: "GET",
                                params: {"questId":questId, "deviceId" : deviceId, "name": $scope.team.name}
                            }).then(
                                function(responce){
                                    console.log(responce);
                                    $scope.endloading();

                                    if(responce.data.status.localeCompare("OK") == 0){
                                        $state.go("questinfo", {});
                                    }
                                },
                                function(error){
                                    console.log(error);
                                    $scope.endloading();
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

.controller('QuestInfoCtrl', ['$scope','$rootScope', '$http', '$ionicPopup', '$ionicLoading',function($scope, $rootScope, $http, $ionicPopup, $ionicLoading) {

    $scope.loading = function(){
        $ionicLoading.show({
            template: '<ion-spinner></ion-spinner>'
        });
    }

    $scope.endloading = function(){
        $ionicLoading.hide();
    }

    //TODO: creanup
    var deviceId = '';
    if(typeof device !== 'undefined'){
        deviceId = device.uuid;
    } else {
        deviceId = 'browser1';
    }
    $scope.deviceId = deviceId;

    $scope.quest = {};
    $scope.tasks = [];

    $scope.refreshdata = function(){
        $scope.loading();
        $http.get("http://filetransfereasyaspie.com:8087/getQuestByDeviceId", {params: {"deviceId":deviceId}}).then(
            function(response){
                $scope.quest = response.data;
                $scope.endloading();
            },
            function(error){
                console.log(error);
                $scope.endloading();

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

        $http.get("http://filetransfereasyaspie.com:8087/getAvailableTasks", {params: {"deviceId":deviceId}}).then(
            function(response){
                $scope.tasks = response.data;
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

.controller('TaskInfoCtrl', ['$scope', '$state', '$stateParams', '$http', '$ionicLoading', '$ionicPopup', function($scope, $state, $stateParams, $http, $ionicLoading, $ionicPopup) {

    var taskId = $stateParams.taskId;

    //TODO: creanup
    var deviceId = '';
    if(typeof device !== 'undefined'){
        deviceId = device.uuid;
    } else {
        deviceId = 'browser1';
    }
    $scope.deviceId = deviceId;

    $scope.task = {};
    $scope.refreshdata = function(){

        $http.get("http://filetransfereasyaspie.com:8087/getTaskById", { params: { "deviceId":deviceId, "taskId": taskId } }).then(
            function(responce){
                $scope.task = responce.data;
            },
            function(error){

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

        $http.get("http://filetransfereasyaspie.com:8087/attemptSolution", { params: { "deviceId":deviceId, "solutionCandidate": value, "taskId": taskId } }).then(
            function(responce){

                var result = responce.data;

                $scope.endloading();

                if(result.status.localeCompare("WRONG") == 0){
                    var alertBox = $ionicPopup.alert({
                        title: 'Wrong answer',
                        template: 'Search for another solution'
                    });
                } else {
                    var alertBox = $ionicPopup.alert({
                        title: 'Corrent solution',
                        template: '<i class="icon ion-checkmark"></i> Great'
                    });

                    alertBox.then(function(){
                        $state.go("taskinfo", {'taskId': result.nextTask});
                    });

                }

            },
            function(error){
                $scope.endloading();

                var confirmPopup = $ionicPopup.confirm({
                    title: 'Network Error',
                    template: 'Try again?'
                });

                confirmPopup.then(function(res){
                    if(res){
                        submit(value);
                    }
                });
            }
        );
    }

    $scope.getlocation = function(){
        navigator.geolocation.getCurrentPosition(
            function(result){
                submit({type: "geo", data: result});
            },
            function(error){
                alert(error.code+ error.message + ' check your location settings');
                cordova.require('cordova/plugin/diagnostic').switchToLocationSettings();
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

    $scope.str = {};
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
                            return $scope.str.solution;
                        }
                    }
                }
            ]
        });
    
    }

    $scope.refreshdata();

}]);
