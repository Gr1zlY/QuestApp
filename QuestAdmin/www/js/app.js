'use strict';

/* App Module */

var questApp = angular.module('questApp', [
  'flow',
  'ngRoute',
  'questServices',
  'questControllers'
  ]);

questApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/registration', {
      templateUrl: 'partials/registration.html',
      controller: 'RegistrationCtrl'
    }).
    when('/login', {
      templateUrl: 'partials/login.html',
      controller: 'LoginCtrl'
    }).
    when('/quests', {
      templateUrl: 'partials/quests.html',
      controller: 'QuestsListCtrl'
    }).
    when('/editQuest/:id', {
      templateUrl: 'partials/editQuest.html',
      controller: 'EditQuestCtrl'
    }).
    when('/editQuest', {
      templateUrl: 'partials/editQuest.html',
      controller: 'EditQuestCtrl'
    }).
    otherwise({
      redirectTo: '/login'
    });
  }]);