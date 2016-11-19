angular.module('app', [])
	.controller('home', function($http) {
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
	});
