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
		self.checkValidLogin = function () {
			console.log("here")
			console.log(self.user);

			self.result.hidden = false;
			$http.get('/checkLogin/'+ self.user.email+"/"+self.user.password).then(function (response) {
				console.log("status"+response.data.status);
				self.result = response.data.userInfo.email;
				console.log('result :: ' + self.result)
			}, function (response) {
				self.result.hidden = true;
				self.result="invalid login";
				console.log('result ::::: ' + self.result)
			});
		}
		
		
		
		
		
	});
