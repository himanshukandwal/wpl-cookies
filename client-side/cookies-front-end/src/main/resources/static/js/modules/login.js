angular.module('loginModule', ['ui.router', 'ngAnimate'])
    .config (function ($stateProvider) {
        $stateProvider
            .state('login-success', {
                url : '/user-profile',
                templateUrl : '../templates/user-profile.html'
            })
            .state('registration-success', {
                url : '/reg-success',
                templateUrl : '../templates/reg-success.html'
            })
            .state('editprofile', {
                url : '/edit-profile',
                templateUrl : '../templates/edit-profile.html'

            });
    })
    .controller('registration', function($http, $injector) {
        var self = this;

        self.submitDetails = function () {
            console.log(self.user);

            $http.get('/ping').then(function (response) {
                self.result = response.data.time;
                console.log('result :: ' + self.result)
            });

            if (self.user.passwordConfirm != self.user.password) {
                self.message = "confirmation password does not matches password";
            } else {
                $http.post('/createUser', self.user).then(function (response) {
                    self.message = false;
                    var $state = $injector.get('$state');
                    $state.go('registration-success');
                }, function (response) {
                    console.log(response.data.status);
                    self.message = "Error creating user";
                });
            }
        }
    })
    .controller('login', function($http, $injector) {
        var self = this;

        self.checkValidLogin = function () {
            if (!self.user || !self.user.email || !self.user.password) {
                if (!self.user || !self.user.email)
                    self.message = "no user email provided";
                else
                    self.message = "no user password provided";
            } else {
                $http.get('/checkLogin/' + self.user.email + "/" + self.user.password).then(function (response) {
                    self.message = false;
                    sessionStorage.user= JSON.stringify(response.data.userInfo);
                    console.log(sessionStorage.user)

                    var $state = $injector.get('$state');
                    $state.go('login-success');
                }, function (response) {
                    console.log(response.data.status);
                    self.message = "Invalid login or password!";
                });
            }
        }
    });