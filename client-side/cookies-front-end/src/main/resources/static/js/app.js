angular.module('app', ['ui.router'])
	.config(function($stateProvider, $urlRouterProvider) {

		$urlRouterProvider
			.when('', '/')
			.otherwise(function($injector) {
				var $state = $injector.get('$state');
				$state.go('404', null, {
					location: false
			});

		});

		$stateProvider
			.state('home', {
				url : '/',
				templateUrl : '../templates/welcome.html'
			})
			.state('login', {
				url : '/login',
				templateUrl : '../templates/login.html'
			})
			.state('about-us', {
				url : '/about-us',
				templateUrl : '../templates/about-us.html'
			})
			.state('contact-us', {
				url : '/contact-us',
				templateUrl : '../templates/contact-us.html'
			})
			.state('404', {
				url: "/404",
				templateUrl: "../templates/404.html",
				data: {
					pageTitle: '404 Not found'
				}
			});
	})
	.controller('registration', function($http,$window) {
		var self = this;
		
		self.submitDetails = function () {
			console.log("here")
			console.log(self.user);
	
			$http.get('/ping').then(function (response) {
				self.result = response.data.time;
				console.log('result :: ' + self.result)
			});
	
			$http.post('/createUser', self.user).then(function (response) {
				self.result = response.data.status;
				console.log('result :: ' + self.result)
			});
		}
	})
	.controller('login', function($http,$window) {
		var self = this;
		
		self.checkValidLogin = function () {
			if (self.user == '' || self.user.email == '' || self.user.password == '') {
				if (self.user == '')
					self.result = "null user element";
				else if (self.user.email == '')
					self.result = "no user email provided";
				else
					self.result = "no user password provided";
			} else {
				$http.get('/checkLogin/' + self.user.email + "/" + self.user.password).then(function (response) {
					console.log(response.data.status);
					self.result = false;
				}, function (response) {
					console.log(response.data.status);
					self.result = "invalid login or password!";
				});
			}
		}
	});