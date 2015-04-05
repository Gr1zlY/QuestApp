'use strict';

/* Controllers */
var questControllers = angular.module('questControllers', []);

questControllers.controller('LoginCtrl', ['$scope', '$http', '$location', 'userService',
	function($scope, $http, $location, userService) {
		$scope.do_login = function() {
			$http.post('http://filetransfereasyaspie.com:8087/login?login='+$scope.login+'&password='+$scope.pass).then(
				function(data) {
					if (data == "-1")
						return;
					var user = {
						userId: data,
						login: $scope.login
					};
					userService.setUser(user);
					$location.path('/quests');
				},
				function(data) {
					console.log(data);
					alert('Unknown error. Please fuck off..');
				});
		}
		$scope.go = function ( path ) {
			$location.path( path );
		};
	}]);

questControllers.controller('RegistrationCtrl', ['$scope', '$http', '$location', 'userService',
	function($scope, $http, $location, userService) {
		$scope.register = function() {
			if ($scope.pass == $scope.confirm_pass) {
				$http.post('http://filetransfereasyaspie.com:8087/register?login='+$scope.login+'&password='+$scope.pass).
				success(function(data) {
					var user = {
						userId: data,
						login: $scope.login
					};
					userService.setUser(user);
					$location.path('/quests');
				}).
				error(function(data) {
					console.log(data);
					alert('Unknown error. Please fuck off..');
				});
			} else {
				alert('Password and confim password don\'t match! Try it again.');
			}
		};
	}]);

questControllers.controller('EditQuestCtrl', ['$scope', '$http', '$location', '$routeParams', /*'$flow',*/ 'userService',
	function($scope, $http, $location, $routeParams, /*$flow,*/ userService) {
		if (userService.getUser() == null)
			$location.path('/');

		if ($routeParams.id == "-1") {
			$scope.quest = {};
			$scope.tasks = [];
		} else {
			$http.get('http://filetransfereasyaspie.com:8087/getQuests').then(function(response) {
				var quests = response.data;
				var quest = $.grep(quests, function(e) { return e.questId==$routeParams.id })[0];
				$scope.quest = quest;
				console.log(quest.questId);
				$http.get('http://filetransfereasyaspie.com:8087/getTasksByQuest?questId='+quest.questId).then(function(response) {
					console.log(response.data);
					$scope.tasks = response.data;
					$scope.oldTasks = response.data;
				}, function(response) {
					alert('error, status: '+response.status);
				});
			}, function(response) {
				alert('error, status: '+response.status);
			});
		}

		$scope.addTask = function() {
			var dd = new Date();
			var taskId = Date.UTC(dd.getFullYear(),dd.getMonth(),dd.getDay(),dd.getHours(),dd.getMinutes(),dd.getSeconds(),dd.getMilliseconds());
			$scope.tasks.push({taskId: taskId, taskName: "New name"});
		};

		$scope.removeTask = function(id) {
			var oldTasks = $scope.tasks;
			$scope.tasks = $.grep(oldTasks, function(e) { return e.taskId!=id });
		};

		$scope.editQuest = function() {
			var dd = new Date();
			var time = Date.UTC(dd.getFullYear(),dd.getMonth(),dd.getDay(),dd.getHours(),dd.getMinutes(),dd.getSeconds(),dd.getMilliseconds());
			if ($scope.quest.questId == null) {
				$http.post('http://filetransfereasyaspie.com:8087/addNewQuest?name='+$scope.quest.name+
					'&description='+$scope.quest.description+
					'&photo=null'/*+$flow.files[0]*/+
					'&userId='+userService.getUser().userId+
					'&time='+time).then(function(data) {
						console.log(data);
						$location.path('/quests');
					},
					function(data) {
						console.log(data);
						alert('error');
					});
				} else {
					$http.post('http://filetransfereasyaspie.com:8087/updateQuest?questId='+$scope.quest.questId+
						'&name='+$scope.quest.name+
						'&description='+$scope.quest.description+
						'&photo=null'+/*$flow.files[0]+*/
						'&userId='+userService.getUser().userId+
						'&time='+time).then(function(data) {
							console.log(data);
							console.log($scope.tasks[0]);
							var i;
							for (i=0;i<$scope.tasks.length;i++) {
								var task = $scope.tasks[i];
								/*$.each($scope.tasks, function(task){*/
									if ($scope.oldTasks.indexOf(task) == -1) {
										console.log(task);
										$http.post('http://filetransfereasyaspie.com:8087/insertTask?questId='+$scope.quest.questId+
											'&name='+task.taskName+
											'&description='+task.description+
											'&photo=null'+
											'&ordinalNumber='+$scope.tasks.indexOf(task)+
											'&taskType='+task.taskType+
											'&solution='+task.solution).then(function(response) {
												console.log(response.data);
											}, function(response) {
												console.log(response);
												alert('error, status: '+response.status);
											});
										} else {
											$http.post('http://filetransfereasyaspie.com:8087/removeTaskByTaskId?taskId='+task.taskId).then(function(data) {
												$http.post('http://filetransfereasyaspie.com:8087/insertTask?questId='+$scope.quest.questId+
													'&name='+task.taskName+
													'&description='+task.description+
													'&photo=null'+
													'&ordinalNumber='+$scope.tasks.indexOf(task)+
													'&taskType='+task.taskType+
													'&solution='+task.solution).then(
													function(response) {
														console.log(response.data);
													}, function(response) {
														console.log(response);
														alert('error, status: '+response.status);
													});
												});
										}
									};
									$location.path('/quests');
								},
								function(data) {
									console.log(data);
									alert('Error during adding new quest. Please wait, and try it again.')
								});
}
};
$scope.go = function ( path ) {
	$location.path( path );
};
}]);

questControllers.controller('QuestsListCtrl', ['$scope', '$http', '$location', 'userService',
	function($scope, $http, $location, userService) {
		if (userService.getUser() == null)
			$location.path('/');
		
		$scope.do_search = function() {
			alert($scope.search_word);
		};

		$scope.addQuest = function() {
			$location.path('/editQuest/-1');
		};

		$scope.editQuest = function(id) {
			$location.path('/editQuest/'+id);
		};

		$scope.removeQuest = function(id) {
			$http.post('http://filetransfereasyaspie.com:8087/removeQuestById?questId='+id).then(
				function(data) {
					var oldQuests = $scope.quests;
					$scope.quests = $.grep(oldQuests, function(e) { return e.questId!=id });
				},
				function(data) {
					console.log(data);
					alert('Error during removing quest. Try it one more time or just give up.');
				});
		};

		$scope.seeAllQuest = function() {
		};

		$http.get('http://filetransfereasyaspie.com:8087/getQuests').then(function(response) {
			$scope.quests = response.data;
		}, function(response) {
			alert('error, status: '+response.status);
		});
	}]);