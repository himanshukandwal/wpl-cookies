angular.module('loginModule', [])
    .controller('registration', function($http,$window) {
        var self = this;

        self.submitDetails = function () {
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
                    $window.location.href = '/login.html';

                }, function (response) {
                    console.log(response.data.status);
                    self.result = "invalid login or password!";
                });
            }
        }
    });