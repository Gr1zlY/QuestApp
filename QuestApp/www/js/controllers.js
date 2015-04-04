angular.module('quest.controllers', [])

.controller('QuestSelectCtrl', ['$scope','$rootScope', function($scope, $rootScope) {

    $scope.quests = [
        {questId: 1, name: "ugly", description: "ugly", photo: null},
        {questId: 2, name: "ugly 2", description: "ugly 2", photo: null}
    ];
    
    $scope.refreshdata = function(){
        $scope.quests.push( {questId: 2, name: "ugly 2", description: "ugly 2", photo: null});
    }

    $scope.refreshdata();
}])

.controller('TeamSelectCtrl', ['$scope','$state', '$stateParams', function($scope, $state, $stateParams) {

    //var questId = $routeParams.questId;

    $scope.teams = [
        {teamId: 1, name: "ugly"},
        {teamId: 2, name: "ugly 2"}
    ];

    $scope.refreshdata = function(){
        //pull data from server
    }

    $scope.refreshdata();

    $scope.teamselected = function(teamId){
        //send get to save
        //send with device id
        console.log(teamId);
        $state.go("questinfo", {});
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
                    alert('check your location settings');
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
