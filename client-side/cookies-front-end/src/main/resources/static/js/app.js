angular.module('app', ['ui.router', 'loginModule'])
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
	});