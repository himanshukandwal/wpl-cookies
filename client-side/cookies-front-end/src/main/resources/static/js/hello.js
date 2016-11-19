angular.module('hello', []).controller('home', function($http) {
	var self = this;
	
	$http({
	    url: 'http://192.168.0.12:8080/cookies-spring-webmvc/api/userlogin/ani123kv@gmail.com/password',
	    method: 'GET',
	    headers: {
	        'Content-Type': 'application/json'
	    }
	}).then(function successCallback(response) {
		self.greeting = response.data;
	  }, function errorCallback(response) {
		self.greeting = { id : 'No answer', content : 'Hello World' };
	  })
	  
	  
});
