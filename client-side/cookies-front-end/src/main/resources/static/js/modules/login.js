angular.module('loginModule', ['ui.router', 'userProfileModule'])
    .config (function ($stateProvider) {
        $stateProvider
            .state('user-profile', {
                url : '/user-profile',
                params : { userInfo : null },
                templateUrl : '../../templates/user-profile/user-profile.html',
                controller : 'static-profile'
            })
            .state('registration-success', {
                url : '/reg-success',
                params : { userInfo : null },
                templateUrl : '../../templates/registration-success.html',
                controller : 'static-profile'
            });
    })
    .controller('registration', function($http, $state) {
        var self = this;

        self.gender = [ "male", "female" ];

        self.submitDetails = function () {

            $http.get('/api/ping').then(function (response) {
                self.result = response.data.time;
                console.log('ping result :: ' + self.result)
            });

            if (self.user.passwordConfirm != self.user.password) {
                self.message = "confirmation password does not matches password";
            } else {
                delete self.user.passwordConfirm;

                $http.post('/api/createUser', self.user).then(function (response) {
                    self.message = false;
                    $state.go('registration-success', { userInfo : response.data.userInfo });
                }, function (response) {
                    console.log(response.data);
                    self.message = "error creating user !";
                });
            }
        }
    })
    .controller('login', function($http, $state) {
        var self = this;

        self.checkValidLogin = function () {
            if (!self.user || !self.user.email || !self.user.password) {
                if (!self.user || !self.user.email)
                    self.message = "no user email provided";
                else
                    self.message = "no user password provided";
            } else {
                $http.get('/api/checkLogin/' + self.user.email + "/" + self.user.password).then(function (response) {
                    self.message = false;
                    $state.go('user-profile', { userInfo : response.data.userInfo });
                }, function (response) {
                    console.log(response.data.status);
                    self.message = "Invalid login or password!";
                });
            }
        }
    });