'use strict';

/* Controllers */
var questControllers = angular.module('questControllers', []);

questControllers.controller('LoginCtrl', ['$scope', '$http', '$location',
	function($scope, $http, $location) {
		$scope.do_login = function() {
			$http.post('/someUrl').
			success(function(data) {
				$location.path('/quests');
			}).
			error(function(data) {
				/*alert(data);*/
				$location.path('/quests');
			});
		}
		$scope.go = function ( path ) {
			$location.path( path );
		};
	}]);

questControllers.controller('RegistrationCtrl', ['$scope', '$http', '$location',
	function($scope, $http, $location) {
		$scope.register = function() {
			if ($scope.pass == $scope.confirm_pass) {
				$http.post('/someUrl').
				success(function(data) {
					$location.path('/quests');
				}).
				error(function(data) {
					/*alert(data);*/
					$location.path('/quests');
				});
			} else {
				alert('ddd');
			}
		};
	}]);

questControllers.controller('EditQuestCtrl', ['$scope', '$http', '$location', '$routeParams',
	function($scope, $http, $location, $routeParams) {
		$http.get('getQuests.json').then(function(response) {
			var quests = response.data;
			$scope.quest = $.grep(quests, function(e) { return e.questId==$routeParams.id })[0];
		}, function(response) {
			var data = response.data,
			status = response.status,
			header = response.header,
			config = response.config;
			alert('error, status: '+status);
		});
		
	}]);

questControllers.controller('QuestsListCtrl', ['$scope', '$http', '$location',
	function($scope, $http, $location) {
		$scope.do_search = function() {
			alert($scope.search_word);
		};

		$scope.addQuest = function() {
			$location.path('/editQuest');
		};

		$scope.editQuest = function(id) {
			$location.path('/editQuest/'+id);
		};

		$scope.removeQuest = function(id) {
			/*$http.delete('192.168.20.38:8087/quest?id='+id);*/
			var oldQuests = $scope.quests;
			$scope.quests = $.grep(oldQuests, function(e) { return e.questId!=id });
		};

		$scope.seeAllQuest = function() {
		};

		$http.get('getQuests.json').then(function(response) {
			$scope.quests = response.data;
		}, function(response) {
			var data = response.data,
			status = response.status,
			header = response.header,
			config = response.config;
			alert('error, status: '+status);
		});
	}]);

