// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
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
            controller: 'TeamSelectCtrl'
        })
        .state('questinfo', {
            url: "/questinfo",
            templateUrl: "templates/questview.html",
            controller: 'QuestInfoCtrl'
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
