'use strict';

var questServices = angular.module('questServices', []);

questServices.service('userService', function() {
  var user;

  var setUser = function(newObj) {
    user = newObj;
    $.cookie("userId", newObj.userId, { expires : 1000 });
    $.cookie("userLogin", newObj.login, { expires : 1000 });
  }

  var getUser = function(){
    if (user != null)
      return user;
    else {
      var cookieId = $.cookie("userId");
      var cookieLogin = $.cookie("userLogin");
      if (cookieId != null && cookieLogin != null)
        return {login: cookieLogin, userId: cookieId};
      else
        return null;
    }
  }

  return {
    setUser: setUser,
    getUser: getUser
  };
});