
angular.module('quest', ['ionic', 'quest.controllers'])

.config(function($stateProvider, $urlRouterProvider, $compileProvider){

    $stateProvider
        .state('questselect', {
            url: "/questselect",
            templateUrl: "templates/questselect.html",
            controller: 'QuestSelectCtrl'
        })  
        .state('teamselect', {
            url: "/teamselect/:questId",
            templateUrl: "templates/teamselect.html",
            controller: 'TeamSelectCtrl',
            cache: false
        })
        .state('questinfo', {
            url: "/questinfo",
            templateUrl: "templates/questview.html",
            controller: 'QuestInfoCtrl'
        })
        .state('taskinfo', {
            url: "/taskinfo/:taskId",
            templateUrl: "templates/taskview.html",
            controller: 'TaskInfoCtrl'
        });

    $urlRouterProvider.otherwise('/questselect');
    
})

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if(window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})
