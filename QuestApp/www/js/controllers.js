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

.controller('TeamSelectCtrl', ['$scope','$routeParams', function($scope, $routeParams) {

    var questId = $routeParams.questId;

    $scope.teams = [
        {teamId: 1, name: "ugly"},
        {teamId: 2, name: "ugly 2"}
    ];

    $scope.refreshdata = function(){

    
    }

    $scope.refreshdata();

}])

.controller('QuestInfoCtrl', ['$scope','$rootScope', function($scope, $rootScope) {

    $scope.refreshdata = function(){
    
    }

    $scope.refreshdata();

}]);
